package it.polimi.ingsw.network.message;

import java.io.Serial;
import java.io.Serializable;

/**
 * abstract message class which must be extended by each concrete message type
 * server and clients communicate using this generic type of message
 */
public abstract class Message implements Serializable {
    @Serial
    private final static long serialVersionUID = 3182670738296108821L;
    private final String nickname;
    private final MessageType messageType;

    /**
     * constructor of the message
     * @param messageType the type of the message
     * @param nickname the nickname of the message sender
     */
    Message(String nickname, MessageType messageType) {
        this.nickname = nickname;
        this.messageType = messageType;
    }

    /**
     * @return nickname of the message sender
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @return type of the message
     */
    public MessageType getMessageType() {
        return messageType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "nickname='" + nickname + '\'' +
                ", messageType=" + messageType +
                '}';
    }
}