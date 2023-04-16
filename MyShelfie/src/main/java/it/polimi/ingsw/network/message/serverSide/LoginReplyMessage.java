package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message sent to client to notify if the login is successful or not
 */
public class LoginReplyMessage extends Message {
    @Serial
    private static final long serialVersionUID = -5665874248255941066L;
    private final boolean isNicknameAccepted;

    public LoginReplyMessage(boolean isNicknameAccepted) {
        super("SERVER", MessageType.LOGIN_REPLY);
        this.isNicknameAccepted = isNicknameAccepted;
    }

    public boolean isNicknameAccepted() {
        return isNicknameAccepted;
    }

    @Override
    public String toString() {
        return "LoginReply{" +
                "isNicknameAccepted=" + isNicknameAccepted +
                '}';
    }
}
