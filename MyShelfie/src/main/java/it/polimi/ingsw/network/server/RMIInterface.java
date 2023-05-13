package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.RMIClient;
import it.polimi.ingsw.network.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends  Remote {
    void onMessage(Message message, RMIClient client) throws RemoteException;

}
