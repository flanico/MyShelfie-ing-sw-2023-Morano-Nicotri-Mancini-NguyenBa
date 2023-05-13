package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.message.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIImplementation extends UnicastRemoteObject implements RMIInterface {
    private final Server server;

    protected RMIImplementation(Server server) throws RemoteException {
        this.server = server;
    }

    public void login(Client client) throws RemoteException {
        RMIClientHandler rmiClientHandler = new RMIClientHandler(this.server, client);
        Thread rmiClientHandlerThread = new Thread(rmiClientHandler, "rmiclienthandler_");
        rmiClientHandlerThread.start();
    }
}
