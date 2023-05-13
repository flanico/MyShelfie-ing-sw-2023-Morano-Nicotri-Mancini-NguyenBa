package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.Message;

import java.rmi.Remote;

/**
 * interface used to handle clients: the RMIClientHandler and SocketClientHandler will implement it
 * send message from client to server and received the return value
 */
public interface ClientHandler extends Remote {

    /**
     * sends a message from the server to the client
     * @param message to be sent
     */
    void sendMessageToClient(Message message);

    /**
     * disconnect the client from the server
     */
    void disconnectClient();
}
