package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

public class ChatReplyMessage extends Message {
    @Serial
    private static final long serialVersionUID = 5436303574660745930L;
    private final String message;
    private final String sender ;
    private final String destination;

    public ChatReplyMessage(String sender, String destination, String message) {
        super(sender, MessageType.CHAT_MESSAGE_REPLY);
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
