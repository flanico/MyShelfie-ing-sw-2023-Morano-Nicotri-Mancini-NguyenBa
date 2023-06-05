package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.clientSide.OrderReplyMessage;
import it.polimi.ingsw.network.message.clientSide.PositionReplyMessage;
import it.polimi.ingsw.network.message.clientSide.TilesReplyMessage;
import it.polimi.ingsw.persistence.Persistence;
import it.polimi.ingsw.view.VirtualView;
import it.polimi.ingsw.view.CLI.ColorCli;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * class that manages turn in the game
 */
public class TurnController implements Serializable {
    @Serial
    private static final long serialVersionUID = -1731938445482154456L;
    private Game game;
    private TurnState turnState;
    private final GameController gameController;
    private transient Map<String, VirtualView> virtualViewMap;
    private final List<String> nicknames;
    private String currentPlayer;
    private boolean hasReplied = false;
    private int currentPosition;
    private List<Tile> currentTiles;
    private Bookshelf currentBookshelf;
    private List<CommonGoalCard> commonGoalCards;
    private Board currentBoard;
    private boolean isStarted;
    private boolean isLast;
    private boolean isPlayerFinished;
    private boolean isDisconnected;

    /**
     * constructor of the turn controller
     * @param game the current game
     * @param gameController the game controller
     * @param virtualViewMap the virtual view of all the clients
     */
    protected TurnController(Game game, GameController gameController, Map<String, VirtualView> virtualViewMap) {
        this.game = game;
        this.gameController = gameController;
        this.virtualViewMap = virtualViewMap;
        this.nicknames = new ArrayList<>(game.getAllPlayers());
        this.currentPlayer = nicknames.get(0);
        this.turnState = TurnState.START;
        this.isStarted = false;
        this.isLast = false;
        this.isPlayerFinished = false;
        this.commonGoalCards = new ArrayList<>(game.getCommongoalcards());
        this.currentBoard = game.getBoard();
        this.isDisconnected = false;
    }

    /**
     * handles all messages received from the game controller
     * @param message received
     */
    protected void messageFromGameController(Message message) {
        switch (message.getMessageType()) {
            case TILES_REPLY -> {
                TilesReplyMessage tilesReplyMessage = (TilesReplyMessage) message;
                currentTiles = tilesReplyMessage.getTiles();
                hasReplied = true;
            }
            case POSITION_REPLY -> {
                PositionReplyMessage positionReplyMessage = (PositionReplyMessage) message;
                currentPosition = positionReplyMessage.getColumn();
                currentTiles = positionReplyMessage.getTiles();
                hasReplied = true;
            }
            case ORDER_REPLY -> {
                OrderReplyMessage orderReplyMessage = (OrderReplyMessage) message;
                currentTiles = orderReplyMessage.getTiles();
                hasReplied = true;
            }
        }
    }

    /**
     * main method that handles all the phases of the turn
     */
    protected void turnManager() {
        while (turnState != TurnState.END) {
            //If is the first turn, select the first player and give him the seat
            if (!isStarted) {
                selectFirstPlayer();
            }
            //At the begging of each turn check if the board needs to refill
            if (currentBoard.isRefillable()) {
                for (VirtualView virtualView : virtualViewMap.values()) {
                    virtualView.showGenericMessage("Board is refilling...");
                }
                currentBoard.fillBoard(game.getBag());
            }
            //Shows common goal cards and their scores
            showCommon();
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

            VirtualView virtualView = virtualViewMap.get(currentPlayer);
            notifyOtherPlayers("Player " + currentPlayer + " has selected: ", currentPlayer);
            virtualView.showGenericMessage("You have selected: ");
            for (VirtualView v : virtualViewMap.values()) {
                for (int i = 0; i < currentTiles.size(); i++) {
                    int index = i + 1;
                    v.showGenericMessage("* Tile " + index + " : ROW -> " + currentTiles.get(i).getX() + ", COLUMN -> " + currentTiles.get(i).getY() + ", TYPE -> " + currentTiles.get(i).toString());
                }
            }
            //Shows the current player bookshelf
            virtualView.showBookshelf(game.getPlayerByNickname(currentPlayer));
            //Inserts the tiles in the bookshelf
            insertTiles();
            //Shows bookshelf of the current player
            showCurrentBookshelf();
            //Check common goal cards
            checkCommonCards();

            //Check bookshelf full, if true the game is finish or last round
            if (currentBookshelf.isFull()) {
                addOneFinalPoint();
                //Case: current player is the one that finishes the game
                if (currentPlayer.equalsIgnoreCase(nicknames.get(nicknames.size() - 1))) {
                    turnState = TurnState.END;
                    //Shows scores
                    scoresPersonal();
                    scoresAdjacentCells();
                    endGame();
                }
                //Case: last round game
                else {
                    isLast = true;
                }
            }
            else if (isLast && currentPlayer.equalsIgnoreCase(nicknames.get(nicknames.size() - 1))) {
                turnState = TurnState.END;
                //Show scores
                scoresPersonal();
                scoresAdjacentCells();
                endGame();
            }
            if (isDisconnected) {
                gameController.setGameState(GameState.DISCONNECTED);
//                System.out.println("Break caso giocatore che gioca");
                break;
            }
            //At the end of each turn save the new info in the file disk
            if (turnState != TurnState.END) {
                Persistence persistence = new Persistence(gameController);
                persistence.storeGame(gameController);
                nextPlayer(nicknames.indexOf(currentPlayer), false);
            }
        }
    }

