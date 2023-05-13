package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends  Remote {
    void login(Client client) throws RemoteException;
}
