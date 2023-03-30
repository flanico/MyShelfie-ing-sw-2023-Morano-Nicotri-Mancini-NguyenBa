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
        for (int y=0; y<5; y++) {                            //controllo una colonna alla volta
            for (int x = 0; x < 6; x++) {                        //per ogni riga aggiungo il tipo della carta (se presente) nell'hashSet
                if (!bookshelf.getMatrix()[x][y].getType().equals(TileType.NULL))       //posizione != null
                    cards.add(bookshelf.getMatrix()[x][y].getType());
                else break;
            }
            if (cards.size() == 6)     //se la quantità di type nell'HashSet è 6, aumento il contatore di colonne che soddisfano il requisito
                counter++;
            cards.clear();
        }
        return counter >= 2; //se il numero di colonne che soddisfano il requisito è maggiore o uguale a 2, la funzione ritorna true
    }
}
