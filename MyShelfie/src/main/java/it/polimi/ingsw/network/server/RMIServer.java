package it.polimi.ingsw.network.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer implements Runnable {
    protected final Server server;
    private final int port;

    public RMIServer(int port, Server server) {
        this.port = port;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            RMIClientHandler remote = new RMIClientHandler(this.server);
            Registry registry = LocateRegistry.createRegistry(this.port);
            registry.bind("rmiServer", remote);
            System.out.println("Server is ready");
        } catch (RemoteException | AlreadyBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
