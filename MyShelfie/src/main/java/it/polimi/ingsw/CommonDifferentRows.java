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
     *@return true if the Player has to take the topmost available scoring token from that card
     * @author Stefano Morano
     */
    public boolean check(Bookshelf bookshelf) {
        HashSet<TileType> cards = new HashSet<>();
        int counter = 0;
        for (int x=0; x<6; x++){                            //controllo una riga alla volta
            for (int y=0; y<5; y++){                        //per ogni colonna aggiungo il tipo della carta (se presente) nell'hashSet
                if (!bookshelf.getMatrix()[x][y].getType().equals(TileType.NULL))
                    cards.add(bookshelf.getMatrix()[x][y].getType());
                else break;
            }
            if (cards.size() == 5)     //se la quantità di type nell'HashSet è 5, aumento il contatore di righe che soddisfano il requisito
                counter++;
            cards.clear();
        }

        return counter >= 2;    //se il numero di righe che soddisfano il requisito è maggiore o uguale a 2, la funzione ritorna true
    }
}