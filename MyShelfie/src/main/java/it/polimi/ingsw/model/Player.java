package it.polimi.ingsw.model;
import java.util.ArrayList;

public class Player {
    private String nickname;

    private Bookshelf bookshelf;

    private PersonalGoalCard personalGoalCard;

    private ArrayList<Tile> selectedTile;

    public Player (String nickname) {
        this.nickname = nickname;
        this.bookshelf = new Bookshelf();
    }

    public PersonalGoalCard getPersonalGoalCard() {
        return personalGoalCard;
    }

    public String getNickname() {
        return this.nickname;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }


    //insertTile
}
