package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.Message;

/**
 * interface used to handle clients
 */
public interface ClientHandler {

    /**
     * sends a message to the client
     * @param message to be sent
     */
    void sendMessageToClient(Message message);

    /**
     * disconnect the client
     */
    void disconnect();
}