    /**
     * sets the next current player to play the turn
     */
    protected void nextPlayer(int index, boolean isDisconnected) {
        turnState = TurnState.SELECT;
        if (isDisconnected) {
            index--;
        }
        if (index + 1 < nicknames.size()) {
            index = index + 1;
        }
        else {
            index = 0;
        }
        currentPlayer = nicknames.get(index);
        currentBookshelf = game.getPlayerByNickname(currentPlayer).getBookshelf();
        notifyOtherPlayers(ColorCli.YELLOW_BOLD + "It's " + currentPlayer + " turn!" + ColorCli.RESET, currentPlayer);
        VirtualView virtualView = virtualViewMap.get(currentPlayer);
        virtualView.showGenericMessage(ColorCli.YELLOW_BOLD + "It's your turn " + currentPlayer + "!" + ColorCli.RESET);
    }

    /**
     * selects the first player to play the turn (he has the "seat")
     */
    private void selectFirstPlayer() {
        if(turnState == TurnState.START) {
            VirtualView virtualView = virtualViewMap.get(currentPlayer);
            currentBookshelf = game.getPlayerByNickname(currentPlayer).getBookshelf();
            virtualView.showGenericMessage(ColorCli.YELLOW_BOLD + "You're the first player to play! You have the seat" + ColorCli.RESET);
            notifyOtherPlayers(ColorCli.YELLOW_BOLD + "It's " + currentPlayer + " turn!" + ColorCli.RESET, currentPlayer);
            for (String nick : nicknames.stream().filter(n -> !n.equalsIgnoreCase(currentPlayer)).toList()) {
                VirtualView v = virtualViewMap.get(nick);
                v.showPersonalCard(game.getPlayerByNickname(nick));
            }
            isStarted = true;
        }
        turnState = TurnState.SELECT;
    }

