package it.polimi.ingsw.network.server.rmi;

import it.polimi.ingsw.network.server.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer implements Runnable {
    private final Registry registry;
    private final RMIStub remote;

    public RMIServer(int port, Server server) throws RemoteException {
        this.registry = LocateRegistry.createRegistry(port);
        this.remote = new RMIStub(server);
    }

    @Override
    public void run() {
        try {
            this.registry.bind("SERVER", remote);
            Server.LOGGER.info(() -> "The RMI server started");
        } catch (RemoteException | AlreadyBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
