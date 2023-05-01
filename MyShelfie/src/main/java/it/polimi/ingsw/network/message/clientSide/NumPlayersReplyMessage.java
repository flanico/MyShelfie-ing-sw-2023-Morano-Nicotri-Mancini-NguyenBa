package it.polimi.ingsw.network.message.clientSide;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message used to send to server the chosen number of players
 */
public class NumPlayersReplyMessage extends Message {

    @Serial
    private static final long serialVersionUID = -6148977784918247177L;
    private final int numPlayers;

    public NumPlayersReplyMessage(String nickname, int numPlayers) {
        super(nickname, MessageType.NUM_PLAYERS_REP);
        this.numPlayers = numPlayers;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    @Override
    public String toString() {
        return "NumPlayersReplyMessage{" +
                "nickname = " + getNickname() + " ,numPlayers = " + numPlayers +
                '}';
    }
}
