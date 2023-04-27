package it.polimi.ingsw.model;

/**
 * override of the method check of the abstract class CommonGoalCard
 * @author Stefano Morano
 */
public class CommonEightSameType extends CommonGoalCard {
    /**
     *Eight tiles of the same type. There’s no restriction about the position of these tiles.
     * @param bookshelf RoundPlayer
     *@return true if the Player has satisfied every parameter of the Eight Same Type Common Card
     * @author Stefano Morano
     */
    public boolean check(Bookshelf bookshelf){
        int[] cards = {0, 0, 0, 0, 0, 0};
        for (int x = 0; x < 6; x++) {                //the algorithm scans the whole bookshelf matrix
            for (int y = 0; y < 5; y++) {
                switch (bookshelf.getMatrix()[x][y].getType()) {       //the array get increased by one and the position depends on the typeCard found in the scan
                    case CAT:
                        cards[0] += 1;
                        break;
                    case PLANT:
                        cards[1] += 1;
                        break;
                    case BOOK:
                        cards[2] += 1;
                        break;
                    case FRAME:
                        cards[3] += 1;
                        break;
                    case GAME:
                        cards[4] += 1;
                        break;
                    case TROPHY:
                        cards[5] += 1;
                        break;
                }
            }
        }

        for (int cont = 0; cont < 6; cont++) {                         //it returns true only if there is one value in the array equals to 8
            if (cards[cont] == 8)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Common Goal Card: Eight tiles of the same type. There’s no restriction about the position of these tiles.";
    }
}
