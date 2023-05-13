package it.polimi.ingsw.network.server;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends UnicastRemoteObject implements Runnable {
    protected final Server server;
    private final int port;

    public RMIServer(int port, Server server) throws RemoteException {
        this.port = port;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            Registry registry = LocateRegistry.createRegistry(this.port);
            registry.bind("server", (Remote)this);
            System.out.println("Server is ready");
        } catch (RemoteException | AlreadyBoundException e) {
            throw new RuntimeException(e);
        }


    }
}
