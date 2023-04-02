package it.polimi.ingsw;
import java.util.HashSet;

/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Stefano Morano
 */
public class CommonDifferentColumns extends CommonGoalCard {

    /**
     * check in the player's bookshelf there are 2 columns full of different types of tiles
     * @param bookshelf RoundPlayer
     * @return true if the Player has to take the topmost available scoring token from that card
     * @author Stefano Morano
     */
    public boolean check(Bookshelf bookshelf) {
        HashSet<TileType> cards = new HashSet<TileType>();
        int counter = 0;
        for (int y=0; y<5; y++) {                            //controlling a column at every cycle
            for (int x = 0; x < 6; x++) {                        //for each row, it adds the TileType in the HashSet
                if (!bookshelf.getMatrix()[x][y].getType().equals(TileType.NULL))
                    cards.add(bookshelf.getMatrix()[x][y].getType());
                else break;
            }
            if (cards.size() == 6)     //if the quantity of the TileType in the HashSet is 6, the counter will be increased
                counter++;
            cards.clear();
        }
        return counter >= 2;
    }
}
