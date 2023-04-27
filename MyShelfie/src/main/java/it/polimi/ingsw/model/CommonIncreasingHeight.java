package it.polimi.ingsw.model;
/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Stefano Morano
 */
public class CommonIncreasingHeight extends CommonGoalCard {
    /**
     *Six Groups of couples of tiles with same types.
     *@param bookshelf RoundPlayer
     *@return true if the Player has satisfied every parameter of the Increasing Height Common Card
     * @author Stefano Morano
     */
    public boolean check(Bookshelf bookshelf) {
        boolean direction = true;
        if (bookshelf.getMatrix()[0][0].getType().equals(TileType.NULL) && bookshelf.getMatrix()[0][4].getType().equals(TileType.NULL)){

            for (int x=1; x<6; x++) {
                if (!bookshelf.getMatrix()[x - 1][x - 1].getType().equals(TileType.NULL) || bookshelf.getMatrix()[x][x - 1].getType().equals(TileType.NULL))
                    x = 6;
                if (x==5)
                    return true;
            }

            for (int x=1; x<6; x++){
                if (!bookshelf.getMatrix()[x - 1][5-x].getType().equals(TileType.NULL) || bookshelf.getMatrix()[x][5-x].getType().equals(TileType.NULL))
                    x=6;
                if (x==5)
                    return true;
            }

            return false;

        } else {
            if (!bookshelf.getMatrix()[0][0].getType().equals(TileType.NULL)) {
                for (int x = 1; x < 5; x++) {
                    if (!bookshelf.getMatrix()[x-1][x].getType().equals(TileType.NULL) || bookshelf.getMatrix()[x][x].getType().equals(TileType.NULL))
                        x = 5;
                    if (x == 4)
                        return true;
                }
            }
            else if (!bookshelf.getMatrix()[0][4].getType().equals(TileType.NULL)) {
                for (int x = 1; x < 5; x++) {
                    if (!bookshelf.getMatrix()[x-1][4 - x].getType().equals(TileType.NULL) || bookshelf.getMatrix()[x][4 - x].getType().equals(TileType.NULL))
                        x = 5;
                    if (x == 4)
                        return true;
                }
            }

            return false;
        }
    }

    @Override
    public String toString() {
        return "Common Goal Card: Five columns of increasing or decreasing height. Starting from the first column on the left or on the right, each next column must be made of exactly one more tile. Tiles can be of any type.";
    }
}
