package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.RMIClient;
import it.polimi.ingsw.network.message.Message;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIClientHandler extends UnicastRemoteObject implements ClientHandler {
    @Serial
    private static final long serialVersionUID = 7973004963846163594L;
    private final transient Server server;


    public RMIClientHandler(Server server) throws RemoteException {
        this.server = server;
    }

    public void sendMessageToClient(Message message) {

    }

    /**
     * disconnect the client from the server
     */
    public void disconnect() {

    }
}
