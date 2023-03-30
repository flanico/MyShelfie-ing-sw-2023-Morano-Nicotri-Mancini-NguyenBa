package it.polimi.ingsw;
import java.util.*;

/**
 * class that define a player
 * @author Alessandro Mancini
 */
public class Player {
    private final String nickname;
    private final Bookshelf bookshelf;
    private final PersonalGoalCard personalgoalcard;

    /**
     * constructor of Player
     * @param nickname of the player
     * @author Alessandro Mancini
     */
    public Player(String nickname) {
        this.nickname = nickname;
        this.bookshelf = new Bookshelf();

        Random rand = new Random();
        PersonalGoalCardType type;
        do {
            type = PersonalGoalCardType.values()[rand.nextInt(PersonalGoalCardType.values().length)];
        } while (type.isTaken());
        this.personalgoalcard = new PersonalGoalCard(type);
    }

    /**
     * getter of nickname
     * @author Alessandro Mancini
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * getter of bookshelf
     * @author Alessandro Mancini
     */
    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    public PersonalGoalCard getPersonalgoalcard() {
        return personalgoalcard;
    }

    public ArrayList<Tile> selectTile() {
        return null;
    }
}
