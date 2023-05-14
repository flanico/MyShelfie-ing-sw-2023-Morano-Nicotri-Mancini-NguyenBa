package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.server.RMIClientHandler;
import it.polimi.ingsw.network.server.RMIInterface;

import java.io.Serial;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient extends Client implements Serializable {
    @Serial
    private final static long serialVersionUID = 3182670738296208821L;
    private final Registry registry;
    private final RMIInterface remote;

    public RMIClient(String ip, int port) throws RemoteException, NotBoundException {
        this.registry = LocateRegistry.getRegistry(ip, port);
        this.remote = (RMIInterface) this.registry.lookup("SERVER");
    }

    public void sendMessage(Message message) {

        try {
            remote.onMessage(message, this);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void readMessage() {}

    public void readMessage(Message message) {
        notifyObserver(message);
    }

    public void disconnect() {}

    public void sendPingMessage(boolean isActive) {}
}
