package it.polimi.ingsw.network.message.clientSide;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message used to communicate the selected position column to insert the tiles in the bookshelf
 */
public class PositionReplyMessage extends Message {
    @Serial
    private static final long serialVersionUID = 139374498578028212L;
    private int column;

    public PositionReplyMessage(String nickname, int column) {
        super(nickname, MessageType.POSITION_REPLY);
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "PositionReplyMessage{" +
                "column=" + column +
                '}';
    }
}
