package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.awt.print.Book;
import java.io.Serial;

/**
 * message sent to client to request to select tiles from the board
 */
public class SelectTileRequestMessage extends Message{
    @Serial
    private static final long serialVersionUID  = -6332107747110883202L;
    private final Board board;
    private final Bookshelf bookshelf;

    public SelectTileRequestMessage(Board board, Bookshelf bookshelf) {
        super("SERVER", MessageType.SELECT_TILE_REQ);
        this.board = board;
        this.bookshelf = bookshelf;
    }

    public Board getBoard() {
        return board;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    @Override
    public String toString() {
        return "SelectTilesRequestMessage{nickname=" + getNickname() +
                "}";
    }
}
