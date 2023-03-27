package it.polimi.ingsw;
import java.util.ArrayList;

public class Player {
    private final String nickname;

    private final Bookshelf bookshelf;

    public Player (String nickname) {
        this.nickname = nickname;
        this.bookshelf = new Bookshelf();
    }

    public String getNickname() {
        return this.nickname;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    //selectTile

}
