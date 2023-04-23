package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message sent to client to notify that his bookshelf is full
 */

public class BookshelfFullMessage extends Message{
    @Serial
    private static final long serialVersionUID = -3150391058180398618L;
    private final Bookshelf bookshelf;

    public BookshelfFullMessage(Bookshelf bookshelf) {
        super("SERVER", MessageType.BOOKSHELF_FULL);
        this.bookshelf = bookshelf;
    }

    public final Bookshelf getBookshelf() {
        return bookshelf;
    }

    @Override
    public String toString() {
        return "BookshelfFull{" +
                "bookshelf" + this.bookshelf + "is full" +
                '}';
    }
}
