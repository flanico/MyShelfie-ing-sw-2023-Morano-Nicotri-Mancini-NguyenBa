package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.server.RMIInterface;
import it.polimi.ingsw.network.message.Message;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient extends Client{
    public RMIClient(String ipAddress, int port) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(ipAddress, port);
        RMIInterface remote = (RMIInterface) registry.lookup("rmiServer");
    }

    public  void sendMessage(Message message) {}

    public void readMessage() {}

    public void disconnect() {}

    public void sendPingMessage(boolean isActive) {}
}
