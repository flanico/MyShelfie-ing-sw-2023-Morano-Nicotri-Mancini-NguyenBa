package it.polimi.ingsw.controller;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;
import it.polimi.ingsw.network.message.clientSide.ChatRequestMessage;
import it.polimi.ingsw.network.message.clientSide.NumPlayersReplyMessage;
import it.polimi.ingsw.network.message.clientSide.PositionReplyMessage;
import it.polimi.ingsw.network.message.clientSide.TilesReplyMessage;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.persistence.Persistence;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;
import it.polimi.ingsw.view.CLI.ColorCli;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;


/**
 * class that represents the controller of the game
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
    private transient Timer timer;
    private boolean isNumPlayersSet;
    private static final String INVALID_STATE = "Invalid game state";
    private static final String INPUT_ERR = ColorCli.RED + "Invalid Input! Please retry." + ColorCli.RESET;
    private static final String ERR_CLIENT = ColorCli.RED + "Client invalid message" + ColorCli.RESET;

    public GameController() {
        this.game = new Game();
        this.virtualViewMap = Collections.synchronizedMap(new HashMap<>());
        this.nicknames = new ArrayList<>();
        this.isNumPlayersSet = false;
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
            default -> Server.LOGGER.warning(INVALID_STATE);
        }
    }

    /**
     * message received in login state (while still creating the game and waiting the players to join)
     * @param message received
     */
    private void loginState(Message message) {
        if(message.getMessageType() == MessageType.NUM_PLAYERS_REP) {
            if(checkNumPlayers(message)) {
                game.setNum(((NumPlayersReplyMessage) message).getNumPlayers());
                isNumPlayersSet = true;
                game.addPlayer(nicknames.get(0), false);
                if(game.getCurrentNum() < game.getNum()) {
                    broadcastingMessage("Waiting other players...");
                }
            }
        }
        else {
            Server.LOGGER.warning(ERR_CLIENT);
        }
    }

    /**
     * checks if the number of players is valid
     * @param message received
     * @return true if the number of players is valid, false otherwise
     */
    private boolean checkNumPlayers(Message message) {
        NumPlayersReplyMessage numPlayersReplyMessage = (NumPlayersReplyMessage) message;
        if(numPlayersReplyMessage.getNumPlayers() >= 2 && numPlayersReplyMessage.getNumPlayers() <= 4) {
            return true;
        }
        else {
            VirtualView virtualView = virtualViewMap.get(message.getNickname());
            virtualView.askPlayersNumber();
            return false;
        }
    }

    /**
     * switch on in game message types (while the game is currently playing)
     * @param message received
     */
    private void inGameState(Message message) {
        switch (message.getMessageType()) {
            case CHAT_MESSAGE_REQ -> {
                //TODO: check if the destination is valid
                broadcastingChat(message);
            }
            case TILES_REPLY -> {
                if(checkTiles(message)) {
                    turnController.messageFromGameController(message);
                }
                else {
                    Server.LOGGER.warning(ERR_CLIENT);
                }
            }
            case POSITION_REPLY -> {
                if (checkPosition(message)) {
                    turnController.messageFromGameController(message);
                }
                else {
                    Server.LOGGER.warning(ERR_CLIENT);
                }
            }
            case ORDER_REPLY -> {
                turnController.messageFromGameController(message);
            }
            default -> Server.LOGGER.warning(INVALID_STATE);
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
     * checks the nickname
     * @param nickname the nickname to be checked
     * @param view the view of the player who is logging in
     * @return true is the nickname is valid, false otherwise
     */
    public boolean checkLoginNickname(String nickname, View view) {
        if(nickname.isEmpty() || game.isNicknameTaken(nickname)) {
            view.showLoginResult(false, true, nickname);
            return false;
        }
        return true;
    }

    /**
     * checks the nickname of the reconnected client
     * @param nickname the nickname to be checked of reconnected client
     * @param view the view of the reconnected player who is logging in
     * @return true is the nickname is valid, false otherwise
     */
    public boolean checkLoginNicknameReconnect(String nickname, View view) {
        if(nickname.isEmpty() || !nicknames.contains(nickname) || turnController.getNicknames().contains(nickname)) {
            view.showLoginResult(false, true, nickname);
            return false;
        }
        return true;
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
                    broadcastingMessage(ColorCli.CYAN_BOLD + "\nServer went down, the game is restoring!" + ColorCli.RESET);
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
            broadcastingMessage(ColorCli.CYAN_BOLD+ "\n" + nickname + " is reconnected to the game!" + ColorCli.RESET);
            addVirtualView(nickname, virtualView);
            virtualView.showGenericMessage(ColorCli.CYAN_BOLD + "\nYou are reconnected to the game!" + ColorCli.RESET);
            int index = nicknames.indexOf(nickname);
            turnController.getNicknames().add(index, nickname);
            game.getPlayerByNickname(nickname).setDisconnected(false);
            game.addPlayer(nickname, true);
            replacePlayer(nickname);
            onDisconnectGame(true, nickname);
        }
    }

    /**
     * checks if the tiles are removable from the board
     * @param message received
     * @return true if the tiles are removable, false otherwise
     */
    protected boolean checkTiles(Message message) {
        TilesReplyMessage tilesReplyMessage = (TilesReplyMessage) message;
        VirtualView virtualView = virtualViewMap.get(tilesReplyMessage.getNickname());
        Bookshelf bookshelfOfTheRoundPlayer = game.getPlayerByNickname(tilesReplyMessage.getNickname()).getBookshelf();
        if(game.getBoard().isRemovable(tilesReplyMessage.getTiles())) {
            return true;
        }
        else {
            virtualView.showGenericMessage(INPUT_ERR + ColorCli.RED + "Tiles selected are NOT removable from the board!" + ColorCli.RESET);
            virtualView.askSelectTiles(game.getBoard(), bookshelfOfTheRoundPlayer);
            return false;
        }
    }

    /**
     * checks if the position column in the bookshelf is valid
     * @param message received
     * @return true if the position is valid, false otherwise
     */
    protected boolean checkPosition(Message message) {
        PositionReplyMessage positionReplyMessage = (PositionReplyMessage) message;
        VirtualView virtualView = virtualViewMap.get(positionReplyMessage.getNickname());
        if(positionReplyMessage.getColumn() >= 0 && positionReplyMessage.getColumn() <= 4 &&
                game.getPlayerByNickname(positionReplyMessage.getNickname()).getBookshelf().isInsertableTile(positionReplyMessage.getTiles(), positionReplyMessage.getColumn())) {
            return true;
        }
        else {
            virtualView.showGenericMessage(INPUT_ERR);
            virtualView.askInsertTiles(game.getPlayerByNickname(positionReplyMessage.getNickname()).getBookshelf(), positionReplyMessage.getTiles());
            return false;
        }
    }

    /**
     * adds a player virtual view to the controller
     * @param nickname of the player to add
     * @param virtualView of the player to add
     */
    void addVirtualView(String nickname, VirtualView virtualView) {
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
        //If there is only one player connected stops the game
        if (virtualViewMap.size() == 1) {
            VirtualView vv = virtualViewMap.entrySet().iterator().next().getValue();
            vv.showGenericMessage("\nYou're the only player connected... If no one try to reconnect, game will finish. " +
                    "\nTimer of tot seconds starts now! If the timer expires you're are the winner of the game!");
            onDisconnectGame(false, nickname);
        }
        //If there are two or three players connected continue the game
        else {
            broadcastingMessage("\nThe game round is: " + turnController.getNicknames());
//            turnController.setDisconnected(true);
            turnController.nextPlayer(index, true);
            Thread threadTurnManager = new Thread(() -> turnController.turnManager());
            threadTurnManager.start();
        }
    }

    /**
     * starts of the game with the initialization of the settings
     */
    private void startGame() {
        game.initGame(game.getNum());
        broadcastingMessage(ColorCli.YELLOW_BOLD+"\n\nGame is starting, all players are connected!" + ColorCli.RESET);
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
        //If in the game there is only one player, a timer is setting
        if (!isReconnected) {
            turnController.setDisconnected(true);
            timer = new Timer();
            //If the timer expires, it ends the game with current player victory
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    VirtualView vv = virtualViewMap.entrySet().iterator().next().getValue();
                    vv.showWinner(virtualViewMap.entrySet().iterator().next().getKey());
                        endGame();
                        System.exit(0);
                    }
            }, 30000); //30 seconds timer
        }
        //If someone reconnects to the game, the timer is cancelled
        if (isReconnected && timer != null) {
            turnController.setDisconnected(false);
            timer.cancel();
            broadcastingMessage("\nThe game round is: " + nicknames);
            //it is the turn of the player connected
            if(gameState == GameState.DISCONNECTED) {
//                System.out.println("Caso del player che sta giocando");
                Thread threadTurnManager = new Thread(() -> turnController.turnManager());
                threadTurnManager.start();
                gameState = GameState.IN_GAME;
            }
            //it is the turn of the player who has reconnected
            else {
                int index = turnController.getNicknames().indexOf(nickname);
                turnController.nextPlayer(index, true);
                Thread threadTurnManager = new Thread(() -> turnController.turnManager());
                threadTurnManager.start();
            }
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
        //If is in login phase, delete all settings
        else {
            nicknames.clear();
            virtualViewMap.clear();
            game.clear();
        }
    }

    /**
     * send a chat message from the server to all players in the game
     * @param message to send
     */
    private void broadcastingChat(Message message){
        String destination;
        destination = ((ChatRequestMessage) message).getDestination();
        for(VirtualView v : virtualViewMap.values()){
                v.addChatMessage(((ChatRequestMessage) message).getSender(), destination, ((ChatRequestMessage) message).getMessage());
            }
    }

    /**
     * send a message contains generic game information to all players in the game
     * @param message to send
     */
    private void broadcastingMessage(String message) {
        for (VirtualView v : virtualViewMap.values()) {
            v.showGenericMessage(message);
        }
    }

    /**
     * send a message to all connected players to notify a disconnection of a player
     * @param nickname of the disconnected player
     * @param isStarted true if the game is already started, false otherwise
     */
    public void broadcastingDisconnection(String nickname, boolean isStarted){
        for(VirtualView v : virtualViewMap.values()){
            v.showDisconnection(nickname, isStarted);
        }
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
            p.setScore(gameController.game.getPlayerByNickname(p.getNickname()).getScore());
            p.setDoneFirstCommon(gameController.game.getPlayerByNickname(p.getNickname()).isDoneFirstCommon());
            p.setDoneSecondCommon(gameController.game.getPlayerByNickname(p.getNickname()).isDoneSecondCommon());
            p.setBookshelf(gameController.game.getPlayerByNickname(p.getNickname()).getBookshelf());
            p.setPersonalGoalCard(gameController.game.getPlayerByNickname(p.getNickname()).getPersonalGoalCard());
        }

        this.game.replaceGame(players, num, board, commonGoalCards, commonGoalCardScores, bag, playerScore);
        this.gameState = gameController.gameState;
        this.turnController = gameController.turnController;
        if(this.turnController != null) {
            this.turnController.setGame(this.game);
            this.turnController.setVirtualViewMap(this.virtualViewMap);
            Thread threadTurnManager = new Thread(() -> turnController.turnManager());
            threadTurnManager.start();
        }
    }

    public TurnController getTurnController() {
        return turnController;
    }

    Map<String, VirtualView> getVirtualViewMap() {
        return virtualViewMap;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean isNumPlayersSet() {
        return isNumPlayersSet;
    }

    public List<String> getNicknames() {
        return nicknames;
    }
}
