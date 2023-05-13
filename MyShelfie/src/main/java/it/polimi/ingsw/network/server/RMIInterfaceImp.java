package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.RMIClient;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIInterfaceImp extends UnicastRemoteObject implements RMIInterface {
    private final Server server;

    protected RMIInterfaceImp(Server server) throws RemoteException {
        this.server = server;
    }

    public void onMessage(Message message, RMIClient client) throws RemoteException {
        if (message != null && message.getMessageType() != MessageType.PING) {
            if (message.getMessageType() == MessageType.LOGIN_REQ) {
                RMIClientHandler rmiClientHandler = new RMIClientHandler(this.server, client);
                this.server.addClient(message.getNickname(), rmiClientHandler);
                System.out.println("ciao");
            }
            else {
                Server.LOGGER.info(() -> "Message received from: " + message.getNickname() + ": " + message);
                this.server.forwardsMessage(message);
            }
        }
    }
}
