package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonXSameTypeTest {
    private Bookshelf bookshelf = new Bookshelf();
    private Bookshelf bookshelf_2 = new Bookshelf();
    public CommonGoalCard card = new CommonXSameType();
    public int[][] shelf_1 = {          //X Shape respected
            {1,0,0,0,0},
            {2,0,0,0,0},
            {1,0,0,0,0},
            {2,0,1,0,1},
            {1,0,3,1,4},
            {2,0,1,2,1},
    };
    public int[][] shelf_2 = {          //X Shape not respected
            {1,4,3,0,1},
            {2,1,5,4,2},
            {1,2,1,2,4},
            {3,1,2,3,2},
            {1,1,4,2,1},
            {2,2,2,1,1},
    };
    /**
     * Create the matrix of the bookshelf using the matrix of integer "shelf"
     * @author Stefano Morano
     */
    void setUp(Bookshelf b_shelf, int[][] shelf) {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                switch (shelf[x][y]) {
                    case 0:
                        b_shelf.getMatrix()[x][y].setType(TileType.NULL);
                        break;
                    case 1:
                        b_shelf.getMatrix()[x][y].setType(TileType.CAT);
                        break;
                    case 2:
                        b_shelf.getMatrix()[x][y].setType(TileType.PLANT);
                        break;
                    case 3:
                        b_shelf.getMatrix()[x][y].setType(TileType.BOOK);
                        break;
                    case 4:
                        b_shelf.getMatrix()[x][y].setType(TileType.FRAME);
                        break;
                    case 5:
                        b_shelf.getMatrix()[x][y].setType(TileType.GAME);
                        break;
                    case 6:
                        b_shelf.getMatrix()[x][y].setType(TileType.TROPHY);
                        break;
                }
            }
        }
    }
    /**
     * Test the check of the Common Goal Card "X Shape Same Type"
     * @author Stefano Morano
     */
    @Test
    void check() {
        setUp(bookshelf, shelf_1);


        assertTrue(card.check(bookshelf));

    }
}