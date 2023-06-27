package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonTwoSquaresTest {
    private Bookshelf bookshelf = new Bookshelf();
    private Bookshelf bookshelf_2 = new Bookshelf();
    public CommonGoalCard card = new CommonTwoSquares();
    public int[][] shelf_1 = {
            {2,2,3,0,1},
            {2,2,4,4,2},
            {1,1,3,2,4},
            {2,1,2,2,2},
            {1,5,2,1,1},
            {2,2,2,1,1},
    };
    public int[][] shelf_2 = {
            {1,4,3,0,1},
            {2,1,5,4,2},
            {1,2,1,2,4},
            {1,1,1,3,2},
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
     * Test the check of the Common Goal Card "Two Squares"
     * @author Stefano Morano
     */
    @Test
    void check() {
        setUp(bookshelf, shelf_1);
        setUp(bookshelf_2, shelf_2);

        assertTrue(card.check(bookshelf));
        assertFalse(card.check(bookshelf_2));
    }

    /**
     * Test if the number of the common goal is correct
     * @author Flavia Nicotri
     */
    @Test
    void getNumberTest() {
        assertEquals(1, card.getNumber());
    }
}