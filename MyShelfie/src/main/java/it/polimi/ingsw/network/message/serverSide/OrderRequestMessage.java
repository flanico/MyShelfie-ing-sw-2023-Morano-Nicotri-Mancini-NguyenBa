package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;
import java.util.List;

public class OrderRequestMessage extends Message {
    @Serial
    private static final long serialVersionUID = -1757584888349247727L;
    private List<Tile> tiles;

    public OrderRequestMessage(List<Tile> tiles) {
        super("SERVER", MessageType.ORDER_REQ);
        this.tiles = tiles;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}
