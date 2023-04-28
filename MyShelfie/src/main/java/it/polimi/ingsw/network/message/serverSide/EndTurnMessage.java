package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message to notify the client that his turn is ended;
 */

public class EndTurnMessage extends Message {
    @Serial
    private static final long serialVersionUID = -7280735645021953044L;
    private  String nickname;

    public EndTurnMessage(String nickname) {
        super("SERVER", MessageType.END_TURN);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return "EndTurn{" + this.nickname + "ended his turn" +
                '}';
    }
}
