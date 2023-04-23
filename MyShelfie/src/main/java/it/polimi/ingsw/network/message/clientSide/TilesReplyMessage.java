package it.polimi.ingsw.network.message.clientSide;

import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;
import java.util.List;

public class TilesReplyMessage extends Message {
    @Serial
    private static final long serialVersionUID = 5909872357046367409L;
    private List<Tile> tiles;

    public TilesReplyMessage(String nickname, List<Tile> tiles) {
        super(nickname, MessageType.TILES_REPLY);
        this.tiles = tiles;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    @Override
    public String toString() {
        return "TilesReplyMessage{" +
                "tiles=" + tiles +
                '}';
    }
}
