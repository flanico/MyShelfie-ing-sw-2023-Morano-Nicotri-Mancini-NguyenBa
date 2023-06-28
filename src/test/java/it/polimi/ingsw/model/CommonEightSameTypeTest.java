package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
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
            {1,2,2,2,1},
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
     * Test the check of the Common Goal Card "Eight Same Type"
     * @author Stefano Morano
     */
    @Test
    void check_TrueTest() {
      assertTrue(card.check(bookshelf));
    }

    /**
     * Test if the number of the common goal is correct
     * @author Flavia Nicotri
     */
    @Test
    void getNumberTest() {
        assertEquals(9, card.getNumber());
    }

    @Test
    void check_FalseTest() {
        bookshelf.getMatrix()[1][0].setType(TileType.NULL);
        assertFalse(card.check(bookshelf));
    }

    @Test
    void toStringTest() {
        assertEquals("Common Goal Card: Eight tiles of the same type. \nThere’s no restriction about the position of these tiles.", card.toString());
    }
}