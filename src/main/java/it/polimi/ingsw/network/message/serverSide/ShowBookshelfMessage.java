package it.polimi.ingsw.network.message.serverSide;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageType;

import java.io.Serial;

/**
 * message to show the client his bookshelf
 */
public class ShowBookshelfMessage extends Message {
    @Serial
    private static final long serialVersionUID = -9168339307648742424L;
    private final Player player;
    private final Bookshelf bookshelf;

    public ShowBookshelfMessage(Player player, Bookshelf bookshelf) {
        super("SERVER", MessageType.SHOW_BOOKSHELF);
        this.player = player;
        this.bookshelf= bookshelf;
    }

    public Player getPlayer() {
        return player;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    @Override
    public String toString() {
        return "ShowBookshelf{player=" + player.getNickname() +
                '}';
    }
}

