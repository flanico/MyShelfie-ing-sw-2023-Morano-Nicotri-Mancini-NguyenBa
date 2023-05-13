package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.server.RMIClientHandler;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient extends Client {

    public RMIClient(String ip, int port) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(ip, port);
        RMIClientHandler remote = (RMIClientHandler) registry.lookup("rmiServer");
    }

    public  void sendMessage(Message message) {}

    public void readMessage() {}

    public void disconnect() {}

    public void sendPingMessage(boolean isActive) {}
}
