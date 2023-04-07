package it.polimi.ingsw.model;

/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Flavia Nicotri
 */
public class CommonFourGroups extends CommonGoalCard {
    /**
     * two groups of four tiles in a square shape, that can contains two different type of tiles
     *@param bookshelf RoundPlayer
     *@return true if the Player has to take the topmost available scoring token from that card
     * @author Flavia Nicotri
     */
    public boolean check(Bookshelf bookshelf) {
        int counter ;
        int num=0;

        //inizializzo counted = 0
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                bookshelf.getMatrix()[i][j].setCounted(false);
            }
        }

        for (int i = 0; i<6; i++) {
            for (int j=0; j<5; j++){
                if (!bookshelf.getMatrix()[i][j].isCounted()) {
                    bookshelf.getMatrix()[i][j].setCounted(true);
                    //System.out.print( i + "" + j );
                    counter = bookshelf.sameGroup(i, j, 1);
                    //System.out.print(counter+" ");
                    if(counter >= 4)
                    {
                        num++;
                        //System.out.println( num + "group");
                    }
                    if (num == 4)
                    {
                        //System.out.print("win the card ");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
