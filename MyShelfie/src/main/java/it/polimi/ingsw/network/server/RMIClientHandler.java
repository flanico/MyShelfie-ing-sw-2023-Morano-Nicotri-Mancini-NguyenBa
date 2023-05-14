package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIClientHandler extends UnicastRemoteObject implements RMIInterface, ClientHandler {
    private final Server server;
    private Client client = null;
    private Message message = null;

    protected RMIClientHandler(Server server) throws RemoteException {
        this.server = server;
    }

    public void sendMessageToServer(Message message, Client client) throws RemoteException {
        this.client = client;
        if (message != null && message.getMessageType() != MessageType.PING) {
            if (message.getMessageType() == MessageType.LOGIN_REQ) {
                Server.LOGGER.info(() -> "Message LoginRequest received from " + message.getNickname() + ": " + message);
                this.server.addClient(message.getNickname(), this);
            }
            else {
                Server.LOGGER.info(() -> "Message received from: " + message.getNickname() + ": " + message);
                this.server.forwardsMessage(message);
            }
        }
    }

    public Message takeMessage() throws RemoteException {
        return this.message;
    }

    public void sendMessageToClient(Message message) {
        this.message = message;
        this.client.readMessage();
    }

    public void disconnectClient() {}
}
