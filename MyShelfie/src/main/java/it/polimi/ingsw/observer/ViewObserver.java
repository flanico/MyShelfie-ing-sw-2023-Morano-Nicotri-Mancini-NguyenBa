package it.polimi.ingsw.observer;

import it.polimi.ingsw.model.Tile;

import java.util.List;

public interface ViewObserver {

    /**
     * creates a new connection to the server
     * @param ip the ip address
     * @param port the port number
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
}
