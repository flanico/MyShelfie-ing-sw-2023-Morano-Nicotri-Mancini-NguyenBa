package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

public class DisconnectionMessage extends Message {
    @Serial
    private static final long serialVersionUID = 4101171297737014184L;
    private String nickname;
    public DisconnectionMessage(String nickname, MessageType messageType) {
        super(nickname, MessageType.DISCONNECTION);
        this.nickname = nickname;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return "Disconnetion player: " + this.nickname +
        '}';
    }
}
