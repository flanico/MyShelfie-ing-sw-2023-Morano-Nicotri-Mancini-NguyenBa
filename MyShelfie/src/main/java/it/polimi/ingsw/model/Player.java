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
    private Bookshelf bookshelf;
    private int score;
    private final String nickname;
    private PersonalGoalCard personalGoalCard;
    private boolean isDoneFirstCommon;
    private boolean isDoneSecondCommon;


    public Player(String nickname) {
        this.bookshelf = new Bookshelf();
        this.nickname = nickname;
        this.score = 0;
        this.isDoneFirstCommon = false;
        this.isDoneSecondCommon = false;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    public int getScore() {
        return score;
    }

    public void setBookshelf(Bookshelf bookshelf) {
        this.bookshelf = bookshelf;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNickname() {
        return nickname;
    }

    public PersonalGoalCard getPersonalGoalCard() {
        return personalGoalCard;
    }

    public void setPersonalGoalCard(PersonalGoalCard personalGoalCard) {
        this.personalGoalCard = personalGoalCard;
    }

    public boolean isDoneFirstCommon() {
        return isDoneFirstCommon;
    }

    public void setDoneFirstCommon(boolean doneFirstCommon) {
        isDoneFirstCommon = doneFirstCommon;
    }

    public boolean isDoneSecondCommon() {
        return isDoneSecondCommon;
    }

    public void setDoneSecondCommon(boolean doneSecondCommon) {
        isDoneSecondCommon = doneSecondCommon;
    }

    @Override
    public String toString() {
        return "Player{" +
                "score=" + score +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
