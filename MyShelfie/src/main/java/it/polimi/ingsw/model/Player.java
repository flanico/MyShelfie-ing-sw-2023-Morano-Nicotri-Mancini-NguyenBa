package it.polimi.ingsw.model;

import java.io.Serial;
import java.io.Serializable;

/**
 * class that defines a player
 * @author Alessandro Mancini
 */
public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = -7872881646495796556L;
    private final Bookshelf bookshelf;
    private int score;
    private final String nickname;
    private PersonalGoalCard personalGoalCard;

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

    /**
     * getter of the personal goal card
     * @return the personal goal card
     */
    public PersonalGoalCard getPersonalGoalCard() {
        return personalGoalCard;
    }

    /**
     * setter of the personal goal card
     * @param personalGoalCard of the player
     */
    protected void setPersonalGoalCard(PersonalGoalCard personalGoalCard) {
        this.personalGoalCard = personalGoalCard;
    }

    @Override
    public String toString() {
        return "Player{" +
                "score=" + score +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
