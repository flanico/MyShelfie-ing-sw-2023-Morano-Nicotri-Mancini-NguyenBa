package it.polimi.ingsw;

/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Stefano Morano
 */
public class CommonXSameType extends CommonGoalCard {   //the algorithm works, the method of the matrix must be modified
    /**
     * five tiles of the same type forming an X
     *@param pl RoundPlayer
     *@return true if the Player has to take the topmost available scoring token from that card
     * @author Stefano Morano
     */
    public boolean check(Player pl) {
        for (int y=0; y<3; y++){        //scanning from the first to the third column
            for (int x=0; x<4; x++){    //scanning from the first to the fourth row
                if (                                                                                 //this if case finds the X pattern
                        !pl.getBookshelf().getBookshelf()[0][0].getType().equals(TileType.NULL)
                                && pl.getBookshelf().getBookshelf()[x][y].getType().equals(pl.getBookshelf().getBookshelf()[x][y+2].getType())
                                && pl.getBookshelf().getBookshelf()[x][y].getType().equals(pl.getBookshelf().getBookshelf()[x+2][y].getType())
                                && pl.getBookshelf().getBookshelf()[x][y].getType().equals(pl.getBookshelf().getBookshelf()[x+2][y+2].getType())
                                && pl.getBookshelf().getBookshelf()[x][y].getType().equals(pl.getBookshelf().getBookshelf()[x+1][y+1].getType())
                                && !pl.getBookshelf().getBookshelf()[x][y].getType().equals(pl.getBookshelf().getBookshelf()[x][y+1].getType())
                                && !pl.getBookshelf().getBookshelf()[x][y].getType().equals(pl.getBookshelf().getBookshelf()[x+1][y].getType())
                                && !pl.getBookshelf().getBookshelf()[x][y].getType().equals(pl.getBookshelf().getBookshelf()[x+1][y+2].getType())
                                && !pl.getBookshelf().getBookshelf()[x][y].getType().equals(pl.getBookshelf().getBookshelf()[x+2][y+1].getType())
                )
                    return true;
            }
        }
        return false;
    }
}