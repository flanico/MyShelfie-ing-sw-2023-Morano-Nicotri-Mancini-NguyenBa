package it.polimi.ingsw.network.message.clientSide;

import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;
import java.util.List;

/**
 * message used to communicate the selected position column to insert the tiles in the bookshelf
 */
public class PositionReplyMessage extends Message {
    @Serial
    private static final long serialVersionUID = 139374498578028212L;
    private int column;
    private List<Tile> tiles;

    public PositionReplyMessage(String nickname, int column, List<Tile> tiles) {
        super(nickname, MessageType.POSITION_REPLY);
        this.column = column;
        this.tiles = tiles;
    }

    public int getColumn() {
        return column;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    @Override
    public String toString() {
        return "PositionReplyMessage{" +
                "nickname = " + getNickname() + ", column = " + column + ", tiles = " + tiles.toString();
    }
}
