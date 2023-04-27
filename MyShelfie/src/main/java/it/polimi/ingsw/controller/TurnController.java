package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.clientSide.OrderReplyMessage;
import it.polimi.ingsw.network.message.clientSide.PositionReplyMessage;
import it.polimi.ingsw.network.message.clientSide.TilesReplyMessage;
import it.polimi.ingsw.network.message.serverSide.GenericMessage;
import it.polimi.ingsw.view.VirtualView;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * class that manages a turn in the game
 */
public class TurnController implements Serializable {
    @Serial
    private static final long serialVersionUID = -1731938445482154456L;
    private final Game game;
    private TurnState turnState;
    private final GameController gameController;
    private Map<String, VirtualView> virtualViewMap;
    private final List<String> nicknames;
    private String currentPlayer;
    private boolean hasAnswered = false;
    private int currentPosition;
    private List<Tile> currentTiles;
    private Bookshelf currentBookshelf;
    private List<CommonGoalCard> commonGoalCards;
    private boolean isStarted;
    private boolean isLast;

    /**
     * constructor of the turn controller
     * @param game the current game
     * @param gameController the game controller
     * @param virtualViewMap the virtual view of all the clients
     */
    public TurnController(Game game, GameController gameController, Map<String, VirtualView> virtualViewMap) {
        this.game = game;
        this.gameController = gameController;
        this.virtualViewMap = virtualViewMap;
        this.nicknames = new ArrayList<>(game.getAllPlayers());
        this.currentPlayer = nicknames.get(0);
        this.turnState = TurnState.START;
        this.isStarted = false;
        this.isLast = false;
        this.commonGoalCards = new ArrayList<>(game.getCommongoalcards());
    }

    /**
     * handles all messages received from the game controller
     * (must write all messages sent by the client (reply messages))
     * @param message received
     */
    public void messageFromGameController(Message message) {
        switch (message.getMessageType()) {
            case TILES_REPLY -> {
                TilesReplyMessage tilesReplyMessage = (TilesReplyMessage) message;
                currentTiles = tilesReplyMessage.getTiles();
                hasAnswered = true;
            }
            case POSITION_REPLY -> {
                PositionReplyMessage positionReplyMessage = (PositionReplyMessage) message;
                currentPosition = positionReplyMessage.getColumn();
                currentTiles = positionReplyMessage.getTiles();
                hasAnswered = true;
            }
            case GENERIC -> {
                GenericMessage genericMessage = (GenericMessage) message;
                hasAnswered = true;
            }
            case ORDER_REPLY -> {
                OrderReplyMessage orderReplyMessage = (OrderReplyMessage) message;
                currentTiles = orderReplyMessage.getTiles();
                hasAnswered = true;
            }
        }
    }

    /**
     * main method that handles all the phases of the turn
     */
    public void turnManager() {

        while(turnState != TurnState.END) {
            //If is the first turn, select the first player and give him the seat
            if(!isStarted) {
                selectFirstPlayer();
            }

            //At the begging of each turn check if the board needs to refill
            if(game.getBoard().isRefillable()) {
                for (VirtualView virtualView : virtualViewMap.values()) {
                    virtualView.showGenericMessage("Board is refilling...");
                }
                game.getBoard().fillBoard(game.getBag());
            }

            //Shows the situation board
            showCurrentBoard();

            //Selects the tiles from the board
            selectTiles();

            //Shows the situation board
            showCurrentBoard();

            //Shows bookshelf of the current player
            showCurrentBookshelf();

            //Inserts the tiles in the bookshelf
            insertTiles();

            //Shows bookshelf of the current player
            showCurrentBookshelf();

            //Check common goal cards
            checkCommonCards();

            //Check bookshelf full
            //Game is finish or last round
            if(currentBookshelf.isFull()) {
                notifyBookshelfFull();
                if(currentPlayer.equalsIgnoreCase(game.getAllPlayers().get(game.getNum()-1))) {
                    turnState = TurnState.END;
                    //Shows scores

                }
                else {
                    isLast = true;
                }
            }

            if (isLast && currentPlayer.equalsIgnoreCase(game.getAllPlayers().get(game.getNum()-1))) {
                turnState = TurnState.END;
                //Show scores

            }

            //New turn player
            nextPlayer();

            //For end the loop
            turnState = TurnState.END;
        }
    }

    /**
     * sets the next current player to play the turn
     */
    private void nextPlayer() {
        int currentIndex = nicknames.indexOf(currentPlayer);
        if(currentIndex + 1 < game.getCurrentNum()) {
            currentIndex = currentIndex + 1;
        }
        else {
            currentIndex = 0;
        }
        notifyOtherPlayers(currentPlayer + " has finished his turn!", currentPlayer);
        VirtualView virtualView = virtualViewMap.get(currentPlayer);
        virtualView.showGenericMessage("You have finished your turn!");

        currentPlayer = nicknames.get(currentIndex);
        currentBookshelf = game.getPlayerByNickname(currentPlayer).getBookshelf();
        notifyOtherPlayers("It's "+ currentPlayer + " turn!", currentPlayer);
        virtualView = virtualViewMap.get(currentPlayer);
        virtualView.showGenericMessage("It's you turn " + currentPlayer + "!");
    }

