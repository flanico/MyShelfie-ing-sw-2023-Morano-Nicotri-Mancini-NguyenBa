package it.polimi.ingsw.network.client.rmi;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.server.rmi.RMIInterface;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * class that represents an RMI client implementation
 */
public class RMIClient extends Client {
    private final String ip;
    private final int port;
    private Registry registry;
    private final RMIInterface stub;
    private final RMIInterface skeleton;
    private boolean connected;

    /**
     * constructor for the RMIClient
     * @param ip of the connection
     * @param port of the connection
     * @author Alessandro Mancini
     */
    public RMIClient(String ip, int port) throws RemoteException, NotBoundException {
        this.ip = ip;
        this.port = port;
        this.registry = LocateRegistry.getRegistry(ip, port);
        this.stub = (RMIInterface) registry.lookup("SERVER");
        this.skeleton = new RMISkeleton(this);
        this.connected = true;
    }

    /**
     * check if the RMIServer is online
     * @author Alessandro Mancini
     */
    private boolean checkConnection() throws RemoteException {
        try {
            this.registry = LocateRegistry.getRegistry(this.ip, this.port);
            return true;
        } catch (ConnectException e) {
            return false;
        }
    }

    /**
     * sends messages to server, using RMIStub method
     * @param message to send
     * @author Alessandro Mancini
     */
    @Override
    public void sendMessage(Message message) {
        try {
            if (connected && this.checkConnection()) {
                try {
                    stub.toServer(message, this.skeleton);
                } catch (RemoteException e) {
                    Client.LOGGER.severe("Error in sending message to the RMI Server");
                }
            }
        } catch (RemoteException e) {
            Client.LOGGER.severe("Server is offline");
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
        try {
            if (this.connected && this.checkConnection())
                notifyObserver(message);
        } catch (RemoteException e) {
            Client.LOGGER.severe("Server is offline");
        }
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
