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
    private final boolean isConnectionSuccessful;

    public LoginReplyMessage(boolean isNicknameAccepted, boolean isConnectionSuccessful) {
        super("SERVER", MessageType.LOGIN_REPLY);
        this.isNicknameAccepted = isNicknameAccepted;
        this.isConnectionSuccessful = isConnectionSuccessful;
    }

    public boolean isNicknameAccepted() {
        return isNicknameAccepted;
    }

    public boolean isConnectionSuccessful() {
        return isConnectionSuccessful;
    }

    @Override
    public String toString() {
        return "LoginReply{" +
                "isNicknameAccepted=" + isNicknameAccepted +
                ", isConnectionSuccessful=" + isConnectionSuccessful +
                '}';
    }
}
