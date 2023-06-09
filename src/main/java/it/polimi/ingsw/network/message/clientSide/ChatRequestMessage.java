package it.polimi.ingsw.network.message.clientSide;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * Message sent from the client to the server to request a chat message
 * @author Flavia Nicotri
 */
public class ChatRequestMessage extends Message {
    @Serial
    private static final long serialVersionUID = 4227821647957063772L;
    private final String message;
    private final String sender;
    private final String destination;

    public ChatRequestMessage(String sender, String destination, String message) {
        super(sender, MessageType.CHAT_MESSAGE_REQ);
        this.sender = sender;
        this.destination = destination;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public String getSender() {
        return sender;
    }
    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Message sent from [" +  sender + "] to "+ destination + ":  " + message ;
    }
}
