package it.polimi.ingsw;

import java.util.HashSet;

/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Stefano Morano
 */
public class CommonDifferentRows extends CommonGoalCard {
    /**
     * Two lines each formed by 5 different types of tiles. One line can show the same or a different combination of the other line.
     *@param bookshelf RoundPlayer
     *@return true if the Player has satisfied every parameter of the Different Rows Common Card
     * @author Stefano Morano
     */
    public boolean check(Bookshelf bookshelf) {
        HashSet<TileType> cards = new HashSet<>();
        int counter = 0;
        for (int x=0; x<6; x++){                            //controlling one rows every cycle
            for (int y=0; y<5; y++){                        //For each column, the algorithm adds the TileType of the card in the HashSet
                if (!bookshelf.getMatrix()[x][y].getType().equals(TileType.NULL))
                    cards.add(bookshelf.getMatrix()[x][y].getType());
                else break;
            }
            if (cards.size() == 5)     //If the quantity of the TileTypes in the HashSet is 5, the counter will be increased
                counter++;
            cards.clear();
        }

        return counter >= 2;    //the boolean function returns true if the counter is 2 or more
    }
}