package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.RMIClient;
import it.polimi.ingsw.network.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends  Remote {
    void sendMessageToServer(Message message, RMIInterface skeleton) throws RemoteException;

    void sendMessageToClient(Message message) throws RemoteException;

    void setClient (RMIClient client);
}
