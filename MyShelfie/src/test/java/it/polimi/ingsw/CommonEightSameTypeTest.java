package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonEightSameTypeTest {

    private Bookshelf bookshelf = new Bookshelf();
    public CommonGoalCard card = new CommonEightSameType();
    public int[][] shelf = {
            {1,5,0,0,0},
            {2,1,0,4,0},
            {1,2,2,2,4},
            {4,3,5,2,1},
            {1,4,3,2,1},
            {6,5,4,3,1},
    };

    void setUp() {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                switch (shelf[x][y]) {
                    case 0:
                        bookshelf.getMatrix()[x][y].setType(TileType.NULL);
                        break;
                    case 1:
                        bookshelf.getMatrix()[x][y].setType(TileType.CAT);
                        break;
                    case 2:
                        bookshelf.getMatrix()[x][y].setType(TileType.PLANT);
                        break;
                    case 3:
                        bookshelf.getMatrix()[x][y].setType(TileType.BOOK);
                        break;
                    case 4:
                        bookshelf.getMatrix()[x][y].setType(TileType.FRAME);
                        break;
                    case 5:
                        bookshelf.getMatrix()[x][y].setType(TileType.GAME);
                        break;
                    case 6:
                        bookshelf.getMatrix()[x][y].setType(TileType.TROPHY);
                        break;
                }
            }
        }
    }
    void print(){
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                System.out.print(bookshelf.getMatrix()[x][y].getType() + " ");
            }
            System.out.println();
        }
    }
    @Test
    void check() {
        setUp();
        print();
        System.out.println();
        if (card.check(bookshelf))
            System.out.println("Correct");
        else System.out.println("Not correct");
    }
}