package it.polimi.ingsw.model;

import java.util.HashSet;
/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Stefano Morano
 */
public class CommonMaxThreeRows extends CommonGoalCard {
    public final int number = 7;

    /**
     *Four tiles of the same type in the four corners of the bookshelf.
     *@param bookshelf RoundPlayer
     *@return true if the Player has satisfied every parameter of the Max Three Columns Common Card
     * @author Stefano Morano
     */
    public boolean check(Bookshelf bookshelf) {
        HashSet<TileType> cards = new HashSet<>();
        int counter = 0;
        for (int x=0; x<6; x++){                            //controlling one row every cycle
            for (int y=0; y<5; y++){                        //for each column the algorithm insert the TypeCard in the HashSet
                if (!bookshelf.getMatrix()[x][y].getType().equals(TileType.NULL))
                    cards.add(bookshelf.getMatrix()[x][y].getType());
                else{
                    y = 5;
                    cards.clear();
                }

            }
            if (cards.size() <= 3 && cards.size() >= 1)     //if the quantity of type in the HashSet is between 1 and 3, the counter increases by 1
                counter++;
            cards.clear();
        }

        return counter >= 4;                                 //if the counter value is more than 3, it returns true
    }

    @Override
    public String toString() {
        return "Common Goal Card: Four lines each formed by 5 tiles of maximum three different types. \nOne line can show the same or a different combination of another line.";
    }

    @Override
    public int getNumber() {
        return number;
    }
}
