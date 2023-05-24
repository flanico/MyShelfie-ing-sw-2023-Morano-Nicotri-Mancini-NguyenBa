package it.polimi.ingsw.model;

/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Stefano Morano
 */
public class CommonXSameType extends CommonGoalCard {   //the algorithm works, the method of the matrix must be modified
    public final int number = 10;

    /**
     * five tiles of the same type forming an X
     *@param bookshelf RoundPlayer
     *@return true if the Player has satisfied every parameter of the X Shape Same Type Common Card
     * @author Stefano Morano
     */
    public boolean check(Bookshelf bookshelf) {
        for (int y=0; y<3; y++){        //scanning from the first to the third column
            for (int x=0; x<4; x++){    //scanning from the first to the fourth row
                if (                                                                                 //this if case finds the X pattern
                        !bookshelf.getMatrix()[0][0].getType().equals(TileType.NULL)
                                && bookshelf.getMatrix()[x][y].getType().equals(bookshelf.getMatrix()[x][y+2].getType())
                                && bookshelf.getMatrix()[x][y].getType().equals(bookshelf.getMatrix()[x+2][y].getType())
                                && bookshelf.getMatrix()[x][y].getType().equals(bookshelf.getMatrix()[x+2][y+2].getType())
                                && bookshelf.getMatrix()[x][y].getType().equals(bookshelf.getMatrix()[x+1][y+1].getType())
                                && (!bookshelf.getMatrix()[x][y].getType().equals(bookshelf.getMatrix()[x][y+1].getType()) || bookshelf.getMatrix()[x][y+1].getType().equals(TileType.NULL) )
                                && (!bookshelf.getMatrix()[x][y].getType().equals(bookshelf.getMatrix()[x+1][y].getType()) || bookshelf.getMatrix()[x+1][y].getType().equals(TileType.NULL) )
                                && (!bookshelf.getMatrix()[x][y].getType().equals(bookshelf.getMatrix()[x+1][y+2].getType()) || bookshelf.getMatrix()[x+1][y+2].getType().equals(TileType.NULL) )
                                && (!bookshelf.getMatrix()[x][y].getType().equals(bookshelf.getMatrix()[x+2][y+1].getType()) || bookshelf.getMatrix()[x+2][y+1].getType().equals(TileType.NULL) )
                )
                    return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Common Goal Card: Five tiles of the same type forming an X.";
    }

    @Override
    public int getNumber() {
        return number;
    }
}