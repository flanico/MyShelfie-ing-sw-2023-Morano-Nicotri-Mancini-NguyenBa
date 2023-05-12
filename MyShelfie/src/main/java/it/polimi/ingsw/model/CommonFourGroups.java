package it.polimi.ingsw.model;

/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Flavia Nicotri
 */
public class CommonFourGroups extends CommonGoalCard {

    public final int number = 3;
    /**
     * two groups of four tiles in a square shape, that can contains two different type of tiles
     *@param bookshelf RoundPlayer
     *@return true if the Player has satisfied every parameter of the Four Groups Common Card
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

    @Override
    public String toString() {
        return "Common Goal Card: Four groups each containing at least " +
                "4 tiles of the same type.\n" +
                "The tiles of one group can be different from those of another group";
    }
    @Override
    public int getNumber() {
        return number;
    }
}
