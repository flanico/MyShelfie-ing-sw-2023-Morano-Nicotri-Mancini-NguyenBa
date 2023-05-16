package it.polimi.ingsw.network.server.rmi;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.server.ClientHandler;
import it.polimi.ingsw.network.server.Server;

import java.rmi.RemoteException;

public class RMIClientHandler implements ClientHandler {
    private RMIInterface skeleton;

    protected RMIClientHandler(RMIInterface skeleton) {
        this.skeleton = skeleton;
    }

    @Override
    public void sendMessageToClient(Message message) {
        try {
            Server.LOGGER.info(() -> "Message to " + message.getNickname() + ": " + message);
            this.skeleton.toClient(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void disconnectClient() {
        try {
            this.skeleton.disconnectRMI();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
