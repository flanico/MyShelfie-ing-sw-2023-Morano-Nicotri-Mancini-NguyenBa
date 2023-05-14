package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;
import it.polimi.ingsw.network.message.clientSide.LoginRequestMessage;
import it.polimi.ingsw.network.message.clientSide.TilesReplyMessage;
import it.polimi.ingsw.network.message.serverSide.LoginReplyMessage;
import it.polimi.ingsw.network.server.RMIInterface;
import it.polimi.ingsw.network.server.Server;

import java.io.Serial;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient extends Client implements Serializable, Runnable {
    @Serial
    private final static long serialVersionUID = 3182670738296208821L;
    private final Registry registry;
    private final RMIInterface remote;
    private Message currentMessage = new LoginReplyMessage(true);
    private boolean connected;


    public RMIClient(String ip, int port) throws RemoteException, NotBoundException {
        this.registry = LocateRegistry.getRegistry(ip, port);
        this.remote = (RMIInterface) this.registry.lookup("SERVER");
        this.connected = true;
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

    public void sendMessage(Message message) {
        if (connected == true) {
            try {
                remote.sendMessageToServer(message, this);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void readMessage() {
        try {
            this.currentMessage = this.remote.takeMessage();
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
