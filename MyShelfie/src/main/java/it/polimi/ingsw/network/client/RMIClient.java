package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.server.RMIInterface;

import java.io.Serial;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient extends Client implements Serializable{
    @Serial
    private final static long serialVersionUID = 3182670738296158821L;
    private final Registry registry;
    private final RMIInterface remote;
    private final RMIInterface skeleton;
    private boolean connected;

    public RMIClient(String ip, int port) throws RemoteException, NotBoundException {
        this.registry = LocateRegistry.getRegistry(ip, port);
        this.remote = (RMIInterface) this.registry.lookup("SERVER");
        this.skeleton = new RMISkeleton(this);
        this.connected = true;
    }
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

    @Override
    public void readMessage() {}

    public void readMessage(Message message) {
        notifyObserver(message);
    }

    @Override
    public void disconnect() {
        this.connected = false;
    }

    @Override
    public void sendPingMessage(boolean isActive) {}
}
