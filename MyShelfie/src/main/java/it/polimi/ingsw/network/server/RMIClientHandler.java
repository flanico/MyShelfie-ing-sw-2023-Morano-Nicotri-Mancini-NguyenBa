package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIClientHandler extends UnicastRemoteObject implements RMIInterface, ClientHandler {
    private final Server server;
    private Message currentMessage = null;
    private boolean read = false;

    protected RMIClientHandler(Server server) throws RemoteException {
        this.server = server;
    }

    public void sendMessageToServer(Message message) throws RemoteException {
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
        this.read = false;
        return this.currentMessage;
    }

    public void sendMessageToClient(Message message) {
        this.currentMessage = message;
        this.read = true;
    }

    public void disconnectClient() {}

    public Boolean isReadable() {
        return this.read;
    }
}
