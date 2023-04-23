package it.polimi.ingsw.observer;

import it.polimi.ingsw.model.Tile;

public interface ViewObserver {
    /**
     * creates a new connection to the server
     * @param ip the ip address
     * @param port the port number
     */
    void createConnection(String ip, String port);

    /**
     * sends a message to the server with the chosen nickname
     * @param nickname chosen
     */
    void sendNickname(String nickname);

    /**
     * sends a message to the server with the players number chosen
     * @param numPlayers the number of players
     */
    void sendNumPlayers(int numPlayers);
}
