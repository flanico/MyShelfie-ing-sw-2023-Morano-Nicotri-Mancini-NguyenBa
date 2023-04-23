package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message sent to client to notify that the bookshelf of another player is full
 */

public class BookshelfFull2Message extends Message{
    @Serial
    private static final long serialVersionUID = -4519865135889047239L;
    private  String firstplayer;

    public BookshelfFull2Message( String firstplayer) {
        super("SERVER", MessageType.BOOKSHELF_FULL2);
        this.firstplayer = firstplayer;
    }


    public String getFirstplayer() {
        return firstplayer;
    }

    @Override
    public String toString() {
        return "BookshelfFull{" + this.firstplayer +
                "'s bookshelf is full" +
                '}';
    }
}