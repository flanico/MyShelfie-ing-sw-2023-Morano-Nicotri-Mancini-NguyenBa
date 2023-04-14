package it.polimi.ingsw.model;

import java.util.HashSet;
/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Stefano Morano
 */
public class CommonMaxThreeColumns extends CommonGoalCard {
    /**
     *Four tiles of the same type in the four corners of the bookshelf.
     *@param bookshelf RoundPlayer
     *@return true if the Player has satisfied every parameter of the Max Three Columns Common Card
     * @author Stefano Morano
     */
    public boolean check(Bookshelf bookshelf) {
        HashSet<TileType> cards = new HashSet<>();
        int counter = 0;
        for (int y=0; y<5; y++){                            //controlling one column at every cycle
            for (int x=0; x<6; x++){                        //for each row, the algorithm adds the TileType of the card in the HashSet
                if (!bookshelf.getMatrix()[x][y].getType().equals(TileType.NULL))
                    cards.add(bookshelf.getMatrix()[x][y].getType());
            }
            if (cards.size() <= 3 && cards.size() >= 1)                 //if the quantity of the TileType in the HashSet is between 1 and 3, the counter will be increased
                counter++;
            cards.clear();
        }
        return counter >= 3;    //if the counter is 3 or more, the boolean function will return true
    }
}
