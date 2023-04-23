package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.Message;

/**
 * interface used to handle clients: the RMIHandler and SocketClientHandler will implement it
 * send message from client to server and received the return value
 */
public interface ClientHandler {

    /**
     * sends a message from the server to the client
     * @param message to be sent
     */
    void sendMessageToClient(Message message);

    /**
     * disconnect the client from the server
     */
    void disconnect();
}
