package it.polimi.ingsw;

/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Stefano Morano
 */
public class CommonSameDiagonal extends CommonGoalCard {
    /**
     * five tiles of the same type forming a diagonal
     *@param pl RoundPlayer
     *@return true if the Player has to take the topmost available scoring token from that card
     * @author Stefano Morano
     */
    public boolean check(Player pl) {
        TileType ref;
        boolean flag=false;
        int x;
        if (!pl.getBookshelf().getBookshelf()[0][0].getType().equals(TileType.NULL)){
            ref = pl.getBookshelf().getBookshelf()[0][0].getType();
            x=1;
            while (x<5){
                if (!pl.getBookshelf().getBookshelf()[x][x].getType().equals(ref))
                    x=5;
                if (x==4)
                    flag=true;
                x++;
            }
        }

        if (!pl.getBookshelf().getBookshelf()[0][4].getType().equals(TileType.NULL) && !flag){
            ref = pl.getBookshelf().getBookshelf()[0][4].getType();
            x=4;
            while(x>0){
                if (!pl.getBookshelf().getBookshelf()[x][4-x].getType().equals(ref))
                    x=0;
                if (x==1)
                    flag=true;
                x--;
            }
        }

        if (!pl.getBookshelf().getBookshelf()[1][0].getType().equals(TileType.NULL) && !flag){
            ref = pl.getBookshelf().getBookshelf()[1][0].getType();
            x=2;
            while(x<6){
                if (!pl.getBookshelf().getBookshelf()[x][x-1].getType().equals(ref))
                    x=6;
                if (x==5)
                    flag=true;
                x++;
            }
        }

        if (!pl.getBookshelf().getBookshelf()[1][4].getType().equals(TileType.NULL) && !flag){
            ref = pl.getBookshelf().getBookshelf()[1][4].getType();
            x=2;
            while(x<6){
                if (!pl.getBookshelf().getBookshelf()[x][5-x].getType().equals(ref))
                    x=6;
                if (x==5)
                    flag=true;
                x++;
            }
        }

        return flag;
    }
}
