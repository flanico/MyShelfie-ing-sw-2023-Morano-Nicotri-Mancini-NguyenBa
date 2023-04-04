package it.polimi.ingsw.model;

/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Stefano Morano
 */
public class CommonFourGroups extends CommonGoalCard {
    /**
     * two groups of four tiles in a square shape, that can contains two different type of tiles
     *@param bookshelf RoundPlayer
     *@return true if the Player has to take the topmost available scoring token from that card
     * @author Stefano Morano
     */
    public boolean check(Bookshelf bookshelf) { return false;
    }
}
