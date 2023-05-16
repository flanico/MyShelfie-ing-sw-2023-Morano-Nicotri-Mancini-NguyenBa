package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.server.RMIInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient extends Client {
    private final Registry registry;
    private final RMIInterface remote;
    private final RMIInterface skeleton;
    private boolean connected;

    public RMIClient(String ip, int port) throws RemoteException, NotBoundException {
        this.registry = LocateRegistry.getRegistry(ip, port);
        this.remote = (RMIInterface) this.registry.lookup("SERVER");
        this.skeleton = (RMIInterface) this.registry.lookup("SERVER");
        this.skeleton.setClient(this);
        this.connected = true;
    }

    public void sendMessage(Message message) {
        if (connected) {
            try {
                remote.sendMessageToServer(message, this.skeleton);
                Client.LOGGER.info("Sent = " + message);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void readMessage() {}

    public void readMessage(Message message) {
        Client.LOGGER.info("Recived = " + message);
        notifyObserver(message);
    }

    public void disconnect() {
        this.connected = false;
    }

    public void sendPingMessage(boolean isActive) {}
}
