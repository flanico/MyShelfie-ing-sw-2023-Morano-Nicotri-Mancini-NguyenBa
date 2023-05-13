package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIImplementation extends UnicastRemoteObject implements RMIInterface {
    private final Server server;
    protected RMIImplementation(Server server) throws RemoteException {
        this.server = server;
    }

    public void sendMessageToClient(Message message) throws RemoteException {}

    public void disconnectClient() throws RemoteException {}
}
