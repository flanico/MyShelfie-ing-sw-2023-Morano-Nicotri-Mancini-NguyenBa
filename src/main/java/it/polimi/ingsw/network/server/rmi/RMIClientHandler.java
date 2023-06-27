package it.polimi.ingsw.network.server.rmi;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;
import it.polimi.ingsw.network.message.serverSide.PingMessage;
import it.polimi.ingsw.network.server.ClientHandler;
import it.polimi.ingsw.network.server.Server;

import java.rmi.RemoteException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * class to handle the RMI connection on server side
 * @author Alessandro Mancini
 */
public class RMIClientHandler implements ClientHandler {
    private Server server;
    private RMIInterface skeleton;

    private final ScheduledExecutorService pinger;

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
        this.pinger = Executors.newSingleThreadScheduledExecutor();
        this.sendPingMessage(true);
    }

    /**
     * method to send messages from server to client, through RMISkeleton
     * @param message to send to the client
     * @author Alessandro Mancini
     */
    @Override
    public void sendMessageToClient(Message message) {
        try {
            if (message != null && message.getMessageType() != MessageType.PING) {
                Server.LOGGER.info(() -> "Message to " + message.getNickname() + ": " + message);
            }
            this.skeleton.toClient(message);
        } catch (RemoteException e) {
            disconnectClient();
            Server.LOGGER.severe("RMI Client connection dropped");
        }
    }

    /**
     * method to disconnect the client from RMI network, through RMISkeleton
     * @author Alessandro Mancini
     */
    @Override
    public void disconnectClient() {
        this.sendPingMessage(false);
        this.server.onDisconnect(this);
    }

    public void sendPingMessage(boolean isActive) {
        if (isActive) {
            pinger.scheduleAtFixedRate(() -> sendMessageToClient(new PingMessage()), 0, 1000, TimeUnit.MILLISECONDS);
        } else {
            pinger.shutdownNow();
        }
    }
}
