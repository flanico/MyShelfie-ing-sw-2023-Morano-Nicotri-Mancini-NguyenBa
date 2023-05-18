package it.polimi.ingsw.network.server.rmi;

import it.polimi.ingsw.network.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * remote interface used by RMIStub and RMISkeleton to handle the RMI connection
 * @author Alessandro Mancini
 */
public interface RMIInterface extends  Remote {

    /**
     * method to send messages from client to server
     * @param message to send to the server
     * @param skeleton remote interface passed from client to server for back-calling
     * @author Alessandro Mancini
     */
    void toServer(Message message, RMIInterface skeleton) throws RemoteException;

    /**
     * method to send messages from server to client
     * @param message to send to the client
     * @author Alessandro Mancini
     */
    void toClient(Message message) throws RemoteException;

    /**
     * method to disconnect the client from the RMI network
     * @author Alessandro Mancini
     */
    void disconnectRMI() throws RemoteException;
}
