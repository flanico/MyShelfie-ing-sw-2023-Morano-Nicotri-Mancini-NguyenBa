package it.polimi.ingsw.controller;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameState;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;
import it.polimi.ingsw.network.message.clientSide.NumPlayersReplyMessage;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * class that represents the evolution of the game
 * @author Alessandro Mancini, Chiara Nguyen Ba
 */
public class GameController implements Serializable {
    @Serial
    private final static long serialVersionUID = 1523751286666277956L;
    private Game game;
    private GameState gameState;
    private Map<String, VirtualView> virtualViewMap;
    private final List<String> nicknames;
    private TurnController turnController;
    private InputController inputController;
    private static final String STR_INVALID_STATE = "Invalid game state";
    private boolean lockLogin;

    /**
     * constructor of the game controller
     */
    public GameController() {
        this.game = new Game();
        this.virtualViewMap = Collections.synchronizedMap(new HashMap<>());
        this.inputController = new InputController(this, virtualViewMap);
        this.nicknames = new ArrayList<>();
        this.lockLogin = false;
        setGameState(GameState.LOGIN);
    }

    /**
     * switch on game state based on the message received from the server
     * @param message from the server (from a particular client)
     */
    public void onMessageReceived(Message message) {
        switch (gameState) {
            case LOGIN -> loginState(message);
            case IN_GAME -> inGameState(message);
            default -> Server.LOGGER.warning(STR_INVALID_STATE);
        }
    }

    /**
     * message received in login state (while still creating the game and waiting the players to join)
     * @param message received
     */
    private void loginState(Message message) {
        if(message.getMessageType() == MessageType.NUM_PLAYERS_REP) {
            if(inputController.checkNumPlayers(message)) {
                game.setNum(((NumPlayersReplyMessage) message).getNumPlayers());
                if(game.getCurrentNum() < game.getNum()) {
                    broadcastingMessage("Waiting other players...");
                }
            }
        }
        else {
            Server.LOGGER.warning("Client sends a wrong message");
        }
    }

    /**
     * switch on in game message type (while the game is currently playing)
     * check always the correctness of the input by calling input controller's methods and then the turn controller
     * @param message received
     */
    private void inGameState(Message message) {
        switch (message.getMessageType()) {
            case TILES_REPLY -> {
                if(inputController.checkTiles(message)) {
                    turnController.messageFromGameController(message);
                }
                else {
                    Server.LOGGER.warning("The format of the message sent by the client is incorrect!");
                }
            }
            case POSITION_REPLY -> {
                if (inputController.checkPosition(message)) {
                    turnController.messageFromGameController(message);
                }
                else {
                    Server.LOGGER.warning("The format of the message sent by the client is incorrect!");
                }
            }
            case ORDER_REPLY -> {
                if(inputController.checkOrder(message)) {
                    turnController.messageFromGameController(message);
                }
                else {
                    Server.LOGGER.warning("The format of the message sent by the client is incorrect!");
                }
            }
            case GENERIC -> turnController.messageFromGameController(message);
            default -> Server.LOGGER.warning(STR_INVALID_STATE);
        }
    }

    public Game getGame() {
        return game;
    }

    /**
     * getter of the turn controller
     * @return the turn controller
     */
    public TurnController getTurnController() {
        return turnController;
    }

    /**
     * getter of the input controller
     * @return the input controller
     */
    public InputController getInputController() {
        return inputController;
    }

    /**
     * sets of the game state
     * @param gameState to be set
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * checks if the game is already started (no more players can connect) or not
     * @return true if the game is started, false otherwise
     */
    public boolean isGameStarted() {
        return this.gameState != GameState.LOGIN;
    }

    /**
     * checks the nickname through the InputController
     * @param nickname the nickname to be checked
     * @param view the view of the player who is logging in
     * @return true is the nickname is valid, false otherwise
     */
    public boolean checkLoginNickname(String nickname, View view) {
        return inputController.checkNickname(nickname, view);
    }

    /**
     * handles the login to the game when a new player joins to the game
     * if he's the first player it asks the max number of players
     * @param nickname of the client
     * @param virtualView of the client
     */
    public void loginHandler(String nickname, VirtualView virtualView) {
        //If the player is the first to connect he must choose the max number of players
        if(virtualViewMap.isEmpty()) {
            addVirtualViewMap(nickname, virtualView);
            game.addPlayer(nickname);
            nicknames.add(nickname);
            virtualView.showLoginResult(true, "SERVER");
            virtualView.askPlayersNumber();
        }
        //If the player is not the first player to connect to the game, he joins in the game
        else if (virtualViewMap.size() < game.getNum()) {
            addVirtualViewMap(nickname, virtualView);
            game.addPlayer(nickname);
            nicknames.add(nickname);
            virtualView.showLoginResult(true, "SERVER");
            broadcastingMessage("Waiting other players...");
            //If all players are connect to the game the match starts
            if(game.getCurrentNum() == game.getNum()) {
                startGame(game.getNum(), nicknames);
            }
        }
    }

    /**
     * adds a player virtual view to the controller
     * @param nickname of the player to add
     * @param virtualView of the player to add
     */
    public void addVirtualViewMap(String nickname, VirtualView virtualView) {
        virtualViewMap.put(nickname, virtualView);
        game.addObserver(virtualView);
    }

    /**
     * remove a player virtual view from the controller
     * @param nickname of the player to remove
     */
    public void removeVirtualView(String nickname, boolean notifyEnabled) {
        VirtualView virtualView = virtualViewMap.remove(nickname);

        game.removeObserver(virtualView);
        game.removePlayerByNickname(nickname, notifyEnabled);
    }

    /**
     * starts of the game with the initialization of the settings
     */
    private void startGame(int numPlayers, List<String> nicknames) {
        game.initGame(numPlayers);
        setGameState(GameState.IN_GAME);
        broadcastingMessage("\nGame is starting, all players are connected!");
        //Shows the two common goal cards selected for the match
        for (VirtualView v : virtualViewMap.values()) {
            v.showCommonCards(game.getCommongoalcards());
            v.showCommonScores(game.getCommongoalcardscores());
        }

        //Shows the personal goal card selected for the match
        for(String nick : nicknames) {
            if(!Objects.equals(nick, nicknames.get(0))) {
                VirtualView vv = virtualViewMap.get(nick);
                vv.showPersonalCard(game.getPlayerByNickname(nick));
            }
        }

        //Shows the selected game round
        broadcastingMessage("\nThe game round is: " + nicknames);

        this.turnController = new TurnController(game, this, virtualViewMap);
        Thread threadTurnManager = new Thread(() -> turnController.turnManager());
        threadTurnManager.start();
    }

    /**
     * sends a message contains generic game information to all players in the game
     * @param message to send
     */
    public void broadcastingMessage(String message) {
        for (VirtualView v : virtualViewMap.values()) {
            v.showGenericMessage(message);
        }
    }
}
