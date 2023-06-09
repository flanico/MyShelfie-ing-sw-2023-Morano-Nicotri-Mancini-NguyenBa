package it.polimi.ingsw.controller;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.clientSide.*;
import it.polimi.ingsw.network.server.ClientHandler;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    private GameController gameController;
    private ClientHandler clientHandler;
    private Server server;
    private String player1 = "Chiara";
    private String player2 = "Ste";
    private String player3 = "Fla";

    @BeforeEach
    void setUp() {
        gameController = new GameController();
        clientHandler = new ClientHandler() {
            @Override
            public void sendMessageToClient(Message message) {

            }

            @Override
            public void disconnectClient() {

            }
        };
        server = new Server(gameController);
        setup(player1, player2, player3);
    }

    @AfterEach
    void tearDown() {
        gameController.endGame();
        gameController = null;
        clientHandler = null;
    }

    private void setup(String player1, String player2, String player3) {
        server.addClient(player1, clientHandler);
        NumPlayersReplyMessage playerNumberReply = new NumPlayersReplyMessage(player1, 3);
        gameController.onMessageReceived(playerNumberReply);
        server.addClient(player2, clientHandler);
        server.addClient(player3, clientHandler);
    }

    @Test
    void gameTest() {
        assertEquals(3, gameController.getVirtualViewMap().size());
        assertEquals(3, gameController.getNicknames().size());
        assertEquals(3, gameController.getGame().getPlayers().size());
        assertNotNull(gameController.getGame().getBoard());
    }

    @Test
    void reconnectionTest() {
        server.removeClient(player3, true);
        server.addClient(player3, clientHandler);
        assertEquals(3, gameController.getVirtualViewMap().size());
    }

    @Test
    void addVirtualView() {
        VirtualView virtualView = new VirtualView(clientHandler);
        gameController.addVirtualView("nickname", virtualView);
        assertEquals(virtualView, gameController.getVirtualViewMap().get("nickname"));
    }

    @Test
    void removeVirtualView() {
        gameController.addVirtualView(player2, new VirtualView(clientHandler));
        gameController.removeVirtualView(player2);
        assertNull(gameController.getVirtualViewMap().get(player2));
    }

    @Test
    void endGame() {
        gameController.setGameState(GameState.LOGIN);
        gameController.endGame();
        assertEquals(0, gameController.getVirtualViewMap().size());
        assertEquals(0, gameController.getNicknames().size());
    }

    @Test
    void isGameStarted() {
        assertTrue(gameController.isGameStarted());
    }

    @Test
    void setGameState() {
        gameController.setGameState(GameState.LOGIN);
        assertFalse(gameController.isGameStarted());
    }
}