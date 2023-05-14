package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends  Remote {
    void sendMessageToServer(Message message, Client client) throws RemoteException;

    Message takeMessage() throws RemoteException;
}
