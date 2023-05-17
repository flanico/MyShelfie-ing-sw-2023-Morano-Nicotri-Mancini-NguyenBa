package it.polimi.ingsw.controller;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;
import it.polimi.ingsw.network.message.clientSide.NumPlayersReplyMessage;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.persistence.Persistence;
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
    private transient Map<String, VirtualView> virtualViewMap;
    private final List<String> nicknames; //All nicknames also disconnected players
    private TurnController turnController;
    private InputController inputController;
    private transient Timer timer;
    private static final String STR_INVALID_STATE = "Invalid game state";

    /**
     * constructor of the game controller
     */
    public GameController() {
        this.game = new Game();
        this.virtualViewMap = Collections.synchronizedMap(new HashMap<>());
        this.inputController = new InputController(this, virtualViewMap);
        this.nicknames = new ArrayList<>();
        gameState = GameState.LOGIN;
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
                game.addPlayer(nicknames.get(0), false);
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
     * checks the nickname of the reconnected client through the InputController
     * @param nickname the nickname to be checked of reconnected client
     * @param view the view of the reconnected player who is logging in
     * @return true is the nickname is valid, false otherwise
     */
    public boolean checkLoginNicknameReconnect(String nickname, View view) {
        return inputController.checkReconnectNickname(nickname, view);
    }

    public List<String> getNicknames() {
        return nicknames;
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
            addVirtualView(nickname, virtualView);
            nicknames.add(nickname);
            virtualView.showLoginResult(true, true, "SERVER");
            virtualView.askPlayersNumber();
        }
        //If the player is not the first player to connect to the game, he joins in the game
        else if (virtualViewMap.size() < game.getNum() && !isGameStarted()) {
            addVirtualView(nickname, virtualView);
            game.addPlayer(nickname, false);
            nicknames.add(nickname);
            virtualView.showLoginResult(true, true, "SERVER");
            broadcastingMessage("Waiting other players...");
            //If all players are connect to the game the match starts
            if(game.getCurrentNum() == game.getNum()) {
                Persistence persistence = new Persistence(this);
                GameController gameControllerPrevious = persistence.restoreGame();
                //Game restoring: checks if the nicknames of the players are the same of the previous game
                if(gameControllerPrevious != null && new HashSet<>(gameControllerPrevious.nicknames).containsAll(game.getAllPlayers())) {
                    broadcastingMessage("\nServer went down, the game is restoring!");
                    replace(gameControllerPrevious);
                }
                //New game: starts a new game
                else {
                    gameState = GameState.IN_GAME;
                    startGame();
                }
            }
        }
        //If the player is reconnecting to the game
        else if (isGameStarted()) {
            broadcastingMessage("\n" + nickname + " is reconnected to the game!");
            addVirtualView(nickname, virtualView);
            virtualView.showGenericMessage("\nYou are reconnected to the game!");
            int index = nicknames.indexOf(nickname);
            turnController.getNicknames().add(index, nickname);
            game.getPlayerByNickname(nickname).setDisconnected(false);
            game.addPlayer(nickname, true);
            replacePlayer(nickname);
            onDisconnectGame(true, nickname);
        }
    }

    /**
     * adds a player virtual view to the controller
     * @param nickname of the player to add
     * @param virtualView of the player to add
     */
    private void addVirtualView(String nickname, VirtualView virtualView) {
        virtualViewMap.put(nickname, virtualView);
        game.addObserver(virtualView);
    }

    /**
     * remove a player virtual view from the controller
     * @param nickname of the player to remove
     */
    public void removeVirtualView(String nickname) {
        VirtualView virtualView = virtualViewMap.remove(nickname);
        //Shows the nickname of the disconnected player
        broadcastingDisconnection(nickname, true);
        game.getPlayerByNickname(nickname).setDisconnected(true);
        game.removeObserver(virtualView);
        game.disconnectionOfPlayer();
        int index = turnController.getNicknames().indexOf(nickname);
        turnController.getNicknames().remove(nickname);
        turnController.getVirtualViewMap().remove(nickname);
        //If there is only one player connected stops the game
        if (virtualViewMap.size() == 1) {
            VirtualView vv = virtualViewMap.entrySet().iterator().next().getValue();
            vv.showGenericMessage("\nYou're the only player connected... If no one try to reconnect, game will finish. " +
                    "\nTimer of tot seconds starts now! If the timer expires you're are the winner of the game!");
            onDisconnectGame(false, nickname);
        }
        //If there are two or three player connected continue the game
        else {
            broadcastingMessage("\nThe game round is: " + turnController.getNicknames());
//            turnController.nextPlayer(index, true);
        }
    }

    /**
     * starts of the game with the initialization of the settings
     */
    private void startGame() {
        game.initGame(game.getNum());
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
        //At the beginning of the match save the new info in the file disk
        Persistence persistence = new Persistence(this);
        persistence.storeGame(this);
        //Shows the selected game round
        broadcastingMessage("\nThe game round is: " + nicknames);
        this.turnController = new TurnController(game, this, virtualViewMap);
        Thread threadTurnManager = new Thread(() -> turnController.turnManager());
        threadTurnManager.start();
    }

    /**
     * set a timer if only one player is in the game, or cancel the timer if someone reconnect to the game
     * @param isReconnected true if someone is reconnected to the game, false otherwise
     */
    private void onDisconnectGame(boolean isReconnected, String nickname) {
        VirtualView vv = virtualViewMap.entrySet().iterator().next().getValue();
        //If in the game there is only one player, a timer is setting
        if (!isReconnected) {
            timer = new Timer();
            //If the timer expires, it ends the game with current player victory
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                        vv.showWinner(virtualViewMap.entrySet().iterator().next().getKey());
                        endGame();
                        System.exit(0);
                    }
            }, 30000); //30 seconds timer
        }
        //If someone reconnect to the game, the timer is cancelled
        if (isReconnected && timer != null) {
            timer.cancel();
            int index = turnController.getNicknames().indexOf(nickname);
            Thread threadTurnManager = new Thread(() -> turnController.turnManager());
            threadTurnManager.start();
//            turnController.nextPlayer(index, true);
        }
    }

    /**
     * delete the file game of the finished current game at the end
     */
    public void endGame() {
        if(isGameStarted()) {
            Persistence persistence = new Persistence(this);
            persistence.deleteGame();
        }
        //If is login phase, delete all settings
        else {
            nicknames.clear();
            virtualViewMap.clear();
            game.clear();
        }
    }

    /**
     * sends a message contains generic game information to all players in the game
     * @param message to send
     */
    private void broadcastingMessage(String message) {
        for (VirtualView v : virtualViewMap.values()) {
            v.showGenericMessage(message);
        }
    }

    /**
     * sends a message to all connected players to notify a disconnection of a player
     * @param nickname of the disconnected player
     * @param isStarted true if the game is already started, false otherwise
     */
    public void broadcastingDisconnection(String nickname, boolean isStarted){
        for(VirtualView v : virtualViewMap.values()){
            v.disconnection(nickname, isStarted);
        }
    }

    public TurnController getTurnController() {
        return turnController;
    }

    /**
     * replace the settings of a disconnected player
     * @param nickname of the reconnected player
     */
    private void replacePlayer(String nickname) {
        Player player = game.getPlayerByNickname(nickname);
        player.setScore(game.getPlayerByNickname(nickname).getScore());
        player.setPersonalGoalCard(game.getPlayerByNickname(nickname).getPersonalGoalCard());
        player.setBookshelf(game.getPlayerByNickname(nickname).getBookshelf());
        player.setDoneFirstCommon(game.getPlayerByNickname(nickname).isDoneFirstCommon());
        player.setDoneSecondCommon(game.getPlayerByNickname(nickname).isDoneSecondCommon());
    }

    /**
     * replace the game controller if the game is restored
     * @param gameController game controller of the restored game
     */
    private void replace(GameController gameController) {
        List<Player> players = gameController.game.getPlayers();
        int num = gameController.game.getNum();
        Board board = gameController.game.getBoard();
        List<CommonGoalCard> commonGoalCards = gameController.game.getCommongoalcards();
        List<CommonGoalCardScore> commonGoalCardScores = gameController.game.getCommongoalcardscores();
        Stack<Tile> bag = gameController.game.getBag();
        Map<String, Integer> playerScore = gameController.game.getPlayerScore();
        for (Player p : players) {
            p.setBookshelf(gameController.game.getPlayerByNickname(p.getNickname()).getBookshelf());
            p.setPersonalGoalCard(gameController.game.getPlayerByNickname(p.getNickname()).getPersonalGoalCard());
        }

        this.game.replaceGame(players, num, board, commonGoalCards, commonGoalCardScores, bag, playerScore);
        this.gameState = gameController.gameState;
        this.turnController = gameController.turnController;
        this.turnController.setGame(this.game);
        this.turnController.setVirtualViewMap(this.virtualViewMap);
        this.inputController = new InputController(this, this.virtualViewMap);
        Thread threadTurnManager = new Thread(() -> turnController.turnManager());
        threadTurnManager.start();
    }
}
