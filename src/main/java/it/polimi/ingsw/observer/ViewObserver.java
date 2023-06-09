package it.polimi.ingsw.observer;

import it.polimi.ingsw.model.Tile;

import java.util.List;

/**
 * interface implemented by the observer ClientController class on the view classes
 * @see ViewObservable
 */
public interface ViewObserver {

    /**
     * creates a new connection to the server
     * @param ip the ip address
     * @param port the port number
     * @param type the type of connection
     */
    void createConnection(String ip, String port, int type);

    /**
     * sends a message to the server with the chosen nickname
     * @param nickname chosen by the client
     */
    void sendNickname(String nickname);

    /**
     * sends a message to the server with the players number chosen
     * @param numPlayers the number of players
     */
    void sendNumPlayers(int numPlayers);

    /**
     * sends a message to the server with the selected tiles
     * @param tiles selected by the client
     */
    void sendSelectTiles(List<Tile> tiles);

    /**
     * sends a message to the server with the selected tiles and the selected column of the bookshelf
     * @param column selected by the client
     * @param tiles selected by the client
     */
    void sendInsertTiles(int column, List<Tile> tiles);

    /**
     * sends a message to the server with the selected order of the tiles
     * @param tiles selected by the client
     */
    void sendOrderTiles(List<Tile> tiles);

    /**
     * sends a message to the server with the chat request
     * @param destination the destination player of the message
     * @param message the message sent from the sender player
     */
    void sendChatMessage(String destination, String message);
}
