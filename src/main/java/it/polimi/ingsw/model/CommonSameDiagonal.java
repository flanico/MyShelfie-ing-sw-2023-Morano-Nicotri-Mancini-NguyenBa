package it.polimi.ingsw.model;

/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Stefano Morano
 */
public class CommonSameDiagonal extends CommonGoalCard {
    public final int number = 11;

    /**
     * five tiles of the same type forming a diagonal
     *@param bookshelf RoundPlayer
     *@return true if the Player has satisfied every parameter of the Same Diagonal Type Common Card
     * @author Stefano Morano
     */
    public boolean check(Bookshelf bookshelf) {
        TileType ref;
        boolean flag=false;
        int x;
        if (!bookshelf.getMatrix()[0][0].getType().equals(TileType.NULL)){
            ref = bookshelf.getMatrix()[0][0].getType();
            x=1;
            while (x<5){
                if (!bookshelf.getMatrix()[x][x].getType().equals(ref))
                    x=5;
                if (x==4)
                    flag=true;
                x++;
            }
        }

        if (!bookshelf.getMatrix()[0][4].getType().equals(TileType.NULL) && !flag){
            ref = bookshelf.getMatrix()[0][4].getType();
            x=4;
            while(x>0){
                if (!bookshelf.getMatrix()[x][4-x].getType().equals(ref))
                    x=0;
                if (x==1)
                    flag=true;
                x--;
            }
        }

        if (!bookshelf.getMatrix()[1][0].getType().equals(TileType.NULL) && !flag){
            ref = bookshelf.getMatrix()[1][0].getType();
            x=2;
            while(x<6){
                if (!bookshelf.getMatrix()[x][x-1].getType().equals(ref))
                    x=6;
                if (x==5)
                    flag=true;
                x++;
            }
        }

        if (!bookshelf.getMatrix()[1][4].getType().equals(TileType.NULL) && !flag){
            ref = bookshelf.getMatrix()[1][4].getType();
            x=2;
            while(x<6){
                if (!bookshelf.getMatrix()[x][5-x].getType().equals(ref))
                    x=6;
                if (x==5)
                    flag=true;
                x++;
            }
        }

        return flag;
    }

    @Override
    public String toString() {
        return "Common Goal Card: Five tiles of the same type forming a diagonal";
    }

    @Override
    public int getNumber() {
        return number;
    }
}
