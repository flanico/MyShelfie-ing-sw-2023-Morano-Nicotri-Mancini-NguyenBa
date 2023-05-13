package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends  Remote {
    /**
     * sends a message from the server to the client
     * @param message to be sent
     */
    void sendMessageToClient(Message message) throws RemoteException;

    /**
     * disconnect the client from the server
     */
    void disconnectClient() throws RemoteException;
}
