package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.server.RMIInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient extends Client implements Runnable {
    private final Registry registry;
    private final RMIInterface remote;
    private Message currentMessage;
    private boolean connected;

    public RMIClient(String ip, int port) throws RemoteException, NotBoundException {
        this.registry = LocateRegistry.getRegistry(ip, port);
        this.remote = (RMIInterface) this.registry.lookup("SERVER");
        this.connected = true;
    }

    public void sendMessage(Message message) {
        if (connected) {
            try {
                Client.LOGGER.info("Sent = " + message);
                remote.sendMessageToServer(message);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run () {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (remote.isReadable())
                    this.readMessage();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void readMessage() {
        try {
            this.currentMessage = this.remote.getCurrentMessage();
            Client.LOGGER.info("Recived = " + this.currentMessage);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        notifyObserver(this.currentMessage);
    }

    public void disconnect() {
        this.connected = false;
        Thread.currentThread().interrupt();
    }

    public void sendPingMessage(boolean isActive) {}
}
