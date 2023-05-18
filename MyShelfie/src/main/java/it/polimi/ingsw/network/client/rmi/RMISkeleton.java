package it.polimi.ingsw.network.client.rmi;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.server.rmi.RMIInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * class that implements the remote interface, to communicate from the server to the client
 * @author Alessandro Mancini
 */
public class RMISkeleton extends UnicastRemoteObject implements RMIInterface {
    private final RMIClient client;

    /**
     * constructor of RMISkeleton
     * @author Alessandro Mancini
     */
    public RMISkeleton(RMIClient client) throws RemoteException {
        this.client = client;
    }

    /**
     * method declared because of the RMIInterface implementation, but not useful for RMISkeleton
     * @param message to send to server
     * @param skeleton remote interface passed from client to server for back-calling
     * @author Alessandro Mancini
     */
    @Override
    public void toServer(Message message, RMIInterface skeleton) throws RemoteException {}

    /**
     * method to send messages from server to client
     * @param message to send to server
     * @author Alessandro Mancini
     */
    @Override
    public void toClient(Message message) throws RemoteException {
        this.client.readMessage(message);
    }

    /**
     * method to disconnect the client from the RMI network
     * @author Alessandro Mancini
     */
    @Override
    public void disconnectRMI() {
        this.client.disconnect();
    }
}
