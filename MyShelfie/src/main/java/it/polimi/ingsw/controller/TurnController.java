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
import java.util.*;

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
    private boolean isPlayerFinish;

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
        this.isPlayerFinish = false;
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

        while (turnState != TurnState.END) {
            //If is the first turn, select the first player and give him the seat
            if (!isStarted) {
                selectFirstPlayer();
            }

            //At the begging of each turn check if the board needs to refill
            if (game.getBoard().isRefillable()) {
                for (VirtualView virtualView : virtualViewMap.values()) {
                    virtualView.showGenericMessage("Board is refilling...");
                }
                game.getBoard().fillBoard(game.getBag());
            }

            //Shows the situation board
            showCurrentBoard();

            //Shows personal card
            showCurrentPersonalCard();

            //Shows bookshelf of the current player
            showCurrentBookshelf();

            //Selects the tiles from the board
            selectTiles();

            //Shows the situation board
            showCurrentBoard();

            //Shows the current player bookshelf
            VirtualView virtualView = virtualViewMap.get(currentPlayer);
            virtualView.showBookshelf(game.getPlayerByNickname(currentPlayer));

            //Inserts the tiles in the bookshelf
            insertTiles();

            //Shows bookshelf of the current player
            showCurrentBookshelf();

            //Check common goal cards
            checkCommonCards();

            //Check bookshelf full
            //Game is finish or last round
            if (currentBookshelf.isFull()) {
                //Final point +1
                addOneFinalPoint();
                //Case: current player is the one that finishes the game
                notifyBookshelfFull();
                if (currentPlayer.equalsIgnoreCase(game.getAllPlayers().get(game.getNum() - 1))) {
                    turnState = TurnState.END;
                    //Shows scores
                    //Personal
                    scoresPersonal();
                    //Adjacent
                    scoresAdjacentCells();
                    //Total
                    endGame();
                }
                //Case: last round game
                else {
                    isLast = true;
                }
            }

            if (isLast && currentPlayer.equalsIgnoreCase(game.getAllPlayers().get(game.getNum() - 1))) {
                turnState = TurnState.END;
                //Show scores
                //Personal
                scoresPersonal();
                //Adjacent
                scoresAdjacentCells();
                //Total
                endGame();
            }

            //New turn player
            if (turnState != TurnState.END) nextPlayer();

            //For end the loop
            //turnState = TurnState.END;
        }
    }

    /**
     * sets the next current player to play the turn
     */
    private void nextPlayer() {
        turnState = TurnState.SELECT;
        int currentIndex = nicknames.indexOf(currentPlayer);
        if(currentIndex + 1 < game.getCurrentNum()) {
            currentIndex = currentIndex + 1;
        }
        else {
            currentIndex = 0;
        }
        VirtualView virtualView = virtualViewMap.get(currentPlayer);
        currentPlayer = nicknames.get(currentIndex);
        currentBookshelf = game.getPlayerByNickname(currentPlayer).getBookshelf();
        notifyOtherPlayers("It's "+ currentPlayer + " turn!", currentPlayer);
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
     * shows the current board situation to all players
     */
    private void showCurrentBoard() {
        for (VirtualView virtualView : virtualViewMap.values()) {
            virtualView.showBoard(game.getBoard());
        }
    }

    /**
     * shows the current personal goal card to the current player
     */
    private void showCurrentPersonalCard() {
        VirtualView virtualView = virtualViewMap.get(currentPlayer);
        virtualView.showPersonalCard(game.getPlayerByNickname(currentPlayer));
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
     * shows the bookshelf of the current player to all players
     */
    private void showCurrentBookshelf(){
        for(VirtualView virtualView : virtualViewMap.values()) {
                virtualView.showBookshelf(game.getPlayerByNickname(currentPlayer));
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

    /**
     * phase of checking the common goal cards
     */
    private void checkCommonCards() {
        if(turnState == TurnState.CHECK) {
            //Check common goal cards
            VirtualView virtualView = virtualViewMap.get(currentPlayer);
            int score = game.checkCommonGoalCards(game.getPlayerByNickname(currentPlayer));
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
     * assigns the end game token to the score of the player who has firstly completed the bookshelf
     */
    private void addOneFinalPoint() {
        if(!isPlayerFinish) {
            isPlayerFinish = true;
            Player player = game.getPlayerByNickname(currentPlayer);
            VirtualView virtualView = virtualViewMap.get(currentPlayer);
            player.setScore(player.getScore() + 1);
            virtualView.showGenericMessage(currentPlayer + " you have the end game token! (+1 point)");
            notifyOtherPlayers(currentPlayer + " have the end game token! (+1 point)", currentPlayer);
        }
    }

    /**
     * calculate for all players the score made with the Personal Goal Card
     */
    private void scoresPersonal() {
        for (Player p : game.getPlayers()) {
            int score = p.getPersonalGoalCard().check(p);
            p.setScore(p.getScore() + score);
            VirtualView virtualView = virtualViewMap.get(p.getNickname());
            virtualView.showGenericMessage("You have made " + score + " points with the Personal Goal Card!");
            notifyOtherPlayers(p.getNickname() + " has made " + score + " points with the Personal Goal Card!", p.getNickname());
        }
    }

    /**
     * calculate for all players the score made with the Adjacent Cells
     */
    private void scoresAdjacentCells() {
        for (Player p : game.getPlayers()) {
            int score = p.getBookshelf().adjacentCells();
            p.setScore(p.getScore() + score);
            VirtualView virtualView = virtualViewMap.get(p.getNickname());
            virtualView.showGenericMessage("You have made " + score + " points with the adjacent item tiles!");
            notifyOtherPlayers(p.getNickname() + " has made " + score + " points with the adjacent item tiles!", p.getNickname());
        }
    }

    /**
     * shows the rank scores of the players
     */
    private void endGame() {
        game.setRankingScore();
        turnState = TurnState.END;

        //Find the winner
        Iterator<String> iterator = game.getPlayerScore().keySet().iterator();
        String winner = "No one";
        if(iterator.hasNext()){
            winner = iterator.next();
        }

        //Shows the rank scores to all players
        for (VirtualView v : virtualViewMap.values()) {
            v.showScores(game.getPlayerScore());
        }

        //Notify the winner
        VirtualView virtualView = virtualViewMap.get(winner);
        virtualView.showWinner(winner);

        //Notify losers
        notifyOtherPlayers("Player " + winner + " has won the match!", winner);
    }

    /**
     * notify to all the player that someone has completed his bookshelf
     */
    public void notifyBookshelfFull (){
        VirtualView virtualView = virtualViewMap.get(currentPlayer);
        virtualView.bookshelfFull();
        notifyOtherPlayers(currentPlayer+ "'s bookshelf is full!", currentPlayer);
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
