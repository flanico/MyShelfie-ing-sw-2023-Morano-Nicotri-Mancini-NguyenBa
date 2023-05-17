package it.polimi.ingsw.network.server.rmi;

import it.polimi.ingsw.network.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends  Remote {
    void toServer(Message message, RMIInterface skeleton) throws RemoteException;

    void toClient(Message message) throws RemoteException;

    void disconnectRMI() throws RemoteException;
}
