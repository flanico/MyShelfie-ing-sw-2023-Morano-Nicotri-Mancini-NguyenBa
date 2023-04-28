package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message sent to client to notify that his bookshelf is full
 */

public class BookshelfFullMessage extends Message{
    @Serial
    private static final long serialVersionUID = -3150391058180398618L;

    public BookshelfFullMessage() {
        super("SERVER", MessageType.BOOKSHELF_FULL);
    }

    @Override
    public String toString() {
        return "BookshelfFull{Your bookshelf is full" +
                '}';
    }
}
