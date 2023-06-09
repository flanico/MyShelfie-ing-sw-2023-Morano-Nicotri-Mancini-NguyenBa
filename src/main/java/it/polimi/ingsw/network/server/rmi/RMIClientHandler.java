package it.polimi.ingsw.network.server.rmi;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.server.ClientHandler;
import it.polimi.ingsw.network.server.Server;

import java.rmi.RemoteException;

/**
 * class to handle the RMI connection on server side
 * @author Alessandro Mancini
 */
public class RMIClientHandler implements ClientHandler {
    private Server server;
    private RMIInterface skeleton;

    /**
     * constructor of RMIClientHandler
     *
     * @param skeleton RMISkeleton remote implementation received from client
     * @param server
     * @author Alessandro Mancini
     */
    protected RMIClientHandler(RMIInterface skeleton, Server server) {
        this.skeleton = skeleton;
        this.server = server;
    }

    /**
     * method to send messages from server to client, through RMISkeleton
     * @param message to send to the client
     * @author Alessandro Mancini
     */
    @Override
    public void sendMessageToClient(Message message) {
        try {
            Server.LOGGER.info(() -> "Message to " + message.getNickname() + ": " + message);
            this.skeleton.toClient(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * method to disconnect the client from RMI network, through RMISkeleton
     * @author Alessandro Mancini
     */
    @Override
    public void disconnectClient() {
        try {
            this.server.onDisconnect(this);
            this.skeleton.disconnect();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
