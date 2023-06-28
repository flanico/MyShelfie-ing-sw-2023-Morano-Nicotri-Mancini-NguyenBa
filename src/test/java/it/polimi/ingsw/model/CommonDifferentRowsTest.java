package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CommonDifferentRowsTest {
    private Bookshelf bookshelf = new Bookshelf();
    public CommonGoalCard card = new CommonDifferentRows();
    public int[][] shelf = {
            {1,5,0,0,0},
            {2,1,0,0,0},
            {3,2,2,2,0},
            {4,3,2,2,0},
            {5,4,3,2,6},
            {6,5,4,3,1},
    };
    /**
     * Create the matrix of the bookshelf using the matrix of integer "shelf"
     * @author Stefano Morano
     */
    @BeforeEach
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
    /**
     * Test the check of the Common Goal Card "Different Rows"
     * @author Stefano Morano
     */
    @Test
    void check() {                                  //testing the check of the common goal card
        assertTrue(card.check(bookshelf));
    }

    /**
     * Test if the number of the common goal is correct
     * @author Flavia Nicotri
     */
    @Test
    void getNumberTest() {
        assertEquals(6, card.getNumber());
    }

    @Test
    void toStringTest() {
        assertEquals("Common Goal Card: Two lines each formed by 5 different types of tiles. One line can show the same or a different combination of the other line.", card.toString());
    }
}