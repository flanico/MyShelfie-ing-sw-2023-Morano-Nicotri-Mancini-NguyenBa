package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.Serial;

/**
 * Chat message sent from the server to the client as a reply to a chat request message
 * @author Flavia Nicotri
 */

public class ChatReplyMessage extends Message {
    @Serial
    private static final long serialVersionUID = 5436303574660745930L;
    private final String message;
    private final String sender ;
    private final String destination;
    private final SimpleDateFormat sdf;

    private final String formattedTime;



    public ChatReplyMessage(String sender, String destination, String message) {
        super(sender, MessageType.CHAT_MESSAGE_REPLY);
        this.sender = sender;
        this.destination = destination;
        this.message = message;
        this.sdf = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        formattedTime = sdf.format(now);
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
        return "[" + formattedTime + "] Message sent from [" +  sender + "] to "+ destination + ":  " + message;

    }
}
