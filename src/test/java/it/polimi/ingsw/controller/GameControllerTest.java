package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.clientSide.*;
import it.polimi.ingsw.network.server.ClientHandler;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    private GameController gameController;
    private ClientHandler clientHandler;
    private Server server;
    private final String player1 = "Chiara";
    private final String player2 = "Ste";
    private final String player3 = "Fla";

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
        assertEquals(3,gameController.getTurnController().getNicknames().size());
        assertEquals("Chiara", gameController.getTurnController().getCurrentPlayer());
    }

    @Test
    void reconnectionTest() {
        server.removeClient(player3, true);
        server.addClient(player3, clientHandler);
        assertEquals(3, gameController.getVirtualViewMap().size());
    }

    @Test
    void checkLoginNicknameReconnectTest() {
        assertFalse(gameController.checkLoginNicknameReconnect(player1, new VirtualView(clientHandler)));
        server.removeClient(player1, true);
        assertTrue(gameController.checkLoginNicknameReconnect(player1, new VirtualView(clientHandler)));
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
        assertEquals(2, gameController.getVirtualViewMap().size());
        assertEquals(3, gameController.getNicknames().size());
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
    void isDisconnected() {
        assertFalse(gameController.isDisconnected());
    }

    @Test
    void setGameState() {
        gameController.setGameState(GameState.LOGIN);
        assertFalse(gameController.isGameStarted());
    }

    @Test
    void checkLoginNicknameTest() {
        assertFalse(gameController.checkLoginNickname("Ste", new VirtualView(clientHandler)));
        assertFalse(gameController.checkLoginNickname("", new VirtualView(clientHandler)));
    }

    @Test
    void tilesReplyTest() {
        List<Tile> tiles = new ArrayList<>();
        Board board = gameController.getGame().getBoard();
        tiles.add(board.getMatrix()[4][1]);
        TilesReplyMessage tilesReplyMessage = new TilesReplyMessage(player1, tiles);
        gameController.onMessageReceived(tilesReplyMessage);
        assertEquals(1, tilesReplyMessage.getTiles().size());
        assertEquals(board.getMatrix()[4][1], tilesReplyMessage.getTiles().get(0));
        tiles.clear();
        tiles.add(board.getMatrix()[4][1]);
        tiles.add(board.getMatrix()[4][2]);
        TilesReplyMessage tilesReplyMessage1 = new TilesReplyMessage(player1, tiles);
        gameController.onMessageReceived(tilesReplyMessage1);
        assertEquals(2, tilesReplyMessage1.getTiles().size());
        assertEquals(board.getMatrix()[4][1], tilesReplyMessage1.getTiles().get(0));
        assertEquals(board.getMatrix()[4][2], tilesReplyMessage1.getTiles().get(1));
    }

    @Test
    void positionReplyTest() {
        List<Tile> tiles = new ArrayList<>();
        Board board = gameController.getGame().getBoard();
        tiles.add(board.getMatrix()[4][1]);
        PositionReplyMessage positionReplyMessage = new PositionReplyMessage(player1, 4, tiles);
        gameController.onMessageReceived(positionReplyMessage);
        assertEquals(4, positionReplyMessage.getColumn());
        assertEquals(1, positionReplyMessage.getTiles().size());
        assertEquals(board.getMatrix()[4][1], positionReplyMessage.getTiles().get(0));
        PositionReplyMessage positionReplyMessage1 = new PositionReplyMessage(player1, 9, tiles);
        gameController.onMessageReceived(positionReplyMessage1);
        assertEquals(9, positionReplyMessage1.getColumn());
        assertEquals(1, positionReplyMessage1.getTiles().size());
    }

    @Test
    void orderReplyTest() {
        List<Tile> tiles = new ArrayList<>();
        Board board = gameController.getGame().getBoard();
        tiles.add(board.getMatrix()[5][0]);
        tiles.add(board.getMatrix()[5][1]);
        OrderReplyMessage orderReplyMessage = new OrderReplyMessage(player1, tiles);
        gameController.onMessageReceived(orderReplyMessage);
        assertEquals(tiles, orderReplyMessage.getTiles());
    }

    @Test
    void chatRequestTest() {
        ChatRequestMessage chatRequestMessage = new ChatRequestMessage(player1, player2, "ciao");
        gameController.onMessageReceived(chatRequestMessage);
        assertEquals(player1, chatRequestMessage.getSender());
        assertEquals(player2, chatRequestMessage.getDestination());
        assertEquals("ciao", chatRequestMessage.getMessage());
    }

}