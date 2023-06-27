package it.polimi.ingsw.network.client.rmi;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.serverSide.ErrorMessage;
import it.polimi.ingsw.network.message.serverSide.PingMessage;
import it.polimi.ingsw.network.server.rmi.RMIInterface;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * class that represents an RMI client implementation
 */
public class RMIClient extends Client {
    private final String ip;
    private final int port;
    private Registry registry;
    private final RMIInterface stub;
    private final RMIInterface skeleton;
    private final ScheduledExecutorService pinger;

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
        this.pinger = Executors.newSingleThreadScheduledExecutor();
        this.sendPingMessage(true);
    }

    /**
     * sends messages to server, using RMIStub method
     * @param message to send
     * @author Alessandro Mancini
     */
    @Override
    public void sendMessage(Message message) {
        try {
            stub.toServer(message, this.skeleton);
        } catch (RemoteException e) {
            Message error = new ErrorMessage("Connection lost with the RMI Server");
            notifyObserver(error);
            this.disconnect();
            Client.LOGGER.severe("Error in sending message to the RMI Server");
        }
    }

    /**
     * function implemented because of the Client inherit, but not used in an RMI connection
     * @author Alessandro Mancini
     */
    @Override
    public void readMessage() {}

    /**
     * receives messages to read, from RMISkeleton method
     * @param message to read
     * @author Alessandro Mancini
     */
    public void readMessage(Message message) {
        notifyObserver(message);
    }

    /**
     * method to disable the RMI connection for a Client
     * @author Alessandro Mancini
     */
    @Override
    public void disconnect() {
        this.sendPingMessage(false);
    }

    /**
     * ping method to check if RMI Server is alive
     * @author Alessandro Mancini
     */
    @Override
    public void sendPingMessage(boolean isActive) {
        if (isActive) {
            pinger.scheduleAtFixedRate(() -> sendMessage(new PingMessage()), 0, 1000, TimeUnit.MILLISECONDS);
        } else {
            pinger.shutdownNow();
        }
    }
}
