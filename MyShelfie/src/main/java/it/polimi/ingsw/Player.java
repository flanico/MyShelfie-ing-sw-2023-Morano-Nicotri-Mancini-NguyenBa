package it.polimi.ingsw;
import java.util.*;

/**
 * class that define a player
 * @author Alessandro Mancini
 */
public class Player {
    private final String nickname;
    private final Bookshelf bookshelf;
    private int score;

    /**
     * constructor of Player
     * @param nickname of the player
     * @author Alessandro Mancini
     */
    public Player(String nickname) {
        this.nickname = nickname;
        this.bookshelf = new Bookshelf();
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

    /**
     * layer takes tiles from the board
     * @param board of the game
     * @author Alessandro Mancini
     */
    public ArrayList<Tile> selectTile(Board board) {
        return null;
    }
}