    /**
     * selects the first player to play the turn (he has the "seat")
     */
    private void selectFirstPlayer() {
        //notify del turn
        if(turnState == TurnState.START) {
            VirtualView virtualView = virtualViewMap.get(currentPlayer);
            currentBookshelf = game.getPlayerByNickname(currentPlayer).getBookshelf();
            virtualView.showGenericMessage("You're the first player to play! You have the seat");
            notifyOtherPlayers("It's " + currentPlayer + " turn!", currentPlayer);
            isStarted = true;
        }
        turnState = TurnState.SELECT;
    }

    /**
     * shows the current board situation
     */
    private void showCurrentBoard() {
        for (VirtualView virtualView : virtualViewMap.values()) {
            virtualView.showBoard(game.getBoard());
        }
    }

    /**
     * phase of selecting the tiles from the board
     */
    private void selectTiles() {
        if(turnState == TurnState.SELECT) {
            VirtualView virtualView = virtualViewMap.get(currentPlayer);
            notifyOtherPlayers(currentPlayer + " is selecting the tiles from the board...", currentPlayer);

            virtualView.askSelectTiles(game.getBoard());
            waitAnswer();

            notifyOtherPlayers("Player " + currentPlayer + " has selected: ", currentPlayer);
            virtualView.showGenericMessage("You have selected: ");
            for (VirtualView v : virtualViewMap.values()) {
                for (int i = 0; i < currentTiles.size(); i++) {
                    int index = i + 1;
                    v.showGenericMessage("* Tile " + index + " : ROW -> " + currentTiles.get(i).getX() + ", COLUMN -> " + currentTiles.get(i).getY() + ", TYPE -> " + currentTiles.get(i).getType());
                }
            }
            turnState = TurnState.REMOVE;
            removeTilesFromBoard(currentTiles);
        }
    }

    /**
     * use in selecting tiles phase for remove the tiles from the board
     * @param tiles to be removed from the board
     */
    private void removeTilesFromBoard(List<Tile> tiles) {
        if(turnState == TurnState.REMOVE) {
            game.getBoard().removeTiles(tiles);
        }
        turnState = TurnState.INSERT;
    }

    /**
     * shows the bookshelf of the current player that insert the tile
     */
    private void showCurrentBookshelf(){
        for(VirtualView virtualView : virtualViewMap.values()) {
                virtualView.showBookshelf(game.getPlayerByNickname(currentPlayer));
        }
    }

    /**
     * shows for each player all the bookshelf of the player in the game
     */
    private void showAllBookshelf(){
        for (String nick : nicknames) {
            VirtualView virtualView = virtualViewMap.get(nick);
            for(String n : nicknames) {
                virtualView.showBookshelf(game.getPlayerByNickname(n));
            }
        }
    }

    /**
     * phase of inserting the tiles in the bookshelf
     */
    private void insertTiles() {
        if(turnState == TurnState.INSERT) {
            VirtualView virtualView = virtualViewMap.get(currentPlayer);
            notifyOtherPlayers(currentPlayer + " is inserting the tiles in his bookshelf...", currentPlayer);

            virtualView.askInsertTiles(currentBookshelf, currentTiles);
            waitAnswer();

            notifyOtherPlayers("Player " + currentPlayer + " has inserted the tiles in the column number: " + currentPosition, currentPlayer);

            turnState = TurnState.ORDER;
            selectOrder();
        }
    }

    /**
     * phase of selecting the order of the tiles in the bookshelf
     */
    private void selectOrder() {
        if (turnState == TurnState.ORDER) {
            VirtualView virtualView = virtualViewMap.get(currentPlayer);
            virtualView.askOrderTiles(currentTiles);
            waitAnswer();

            virtualView.showGenericMessage("Tiles are inserting in the bookshelf in the column number " + currentPosition);
            game.getPlayerByNickname(currentPlayer).getBookshelf().insertTile(currentTiles, currentPosition);
        }
        turnState = TurnState.CHECK;
    }

    private void checkCommonCards() {
        if(turnState == TurnState.CHECK) {
            //Check common goal cards
            VirtualView virtualView = virtualViewMap.get(currentPlayer);
            int score =game.checkCommonGoalCards(game.getPlayerByNickname(currentPlayer));
            if(score!= 0)
            {
                if(game.getPlayerByNickname(currentPlayer).isDoneFirstCommon()){
                    virtualView.showCommonGoalComplete1( commonGoalCards.get(0), score);
                    notifyOtherPlayers(currentPlayer+"has completed the common Goal Card 1", currentPlayer);
                }
                else if (game.getPlayerByNickname(currentPlayer).isDoneSecondCommon()){
                    virtualView.showCommonGoalComplete1(commonGoalCards.get(1), score);
                    notifyOtherPlayers(currentPlayer+"has completed the common Goal Card 2", currentPlayer);
                }
                else {
                    virtualView.showGenericMessage("Errore su check Common Goal Card");
                }
            }
        }
    }

    /**
     * notify to all the player that someone complete is bookshelf
     */
    public void notifyBookshelfFull (){
        VirtualView virtualView = virtualViewMap.get(currentPlayer);
        virtualView.bookshelfFull();
        notifyOtherPlayers(currentPlayer+ "'s bookshelf is full", currentPlayer);
    }

    /**
     * waits the player's answer before going on with the game
     */
    private void waitAnswer() {
        while (!hasAnswered) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hasAnswered = false;
    }

    /**
     * sends a message to all the players except the current one
     * @param message to send
     * @param currentPlayer who is playing
     */
    public void notifyOtherPlayers(String message, String currentPlayer){
        for(String nickname : virtualViewMap.keySet()){
            if(!nickname.equals(currentPlayer)) {
                virtualViewMap.get(nickname).showGenericMessage(message);
            }
        }
    }


}
