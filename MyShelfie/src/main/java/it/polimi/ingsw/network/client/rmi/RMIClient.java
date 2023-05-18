package it.polimi.ingsw.network.client.rmi;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.server.rmi.RMIInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * class that represents an RMI client implementation
 */
public class RMIClient extends Client {
    private final RMIInterface remote;
    private final RMIInterface skeleton;
    private boolean connected;

    /**
     * constructor for the RMIClient
     * @param ip of the connection
     * @param port of the connection
     * @author Alessandro Mancini
     */
    public RMIClient(String ip, int port) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(ip, port);
        this.remote = (RMIInterface) registry.lookup("SERVER");
        this.skeleton = new RMISkeleton(this);
        this.connected = true;
    }

    /**
     * sends messages to server, using RMIStub method
     * @param message to send
     * @author Alessandro Mancini
     */
    @Override
    public void sendMessage(Message message) {
        if (connected) {
            try {
                remote.toServer(message, this.skeleton);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * function implemented because of the Client inherit, but not used in an RMI connection
     * @author Alessandro Mancini
     */
    @Override
    public void readMessage() {}

    /**
     * recives messages to read, from RMISkeleton method
     * @param message to read
     * @author Alessandro Mancini
     */
    public void readMessage(Message message) {
        if (this.connected)
            notifyObserver(message);
    }

    /**
     * method to disable the RMI connection for a Client
     * @author Alessandro Mancini
     */
    @Override
    public void disconnect() {
        this.connected = false;
    }

    /**
     * function implemented because of the Client inherit, but not used in an RMI connection
     * @author Alessandro Mancini
     */
    @Override
    public void sendPingMessage(boolean isActive) {}
}
