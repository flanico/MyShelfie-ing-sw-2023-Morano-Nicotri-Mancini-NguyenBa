package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message to show the client his bookshelf
 */
public class ShowBookshelf extends Message {
    @Serial
    private static final long serialVersionUID = -9168339307648742424L;
    private String nickname;
    private Bookshelf bookshelf;

    public ShowBookshelf(String nickname, Bookshelf bookshelf) {
        super("SERVER", MessageType.SHOW_BOOKSHELF);
        this.nickname = nickname;
        this.bookshelf= bookshelf;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    @Override
    public String toString() {
        return "ShowBookshelf{" +
                "bookshelf=" + bookshelf + "player= " + nickname +
                '}';
    }
}

