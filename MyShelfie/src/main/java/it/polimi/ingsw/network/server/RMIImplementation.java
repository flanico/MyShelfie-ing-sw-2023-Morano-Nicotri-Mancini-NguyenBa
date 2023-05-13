package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIImplementation extends UnicastRemoteObject implements RMIInterface {
    private static final long serialVersionUID = 7973004963846163594L;
    private final Server server;

    protected RMIImplementation(Server server) throws RemoteException {
        this.server = server;
    }

    public void onMessage(Message message, Client client) throws RemoteException {
        if (message.getMessageType() == MessageType.LOGIN_REQ) {
            RMIClientHandler rmiClientHandler = new RMIClientHandler(this.server, client);
            this.server.addClient(message.getNickname(), rmiClientHandler);
        }
    }


}
