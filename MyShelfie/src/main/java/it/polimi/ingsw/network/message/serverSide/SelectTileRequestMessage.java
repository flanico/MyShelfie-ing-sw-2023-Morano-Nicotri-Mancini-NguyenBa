package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message sent to client to request to select tiles from the board
 */

public class SelectTileRequestMessage extends Message{
    @Serial
    private static final long serialVersionUID  = -6332107747110883202L;
    private Board board;

    public SelectTileRequestMessage(Board board) {
        super("SERVER", MessageType.SELECT_TILE_REQ);
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return "SelectTilesRequestMessage{" + getNickname() +
                "}";
    }
}
