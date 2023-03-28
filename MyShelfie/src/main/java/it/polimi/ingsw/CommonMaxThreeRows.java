package it.polimi.ingsw;

import java.util.HashSet;

public class CommonMaxThreeRows extends CommonGoalCard {
    public boolean check(Player pl) {
        HashSet<TileType> cards = new HashSet<>();
        int counter = 0;
        for (int x=0; x<6; x++){                            //controlling one row every cycle
            for (int y=0; y<5; y++){                        //for each column the algorithm insert the TypeCard in the HashSet
                if (!pl.getBookshelf().getBookshelf()[x][y].getType().equals(TileType.NULL))
                    cards.add(pl.getBookshelf().getBookshelf()[x][y].getType());
                else break;
            }
            if (cards.size() <= 3 && cards.size() >= 1)     //if the quantity of type in the HashSet is between 1 and 3, the counter increases by 1
                counter++;
            cards.clear();
        }

        return counter >= 4;                                 //if the counter value is more than 3, it returns true
    }
}
