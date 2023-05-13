package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.RemoteInterface;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends UnicastRemoteObject implements RemoteInterface, Runnable {
    protected final Server server;
    private final int port;

    public RMIServer(int port, Server server) throws RemoteException {
        this.port = port;
        this.server = server;
    }

    public String sayHello() throws RemoteException{
        return ("HELLO");
    }

    @Override
    public void run() {
        try {
            Registry registry = LocateRegistry.createRegistry(this.port);
            registry.bind("rmi server", this);
            System.out.println("Server is ready");
        } catch (RemoteException | AlreadyBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
