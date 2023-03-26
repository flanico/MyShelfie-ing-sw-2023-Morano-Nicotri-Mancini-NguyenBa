package it.polimi.ingsw.model;

import java.util.HashSet;

public class CommonMaxThreeColumns extends CommonGoalCard {
    public boolean check() {
        HashSet<TileType> cards = new HashSet<>();
        int counter = 0;
        for (int y=0; y<5; y++){                            //controllo una colonna alla volta
            for (int x=0; x<6; x++){                        //per ogni riga aggiungo il tipo della carta (se presente) nell'hashSet
                if (!GetCard(x,y).empty)
                    cards.add(GetCard(x,y).type);
                else return false;
            }
            if (cards.size() <= 3 && cards.size() >= 1)     //se la quantità di type nell'HashSet è compreso tra 1 e 3 aumento il contatore di colonne che soddisfano il requisito
                counter++;
            cards.clear();
        }
        if (counter >= 3)                                   //se il numero di colonne che soddisfano il requisito è maggiore o uguale a 3, la funzione ritorna true
            return true;
        else return false;
    }
}
