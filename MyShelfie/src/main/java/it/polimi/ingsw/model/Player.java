package it.polimi.ingsw.model;

/**
 * class that defines a player
 * @author Alessandro Mancini
 */
public class Player {
    private final Bookshelf bookshelf;
    private int score;
    private String nickname;

    /**
     * constructor of Player
     * @param nickname of the player
     * @author Alessandro Mancini
     */
    public Player(String nickname) {
        this.bookshelf = new Bookshelf();
        this.nickname = nickname;
    }

    /**
     * getter of bookshelf
     * @return bookshelf
     * @author Alessandro Mancini
     */
    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    /**
     * getter of score
     * @return score
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
     * getter of the nickname
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }
}
