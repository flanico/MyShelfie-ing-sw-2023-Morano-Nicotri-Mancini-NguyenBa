package it.polimi.ingsw;

public class CommonEightSameType extends CommonGoalCard {
    public boolean check(Player pl){
        int[] cards = {0, 0, 0, 0, 0, 0};
        int cont = 0;
        for (int x = 0; x < 5; x++) {                //the algorithm scans the whole bookshelf matrix
            for (int y = 0; y < 6; y++) {
                switch (pl.getBookshelf().getBookshelf()[x][y].getType()) {       //the array get increased by one and the position depends on the typeCard found in the scan
                    case CAT:
                        cards[0] += 1;
                    case PLANT:
                        cards[1] += 1;
                    case BOOK:
                        cards[2] += 1;
                    case FRAME:
                        cards[3] += 1;
                    case GAME:
                        cards[4] += 1;
                    case TROPHY:
                        cards[5] += 1;
                }
            }
        }
        while (cont < 6) {                         //it returns true only if there is one value in the array equals to 8
            if (cards[cont] == 8)
                return true;
            cont++;
        }
        return false;
    }
}
