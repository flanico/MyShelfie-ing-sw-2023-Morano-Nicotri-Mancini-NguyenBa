package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.message.Message;

public class RMIClientHandler implements ClientHandler, Runnable {
    private final Server server;
    private final Client client;

    public RMIClientHandler(Server server, Client client) {
        this.server = server;
        this.client = client;
    }

    @Override
    public void run() {

    }

    /**
     * sends a message from the server to the client
     * @param message to be sent
     */
    public void sendMessageToClient(Message message){}

    /**
     * disconnect the client from the server
     */
    public void disconnectClient(){}
}
