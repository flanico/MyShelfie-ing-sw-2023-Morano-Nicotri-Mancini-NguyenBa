package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.view.VirtualView;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TurnController implements Serializable {
    @Serial
    private static final long serialVersionUID = -1731938445482154456L;
    private final Game game;
    private final GameController gameController;
    private Map<String, VirtualView> virtualViewMap;
    private final List<String> nicknames;
    private String currentPlayer;

    /**
     * constructor of the turn controller
     * @param game current game
     * @param gameController game controller
     * @param virtualViewMap virtual view of all the clients
     */
    public TurnController(Game game, GameController gameController, Map<String, VirtualView> virtualViewMap) {
        this.game = game;
        this.gameController = gameController;
        this.virtualViewMap = virtualViewMap;
        this.nicknames = new ArrayList<>(game.getAllPlayers());
        this.currentPlayer = nicknames.get(0);
    }

    /**
     * @return the nickname of the current player
     */
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * sets the current player
     * @param currentPlayer nickname of the current player to be set
     */
    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * sets the next current player
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
     * @return a list of players' nicknames
     */
    public List<String> getNicknames() {
        return nicknames;
    }

}
