package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonIncreasingHeightTest {

    private Bookshelf bookshelf_1 = new Bookshelf();
    private Bookshelf bookshelf_2 = new Bookshelf();
    private Bookshelf bookshelf_3 = new Bookshelf();
    private Bookshelf bookshelf_4 = new Bookshelf();
    private Bookshelf bookshelf_5 = new Bookshelf();
    public CommonGoalCard card = new CommonIncreasingHeight();
    public int[][] shelf_2 = {
            {2,0,0,0,0},
            {2,4,0,0,0},
            {1,1,2,0,0},
            {2,1,2,3,0},
            {1,5,2,1,2},
            {2,2,2,1,1},
    };
    public int[][] shelf_1 = {
            {0,0,0,0,0},
            {2,0,0,0,0},
            {1,2,0,0,0},
            {2,1,2,0,0},
            {1,5,2,5,0},
            {2,2,2,1,5},
    };

    public int[][] shelf_3 = {
            {0,0,0,0,1},
            {0,0,0,1,2},
            {0,0,4,2,4},
            {0,1,2,2,2},
            {2,5,2,1,1},
            {2,2,2,1,1},
    };
    public int[][] shelf_4 = {
            {0,0,0,0,0},
            {0,0,0,0,5},
            {0,0,0,2,4},
            {0,0,5,2,2},
            {0,2,2,1,1},
            {2,2,2,1,1},
    };

    public int[][] shelf_5 = {
            {0,0,0,0,0},
            {0,0,0,1,2},
            {0,0,0,3,4},
            {0,0,2,2,2},
            {0,2,2,1,1},
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
     * Test the check of the Common Goal Card "Increasing Eight"
     * @author Stefano Morano
     */
    @Test
    void check() {
        setUp(bookshelf_1, shelf_1);
        setUp(bookshelf_2, shelf_2);
        setUp(bookshelf_3, shelf_3);
        setUp(bookshelf_4, shelf_4);
        setUp(bookshelf_5, shelf_5);

        assertTrue(card.check(bookshelf_1));
        assertTrue(card.check(bookshelf_2));
        assertTrue(card.check(bookshelf_3));
        assertTrue(card.check(bookshelf_4));
        assertFalse(card.check(bookshelf_5));
    }
}