package it.polimi.ingsw;

public class CommonEightSameType extends CommonGoalCard {
    public boolean check()int cards[] = {0, 0, 0, 0, 0, 0};
    int cont = 0;
        for (int x = 0; x < 5; x++) {                //scansione completa della matrice bookshelf
        for (int y = 0; y < 6; y++) {
            switch GetCard(x, y).type
            case       //l'array cards viene incrementato di 1 in base al tipo di carta trovato nella scansione
                    CAT:
                cards[0] += 1;
                PLANT:
                cards[1] += 1;
                BOOK:
                cards[2] += 1;
                FRAME:
                cards[3] += 1;
                GAME:
                cards[4] += 1;
                TROPHY:
                cards[5] += 1;
        }
    }
        while (cont < 6) {                         //ritorna 1 se nell'array esiste almeno un type che abbia raggiunto 8 presenze
        if (cards[cont] == 8)
            return true;
        cont++;
    }
        return false;
}
}
