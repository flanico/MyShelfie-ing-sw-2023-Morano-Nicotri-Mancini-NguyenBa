package it.polimi.ingsw.model;

/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Stefano Morano
 */
public class CommonFourCorners extends CommonGoalCard {
    /**
     *Four tiles of the same type in the four corners of the bookshelf.
     *@param bookshelf RoundPlayer
     *@return true if the Player has satisfied every parameter of the Four Corners Common Card
     * @author Stefano Morano
     */
    public boolean check(Bookshelf bookshelf) {       //the algorithm controls if the cards in the corner are not NULL and if they are of the same type
        return !bookshelf.getMatrix()[0][0].getType().equals(TileType.NULL)
                && !bookshelf.getMatrix()[0][4].getType().equals(TileType.NULL)
                && !bookshelf.getMatrix()[5][0].getType().equals(TileType.NULL)
                && !bookshelf.getMatrix()[5][4].getType().equals(TileType.NULL)
                && bookshelf.getMatrix()[0][0].getType().equals(bookshelf.getMatrix()[0][4].getType())
                && bookshelf.getMatrix()[0][4].getType().equals(bookshelf.getMatrix()[5][0].getType())
                && bookshelf.getMatrix()[5][0].getType().equals(bookshelf.getMatrix()[5][4].getType());
    }

    @Override
    public String toString() {
        return "Common Goal Card:  Four tiles of the same type in the four corners of the bookshelf.";
    }
}
