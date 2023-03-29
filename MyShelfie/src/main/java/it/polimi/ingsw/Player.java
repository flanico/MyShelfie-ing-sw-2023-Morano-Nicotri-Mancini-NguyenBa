package it.polimi.ingsw;
import java.util.*;

public class Player {
    private final String nickname;
    private final Bookshelf bookshelf;
    private PersonalGoalCard personalgoalcard;

    public Player(String nickname) {
        this.nickname = nickname;
        this.bookshelf = new Bookshelf();
    }

    public String getNickname() {
        return this.nickname;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    public PersonalGoalCard getPersonalgoalcard() {
        return personalgoalcard;
    }

    public void setPersonalgoalcard(PersonalGoalCard personalgoalcard) {
        this.personalgoalcard = personalgoalcard;
    }

    public ArrayList<Tile> selectTile() {
        return null;
    }
}
