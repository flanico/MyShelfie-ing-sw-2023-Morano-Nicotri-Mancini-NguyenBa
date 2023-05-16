package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIInterfaceImp extends UnicastRemoteObject implements RMIInterface {
    private final Server server;

    protected RMIInterfaceImp(Server server) throws RemoteException {
        this.server = server;
    }

    @Override
    public void toServer(Message message, RMIInterface skeleton) throws RemoteException {
        if (message != null && message.getMessageType() != MessageType.PING) {
            if (message.getMessageType() == MessageType.LOGIN_REQ) {
                ClientHandler handler = new RMIClientHandler(skeleton);
                Server.LOGGER.info(() -> "Login from " + message.getNickname() + ": " + message);
                this.server.addClient(message.getNickname(), handler);
            }
            else {
                Server.LOGGER.info(() -> "Message from: " + message.getNickname() + ": " + message);
                this.server.forwardsMessage(message);
            }
        }
    }

    @Override
    public void toClient(Message message) throws RemoteException {}

    @Override
    public void disconnectRMI() throws RemoteException {}
}
