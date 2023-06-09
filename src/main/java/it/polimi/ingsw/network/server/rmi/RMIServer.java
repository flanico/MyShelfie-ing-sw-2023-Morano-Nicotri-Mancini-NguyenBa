package it.polimi.ingsw.network.server.rmi;

import it.polimi.ingsw.network.server.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * class that implements the RMI server, as a thread
 * @author Alessandro Mancini
 */
public class RMIServer implements Runnable {
    private final Registry registry;
    private final RMIStub remote;

    /**
     * constructor for RMIServer
     * @param port of the connection
     * @param server that handle the RMI connection
     * @author Alessandro Mancini
     */
    public RMIServer(int port, Server server) throws RemoteException {
        this.registry = LocateRegistry.createRegistry(port);
        this.remote = new RMIStub(server);
    }

    /**
     * run method of the thread, that creates the registry bind
     * @author Alessandro Mancini
     */
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
