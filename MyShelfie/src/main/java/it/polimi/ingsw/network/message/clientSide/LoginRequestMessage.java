package it.polimi.ingsw.network.message.clientSide;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message used to communicate the selected nickname
 */
public class LoginRequestMessage extends Message {
     @Serial
     private static final long serialVersionUID = 2822092131142749883L;

    public LoginRequestMessage(String nickname) {
        super(nickname, MessageType.LOGIN_REQ);
    }

    @Override
    public String toString() {
        return "LoginRequestMessage{" +
                "nickname " + getNickname() +
                "}";
    }
}
