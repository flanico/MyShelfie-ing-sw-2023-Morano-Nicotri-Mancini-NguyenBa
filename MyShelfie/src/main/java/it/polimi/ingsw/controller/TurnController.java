package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.message.Message;
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
    private boolean isStarted;
    private boolean isSelectValid;

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
        this.isSelectValid = false;
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
                hasAnswered = true;
            }
            case GENERIC -> {
                GenericMessage genericMessage = (GenericMessage) message;
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
        }
    }

    /**
     * @return the nickname of the current player
     */
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * sets the next current player to play the turn
     */
    public void nextPlayer() {
        int currentIndex = nicknames.indexOf(currentPlayer);
        if(currentIndex + 1 < game.getCurrentNum()) {
            currentIndex = currentIndex + 1;
        }
        else {
            currentIndex = 0;
        }
        currentPlayer = nicknames.get(currentIndex);
    }

    /**
     * selects the first player to play the turn (he has the "seat")
     */
    private void selectFirstPlayer() {
        //notify del turn
        if(turnState == TurnState.START) {
            VirtualView virtualView = virtualViewMap.get(getCurrentPlayer());
            virtualView.showGenericMessage("You're the first player to play! You have the seat");
            notifyOtherPlayers("It's " + getCurrentPlayer() + " turn!", getCurrentPlayer());
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

    private void selectTiles() {
        if(turnState == TurnState.SELECT) {
            VirtualView virtualView = virtualViewMap.get(getCurrentPlayer());
            virtualView.showGenericMessage("Hey " + getCurrentPlayer() + " you have to select the tiles from the board!");
            notifyOtherPlayers(getCurrentPlayer() + " is selecting the tiles from the board...", getCurrentPlayer());

            while (!isSelectValid) {
                virtualView.askSelectTiles(game.getBoard());
                waitAnswer();

                if(game.getBoard().isRemovable(currentTiles)) {
                    isSelectValid = true;
                    virtualView.showGenericMessage("Tiles selected are removable from the board!");

                    notifyOtherPlayers("Player " + getCurrentPlayer() + " has selected: ", getCurrentPlayer());
                    for (VirtualView v : virtualViewMap.values()) {
                        for (int i = 0; i < currentTiles.size(); i++) {
                            int index = i + 1;
                            v.showGenericMessage("Tile " + index + " : ROW -> " + currentTiles.get(i).getX() + ", COLUMN -> " + currentTiles.get(i).getY() + ", TYPE -> " + currentTiles.get(i).getType());
                        }
                    }
                    turnState = TurnState.REMOVE;
                    removeTilesFromBoard(currentTiles);
                }
                else {
                    virtualView.showGenericMessage("Sorry, tiles selected are NOT removable from the board!");
                }
            }
        }
    }

    private void removeTilesFromBoard(List<Tile> tiles) {
        if(turnState == TurnState.REMOVE) {
            game.getBoard().removeTiles(tiles);
        }
        //For loop
        turnState = TurnState.END;
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
