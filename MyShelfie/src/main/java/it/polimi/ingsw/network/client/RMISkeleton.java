package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.server.RMIInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMISkeleton extends UnicastRemoteObject implements RMIInterface {
    private RMIClient client;

    public RMISkeleton(RMIClient client) throws RemoteException {
        this.client = client;
    }

    @Override
    public void toServer(Message message, RMIInterface skeleton) throws RemoteException {}

    @Override
    public void toClient(Message message) throws RemoteException {
        this.client.readMessage(message);
    }

    @Override
    public void disconnectRMI() {
        this.client.disconnect();
    }
}
