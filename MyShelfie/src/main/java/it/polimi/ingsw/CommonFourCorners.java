package it.polimi.ingsw;

/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Stefano Morano
 */
public class CommonFourCorners extends CommonGoalCard {
    /**
     *Four tiles of the same type in the four corners of the bookshelf.
     *@return true if the Player has to take the topmost available scoring token from that card
     * @author Stefano Morano
     */
    public boolean check(Bookshelf bookshelf) {
        return !bookshelf.getMatrix()[0][0].getType().equals(TileType.NULL)
                && !bookshelf.getMatrix()[0][4].getType().equals(TileType.NULL)
                && !bookshelf.getMatrix()[5][0].getType().equals(TileType.NULL)
                && !bookshelf.getMatrix()[5][4].getType().equals(TileType.NULL)
                && bookshelf.getMatrix()[0][0].getType().equals(bookshelf.getMatrix()[0][5].getType())
                && bookshelf.getMatrix()[0][4].getType().equals(bookshelf.getMatrix()[4][0].getType())
                && bookshelf.getMatrix()[5][0].getType().equals(bookshelf.getMatrix()[4][5].getType());
    }
}
