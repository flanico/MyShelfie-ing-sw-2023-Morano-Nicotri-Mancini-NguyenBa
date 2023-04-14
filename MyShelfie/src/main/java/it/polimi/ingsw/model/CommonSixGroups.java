package it.polimi.ingsw.model;
/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Stefano Morano
 */
public class CommonSixGroups extends CommonGoalCard {
    /**
     *Six Groups of couples of tiles with same types.
     *@param bookshelf RoundPlayer
     *@return true if the Player has satisfied every parameter of the Max Three Columns Common Card
     * @author Stefano Morano
     */
    public boolean check(Bookshelf bookshelf) {

        int counter = 0;

        for (int x = 0; x < 6; x++) {                           //initializing every tile whit Counted = false
            for (int y = 0; y < 5; y++) {
                bookshelf.getMatrix()[x][y].setCounted(false);
            }
        }

        for (int x=0; x<5; x++){
            for (int y=0; y<5; y++){
                if (!bookshelf.getMatrix()[x][y].isCounted() && !bookshelf.getMatrix()[x+1][y].isCounted()
                      && bookshelf.getMatrix()[x][y].getType().equals(bookshelf.getMatrix()[x+1][y].getType())) {
                    bookshelf.getMatrix()[x][y].setCounted(true);
                    bookshelf.getMatrix()[x+1][y].setCounted(true);
                    counter++;
                }
            }
        }

        for (int x=0; x<6; x++){
            for (int y=0; y<4; y++){
                if (!bookshelf.getMatrix()[x][y].isCounted() && !bookshelf.getMatrix()[x][y+1].isCounted()
                        && bookshelf.getMatrix()[x][y].getType().equals(bookshelf.getMatrix()[x][y+1].getType())) {
                    bookshelf.getMatrix()[x][y].setCounted(true);
                    bookshelf.getMatrix()[x][y+1].setCounted(true);
                    counter++;
                }
            }
        }

        return counter >= 6;

    }
}