    /**
     * shows the current board situation to all players
     */
    private void showCurrentBoard() {
        for (VirtualView virtualView : virtualViewMap.values()) {
            virtualView.showBoard(currentBoard);
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
     * show the common goal cards and the common goal scores to all players
     */
    private void showCommon() {
        for (VirtualView v : virtualViewMap.values()) {
            v.showCommonCards(game.getCommongoalcards());
            v.showCommonScores(game.getCommongoalcardscores());
        }
    }

    /**
     * phase of selecting the tiles from the board
     */
    private void selectTiles() {
        if(turnState == TurnState.SELECT) {
            VirtualView virtualView = virtualViewMap.get(currentPlayer);
            notifyOtherPlayers("\n" + currentPlayer + " is selecting the tiles from the board...", currentPlayer);
            notifyOtherPlayers(ColorCli.PINK + "\nIf you want to: \n-start a chat write -chat destination message \n-see the chat write -show_chat"+ ColorCli.RESET, currentPlayer);
            virtualView.askSelectTiles(currentBoard, currentBookshelf);
            waitAnswer();
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
            currentBoard.removeTiles(tiles);
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
            notifyOtherPlayers("\n" + currentPlayer + " is inserting the tiles in his bookshelf...", currentPlayer);

            virtualView.askInsertTiles(currentBookshelf, currentTiles);
            waitAnswer();

            notifyOtherPlayers(currentPlayer + " has inserted the tiles in the column number: " + currentPosition, currentPlayer);

            turnState = TurnState.ORDER;
            //If the selected tiles have the same type skip select order
            if(currentTiles.stream().map(Tile::getType).distinct().count() == 1) {
                game.getPlayerByNickname(currentPlayer).getBookshelf().insertTile(currentTiles, currentPosition);
                turnState = TurnState.CHECK;
            }
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
//            virtualView.showGenericMessage("Tiles are inserting in the bookshelf in the column number " + currentPosition);
            game.getPlayerByNickname(currentPlayer).getBookshelf().insertTile(currentTiles, currentPosition);
        }
        turnState = TurnState.CHECK;
    }

    /**
     * phase of checking the common goal cards
     */
    private void checkCommonCards() {
        if(turnState == TurnState.CHECK) {
            VirtualView virtualView = virtualViewMap.get(currentPlayer);
            int score = game.checkCommonGoalCards(game.getPlayerByNickname(currentPlayer));
            if(score!= 0)
            {
                if(game.getPlayerByNickname(currentPlayer).isDoneFirstCommon()){
                    virtualView.showCommonGoalComplete(commonGoalCards.get(0), score);
                    notifyOtherPlayers(ColorCli.YELLOW_BOLD + "\n" + currentPlayer+" has completed the common Goal Card 1!" + ColorCli.RESET, currentPlayer);
                    for (VirtualView v : virtualViewMap.values()) {
                        v.showCommonScores(game.getCommongoalcardscores());
                    }
                }
                else if (game.getPlayerByNickname(currentPlayer).isDoneSecondCommon()){
                    virtualView.showCommonGoalComplete(commonGoalCards.get(1), score);
                    notifyOtherPlayers(ColorCli.YELLOW_BOLD + "\n" + currentPlayer+" has completed the common Goal Card 2!" +ColorCli.RESET, currentPlayer);
                    for (VirtualView v : virtualViewMap.values()) {
                        v.showCommonScores(game.getCommongoalcardscores());
                    }
                }
                else {
                    virtualView.showError("Error checking common goal cards");
                }
            }
        }
    }

    /**
     * assigns the end game token to the score of the player who has firstly completed the bookshelf
     */
    private void addOneFinalPoint() {
        if(!isPlayerFinished) {
            isPlayerFinished = true;
            notifyBookshelfFull();
            Player player = game.getPlayerByNickname(currentPlayer);
            VirtualView virtualView = virtualViewMap.get(currentPlayer);
            player.setScore(player.getScore() + 1);
            virtualView.showGenericMessage(currentPlayer + " you have the end game token! (+1 point)");
            notifyOtherPlayers(currentPlayer + " has the end game token! (+1 point)", currentPlayer);
        }
    }

    /**
     * calculate for all players the score made with the Personal Goal Card
     */
    private void scoresPersonal() {
        for (Player p : game.getPlayers()) {
            int score = p.getPersonalGoalCard().check(p);
            p.setScore(p.getScore() + score);
            if(!p.isDisconnected()) {
                VirtualView virtualView = virtualViewMap.get(p.getNickname());
                virtualView.showGenericMessage("You have made " + score + " points with the Personal Goal Card!");
            }
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
            if(!p.isDisconnected()) {
                VirtualView virtualView = virtualViewMap.get(p.getNickname());
                virtualView.showGenericMessage("You have made " + score + " points with the adjacent item tiles!");
            }
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
        String winner = null;
        if(iterator.hasNext()){
            winner = iterator.next();
        }
        //Shows the rank scores and the winner to all players
        for (VirtualView v : virtualViewMap.values()) {
            v.showScores(game.getPlayerScore());
            v.showWinner(winner);
        }
        gameController.endGame();
    }

    /**
     * notify to all the player that someone has completed his bookshelf
     */
    private void notifyBookshelfFull() {
        VirtualView virtualView = virtualViewMap.get(currentPlayer);
        virtualView.showGenericMessage(ColorCli.YELLOW_BOLD + "You have completed the bookshelf!" + ColorCli.RESET);
        notifyOtherPlayers(ColorCli.YELLOW_BOLD + currentPlayer+ "'s bookshelf is full!" + ColorCli.RESET, currentPlayer);
    }

    protected void setVirtualViewMap(Map<String, VirtualView> virtualViewMap) {
        this.virtualViewMap = virtualViewMap;
    }

    protected void setGame(Game game) {
        this.game = game;
    }

    public List<String> getNicknames() {
        return nicknames;
    }

    protected Map<String, VirtualView> getVirtualViewMap() {
        return virtualViewMap;
    }

    public void setDisconnected(boolean disconnected) {
        isDisconnected = disconnected;
    }

    /**
     * waits the player's answer before going on with the game
     */
    private void waitAnswer() {
        while (!hasReplied) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hasReplied = false;
    }

    /**
     * sends a message to all the players except the current one
     * @param message to send
     * @param currentPlayer who is playing
     */
    private void notifyOtherPlayers(String message, String currentPlayer){
        for(String nickname : virtualViewMap.keySet()){
            if(!nickname.equals(currentPlayer)) {
                virtualViewMap.get(nickname).showGenericMessage(message);
            }
        }
    }
}
