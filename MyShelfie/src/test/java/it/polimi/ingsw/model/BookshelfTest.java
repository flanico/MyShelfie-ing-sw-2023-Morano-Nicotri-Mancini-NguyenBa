package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class tests the Bookshelf methods
 * @author Chiara Nguyen Ba, Flavia Nicotri
 */
class BookshelfTest {

    private Bookshelf bookshelf;
    private static final int ROW = 6;
    private static final int COL = 5;

    private int[][] shelf = {
            {1,1,1,5,1},
            {2,4,0,2,4},
            {1,2,2,2,4},
            {4,4,4,2,1},
            {1,4,3,2,1},
            {1,5,4,3,1},
    };

    @BeforeEach
    void setUp() {
        bookshelf = new Bookshelf();
    }

    @AfterEach
    void tearDown() {
        bookshelf = null;
    }

    @Test
    void getMatrixTest() {
        assertNotEquals(null, bookshelf.getMatrix());
    }

    @Test
    void insertTileTest() {
        Bookshelf bookshelfcheck = new Bookshelf();
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(TileType.BOOK));
        tiles.add(new Tile(TileType.PLANT));
        tiles.add(new Tile(TileType.CAT));

        bookshelfcheck.getMatrix()[5][4].setType(TileType.BOOK);
        bookshelfcheck.getMatrix()[4][4].setType(TileType.PLANT);
        bookshelfcheck.getMatrix()[3][4].setType(TileType.CAT);
        bookshelf.insertTile(tiles, 4);

        ArrayList<Tile> tiles2 = new ArrayList<>();
        tiles2.add(new Tile(TileType.TROPHY));
        tiles2.add(new Tile(TileType.FRAME));
        bookshelfcheck.getMatrix()[2][4].setType(TileType.TROPHY);
        bookshelfcheck.getMatrix()[1][4].setType(TileType.FRAME);
        bookshelf.insertTile(tiles2, 4);

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                //System.out.print(bookshelf.getMatrix()[i][j].getType() + " ");
                assertEquals(bookshelfcheck.getMatrix()[i][j].getType(), bookshelf.getMatrix()[i][j].getType());
            }
            //System.out.println();
        }
    }

    /**
     * Test the method adjacentCells
     * @author Flavia Nicotri
     */
    @Test
    void adjacentCellsTest() {
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
        /*
            for (int x = 0; x < 6; x++) {
                for (int y = 0; y < 5; y++) {
                    System.out.print(bookshelf.getMatrix()[x][y].getType() + " ");
                }
                System.out.println();
            }
        */
        assertEquals(15, bookshelf.adjacentCells());
    }

    /**
     * Test a matrix that has only a Tile equals to null --> is not Full
     * @author Flavia Nicotri
     */
    @Test
    void isFull_falseTest() {
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
        assertFalse(bookshelf.isFull());
    }


    /**
     * Test a matrix that hasn't any Tile equals to null --> is Full
     * @author Flavia Nicotri
     */
    @Test
    void isFull_trueTest() {
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
        bookshelf.getMatrix()[1][2].setType(TileType.GAME);
        assertTrue(bookshelf.isFull());
    }

    @Test
    void isColFull_trueTest() {
        bookshelf.getMatrix()[5][0].setType(TileType.FRAME);
        bookshelf.getMatrix()[4][0].setType(TileType.GAME);
        bookshelf.getMatrix()[3][0].setType(TileType.GAME);
        bookshelf.getMatrix()[2][0].setType(TileType.BOOK);
        bookshelf.getMatrix()[1][0].setType(TileType.PLANT);
        bookshelf.getMatrix()[0][0].setType(TileType.BOOK);

        assertTrue(bookshelf.isColFull(0));
    }

    @Test
    void isColFull_falseTest() {
        bookshelf.getMatrix()[5][2].setType(TileType.FRAME);
        bookshelf.getMatrix()[4][2].setType(TileType.GAME);
        bookshelf.getMatrix()[3][2].setType(TileType.GAME);
        bookshelf.getMatrix()[2][2].setType(TileType.BOOK);

        assertFalse(bookshelf.isColFull(2));
    }
}