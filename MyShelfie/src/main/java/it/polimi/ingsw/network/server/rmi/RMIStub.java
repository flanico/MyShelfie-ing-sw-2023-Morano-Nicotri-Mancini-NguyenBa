package it.polimi.ingsw.network.server.rmi;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;
import it.polimi.ingsw.network.server.ClientHandler;
import it.polimi.ingsw.network.server.Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * class that implements the remote interface, to communicate from the client to the server
 * @author Alessandro Mancini
 */
public class RMIStub extends UnicastRemoteObject implements RMIInterface {
    private final Server server;

    /**
     * constructor of RMIStub
     * @param server connected
     * @author Alessandro Mancini
     */
    protected RMIStub(Server server) throws RemoteException {
        this.server = server;
    }

    /**
     * method to send messages from client to server
     * @param message to send to server
     * @param skeleton remote interface passed from client to server for back-calling
     * @author Alessandro Mancini
     */
    @Override
    public void toServer(Message message, RMIInterface skeleton) throws RemoteException {
        if (message != null && message.getMessageType() != MessageType.PING) {
            if (message.getMessageType() == MessageType.LOGIN_REQ) {
                ClientHandler handler = new RMIClientHandler(skeleton, this.server);
                Server.LOGGER.info(() -> "Login from " + message.getNickname() + ": " + message);
                this.server.addClient(message.getNickname(), handler);
            }
            else {
                Server.LOGGER.info(() -> "Message from: " + message.getNickname() + ": " + message);
                this.server.forwardsMessage(message);
            }
        }
    }

    /**
     * method declared because of the RMIInterface implementation, but not useful for RMIStub
     * @param message to send to server
     * @author Alessandro Mancini
     */
    @Override
    public void toClient(Message message) throws RemoteException {}

    /**
     * method to disconnect the client, used in RMI Skeleton
     * @author Alessandro Mancini
     */
    @Override
    public void disconnect() throws RemoteException {}
}
