package it.polimi.ingsw;

import java.util.HashSet;

public class CommonDifferentRows extends CommonGoalCard {
    public boolean check()public boolean check() {
        HashSet<TileType> cards = new HashSet<>();
        int counter = 0;
        for (int x=0; x<6; x++){                            //controllo una riga alla volta
            for (int y=0; y<5; y++){                        //per ogni colonna aggiungo il tipo della carta (se presente) nell'hashSet
                if (!GetCard(x,y).empty)
                    cards.add(GetCard(x,y).type);
                else return false;
            }
            if (cards.size() == 5)     //se la quantità di type nell'HashSet è 5, aumento il contatore di righe che soddisfano il requisito
                counter++;
            cards.clear();
        }
        if (counter >= 2)                                   //se il numero di righe che soddisfano il requisito è maggiore o uguale a 2, la funzione ritorna true
            return true;
        else return false;
    }
}
