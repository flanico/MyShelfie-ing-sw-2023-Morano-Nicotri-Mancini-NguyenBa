package it.polimi.ingsw.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class test the CommonFourGroups methods
 * @author Flavia Nicotri
 */

public class CommonFourGroupsTest {
    private Bookshelf bookshelf = new Bookshelf();
    public CommonGoalCard card = new CommonFourGroups();
    public int[][] shelf = {
            {1,1,1,0,1},
            {4,1,2,4,2},
            {2,2,2,7,1},
            {4,4,5,2,1},
            {4,4,3,2,1},
            {1,5,4,3,1},
    };

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

    @AfterEach
    void tearDown(){
        bookshelf = null;
    }

    @Test
    public void check_true(){
        assertTrue(card.check(bookshelf)) ;
    }

    @Test
    public void check_false(){
        bookshelf.getMatrix()[4][0].setType(TileType.NULL);
        assertFalse(card.check(bookshelf)) ;
    }

    /**
     * Test if the number of the common goal is correct
     * @author Flavia Nicotri
     */
    @Test
    void getNumberTest() {
        assertEquals(3, card.getNumber());
    }
}
