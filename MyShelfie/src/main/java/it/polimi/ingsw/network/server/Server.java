package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.view.VirtualView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 *  class that represents the server
 */
public class Server {
    public static final Logger LOGGER =  Logger.getLogger(Server.class.getName());
    private final Map<String, ClientHandler> clientHandlerMap;
    private final GameController gameController;
    private final Object lock;

    public Server(GameController gameController) {
        this.gameController = gameController;
        this.clientHandlerMap = Collections.synchronizedMap(new HashMap<>());
        this.lock = new Object();
    }

    /**
     * adds a client to be managed by the socket server
     * @param nickname of the added client
     * @param clientHandler the client handler associated with the client
     */
    public void addClient(String nickname, ClientHandler clientHandler) {
        VirtualView virtualView = new VirtualView(clientHandler);
        if(!gameController.isGameStarted()) {
            if(gameController.checkLoginNickname(nickname, virtualView)) {
                clientHandlerMap.put(nickname, clientHandler);
                gameController.loginHandler(nickname, virtualView);
            }
        }
        //Case of reconnected client
        else if (gameController.isGameStarted() && gameController.getGame().getPlayers().size() < gameController.getGame().getNum()) {
            if(gameController.checkLoginNicknameReconnect(nickname, virtualView)) {
                clientHandlerMap.put(nickname, clientHandler);
                gameController.loginHandler(nickname, virtualView);
                LOGGER.info(() -> "A player is reconnected to the game");
            }
        }
        //Case of already started game to review!
        else {
            virtualView.showLoginResult(false, null);
            LOGGER.info(() -> "Game is already started");
            clientHandler.disconnectClient();
        }
    }

    /**
     * removes a client given his nickname
     * @param nickname of the client to remove
     * @param isStarted true if the game is already started, false otherwise
     */
    public void removeClient(String nickname, boolean isStarted) {
        if(isStarted) {
            clientHandlerMap.remove(nickname);
            gameController.removeVirtualView(nickname);
            LOGGER.info(() -> "Removed " + nickname + " from the list of connected players");
        }
        else {
            clientHandlerMap.clear();
        }
    }

    /**
     * forwards a message received from the client to the game controller
     * @param message to be forwarded
     */
    public void forwardsMessage(Message message) {
        gameController.onMessageReceived(message);
    }

    /**
     * disconnects a client from the server
     * @param clientHandler the client handler to be disconnected
     */
    public void onDisconnect(ClientHandler clientHandler) {
        synchronized (lock) {
            String nickname = getNicknameFromClientHandler(clientHandler);
            if(nickname != null) {
                //If is login phase ends the game
                if(!gameController.isGameStarted()) {
                    removeClient(nickname, false);
                    gameController.broadcastingDisconnection(nickname, false);
                    gameController.endGame();
                    LOGGER.warning(() -> "Game is finished");
                }
                //If is in game phase continue for the disconnection resilience
                else {
                    removeClient(nickname, true);
                    clientHandlerMap.remove(nickname);
                    LOGGER.info(() -> "Game continue for resilience");
                }
            }
        }
    }

    /**
     * returns the corresponding nickname of a ClientHandler
     * @param clientHandler the client handler
     * @return the corresponding nickname of a ClientHandler
     */
    private String getNicknameFromClientHandler(ClientHandler clientHandler) {
        return clientHandlerMap.entrySet()
                .stream()
                .filter(entry -> clientHandler.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
}
