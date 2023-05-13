package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.observer.Observable;

import java.io.Serial;
import java.util.logging.Logger;

/**
 * abstract class used to communicate with the server, every type of connection must implement this class
 */
public abstract class Client extends Observable {
    public static final Logger LOGGER = Logger.getLogger(Client.class.getName());
    /**
     * sends a message to the server
     * @param message to be sent
     */
    public abstract void sendMessage(Message message);

    /**
     * reads a message from the server and notifies the ClientController
     */
    public abstract void readMessage();

    /*+
     * disconnect from the server
     */
    public abstract void disconnect();

    /**
     * sends a ping message in order to keep alive the connection between server and client
     * @param isActive is true to enable the ping, false otherwise
     */
    public abstract void sendPingMessage(boolean isActive);
}
