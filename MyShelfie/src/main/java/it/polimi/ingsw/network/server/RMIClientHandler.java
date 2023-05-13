package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIClientHandler extends UnicastRemoteObject implements Runnable, ClientHandler {
    private final Server server;

    public RMIClientHandler(Server server) throws RemoteException {
        this.server = server;
    }

    @Override
    public void run () {

    }

    public void sendMessageToClient(Message message) {

    }

    public void disconnectClient() {

    }
}
