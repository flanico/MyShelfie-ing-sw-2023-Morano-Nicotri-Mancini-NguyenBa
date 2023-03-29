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
    public boolean check(Player pl) {
        return !pl.getBookshelf().getBookshelf()[0][0].getType().equals(TileType.NULL)
                && !pl.getBookshelf().getBookshelf()[0][4].getType().equals(TileType.NULL)
                && !pl.getBookshelf().getBookshelf()[5][0].getType().equals(TileType.NULL)
                && !pl.getBookshelf().getBookshelf()[5][4].getType().equals(TileType.NULL)
                && pl.getBookshelf().getBookshelf()[0][0].getType().equals(pl.getBookshelf().getBookshelf()[0][5].getType())
                && pl.getBookshelf().getBookshelf()[0][4].getType().equals(pl.getBookshelf().getBookshelf()[4][0].getType())
                && pl.getBookshelf().getBookshelf()[5][0].getType().equals(pl.getBookshelf().getBookshelf()[4][5].getType());
    }
}
