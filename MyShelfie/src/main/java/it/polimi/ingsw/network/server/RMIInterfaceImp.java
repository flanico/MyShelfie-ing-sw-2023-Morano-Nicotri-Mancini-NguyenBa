package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIInterfaceImp extends UnicastRemoteObject implements RMIInterface {
    private final Server server;

    protected RMIInterfaceImp(Server server) throws RemoteException {
        this.server = server;
    }

    public void onMessage(Message message) throws RemoteException {
        System.out.println("aw");
    }
}
