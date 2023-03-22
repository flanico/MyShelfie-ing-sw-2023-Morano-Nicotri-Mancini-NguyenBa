package it.polimi.ingsw;

public class Player {
    private String nickname;

    private Bookshelf bookshelf;

    public Player (String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return this.nickname;
    }
}
