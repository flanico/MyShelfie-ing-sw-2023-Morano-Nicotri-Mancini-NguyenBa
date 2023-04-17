package it.polimi.ingsw.model;

/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Stefano Morano
 */
public class CommonTwoSquares extends CommonGoalCard {
    /**
     * two groups of four tiles in a square shape, that can contains two different type of tiles
     *@param bookshelf RoundPlayer
     *@return true if the Player has satisfied every parameter of the Two Squares Common Card
     * @author Stefano Morano
     */
    public boolean check(Bookshelf bookshelf) {
        TileType ref;
        boolean flag;
        int counter=0;
        for (int x=0; x<5; x++){
            for (int y=0; y<4; y++){
                flag=false;
                ref=bookshelf.getMatrix()[x][y].getType();
                if (bookshelf.getMatrix()[x][y+1].getType().equals(ref) && bookshelf.getMatrix()[x+1][y+1].getType().equals(ref) && bookshelf.getMatrix()[x+1][y].getType().equals(ref)){
                    flag=true;
                    if (y!=0 && (bookshelf.getMatrix()[x][y-1].getType().equals(ref) || bookshelf.getMatrix()[x+1][y-1].getType().equals(ref)))
                        flag=false;
                    if (y!=3 && (bookshelf.getMatrix()[x][y+2].getType().equals(ref) || bookshelf.getMatrix()[x+1][y+2].getType().equals(ref)))
                        flag=false;
                    if (x!=4 && (bookshelf.getMatrix()[x+2][y].getType().equals(ref) || bookshelf.getMatrix()[x+2][y+1].getType().equals(ref)))
                        flag=false;
                    if (x!=0 && (bookshelf.getMatrix()[x-1][y].getType().equals(ref) || bookshelf.getMatrix()[x-1][y+1].getType().equals(ref)))
                        flag=false;
                }
                if (flag)
                    counter++;
            }
        }

        return (counter>=2);
    }
}
