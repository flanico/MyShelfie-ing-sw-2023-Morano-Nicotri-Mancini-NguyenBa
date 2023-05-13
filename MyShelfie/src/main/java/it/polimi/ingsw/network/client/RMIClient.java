package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.server.RMIImplementation;
import it.polimi.ingsw.network.server.RMIInterface;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient extends Client {
    private final Registry registry;
    private final RMIInterface remote;

    public RMIClient(String ip, int port) throws RemoteException, NotBoundException {
        this.registry = LocateRegistry.getRegistry(ip, port);
        this.remote = (RMIInterface) this.registry.lookup("rmiServer");
        this.remote.login(this);
    }

    public void sendMessage(Message message) {}

    public void readMessage() {}

    public void disconnect() {}

    public void sendPingMessage(boolean isActive) {}
}
