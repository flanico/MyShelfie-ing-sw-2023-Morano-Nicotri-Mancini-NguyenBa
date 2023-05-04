package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message sent to client to notify the disconnection of a player
 */
public class DisconnectionMessage extends Message {
    @Serial
    private static final long serialVersionUID = 4101171297737014184L;
    private final String nickname;

    public DisconnectionMessage(String nickname) {
        super( "SERVER" , MessageType.DISCONNECTION);
        this.nickname = nickname;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return "DisconnectionMessage{" +
                "nickname='" + nickname + '\'' +
                '}';
    }
}
