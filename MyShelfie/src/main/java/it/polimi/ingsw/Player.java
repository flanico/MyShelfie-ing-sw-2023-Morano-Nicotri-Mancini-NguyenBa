package it.polimi.ingsw;
import java.util.*;

/**
 * class that define a player
 * @author Alessandro Mancini
 */
public class Player {
    private final Bookshelf bookshelf;
    private int score;

    /**
     * constructor of Player
     * @author Alessandro Mancini
     */
    public Player() {
        this.bookshelf = new Bookshelf();
    }

    /**
     * getter of bookshelf
     * @author Alessandro Mancini
     */
    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    /**
     * getter of score
     * @author Alessandro Mancini
     */
    public int getScore() {
        return score;
    }

    /**
     * setter of score
     * @author Alessandro Mancini
     */
    public void setScore(int score) {
        this.score = score;
    }
}
