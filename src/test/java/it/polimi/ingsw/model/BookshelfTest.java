package it.polimi.ingsw.model;

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

    private final int[][] shelf = {
            {1,1,0,5,1},
            {2,4,2,2,4},
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
        tiles.add(new Tile(TileType.BOOK, 1));
        tiles.add(new Tile(TileType.PLANT, 1));
        tiles.add(new Tile(TileType.CAT, 1));

        bookshelfcheck.getMatrix()[5][4].setType(TileType.BOOK);
        bookshelfcheck.getMatrix()[4][4].setType(TileType.PLANT);
        bookshelfcheck.getMatrix()[3][4].setType(TileType.CAT);
        bookshelf.insertTile(tiles, 4);

        ArrayList<Tile> tiles2 = new ArrayList<>();
        tiles2.add(new Tile(TileType.TROPHY, 1));
        tiles2.add(new Tile(TileType.FRAME, 1));
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
                    case 0 -> bookshelf.getMatrix()[x][y].setType(TileType.NULL);
                    case 1 -> bookshelf.getMatrix()[x][y].setType(TileType.CAT);
                    case 2 -> bookshelf.getMatrix()[x][y].setType(TileType.PLANT);
                    case 3 -> bookshelf.getMatrix()[x][y].setType(TileType.BOOK);
                    case 4 -> bookshelf.getMatrix()[x][y].setType(TileType.FRAME);
                    case 5 -> bookshelf.getMatrix()[x][y].setType(TileType.GAME);
                    case 6 -> bookshelf.getMatrix()[x][y].setType(TileType.TROPHY);
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
        assertEquals(13, bookshelf.adjacentCells());
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
                    case 0 -> bookshelf.getMatrix()[x][y].setType(TileType.NULL);
                    case 1 -> bookshelf.getMatrix()[x][y].setType(TileType.CAT);
                    case 2 -> bookshelf.getMatrix()[x][y].setType(TileType.PLANT);
                    case 3 -> bookshelf.getMatrix()[x][y].setType(TileType.BOOK);
                    case 4 -> bookshelf.getMatrix()[x][y].setType(TileType.FRAME);
                    case 5 -> bookshelf.getMatrix()[x][y].setType(TileType.GAME);
                    case 6 -> bookshelf.getMatrix()[x][y].setType(TileType.TROPHY);
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
                    case 0 -> bookshelf.getMatrix()[x][y].setType(TileType.NULL);
                    case 1 -> bookshelf.getMatrix()[x][y].setType(TileType.CAT);
                    case 2 -> bookshelf.getMatrix()[x][y].setType(TileType.PLANT);
                    case 3 -> bookshelf.getMatrix()[x][y].setType(TileType.BOOK);
                    case 4 -> bookshelf.getMatrix()[x][y].setType(TileType.FRAME);
                    case 5 -> bookshelf.getMatrix()[x][y].setType(TileType.GAME);
                    case 6 -> bookshelf.getMatrix()[x][y].setType(TileType.TROPHY);
                }
            }
        }
        bookshelf.getMatrix()[0][2].setType(TileType.GAME);
        assertTrue(bookshelf.isFull());
    }

    /**
     * Test the max of the selectable tiles
     * @author Flavia Nicotri
     */

    @Test
    void isMaxTiles_OneTest() {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                switch (shelf[x][y]) {
                    case 0 -> bookshelf.getMatrix()[x][y].setType(TileType.NULL);
                    case 1 -> bookshelf.getMatrix()[x][y].setType(TileType.CAT);
                    case 2 -> bookshelf.getMatrix()[x][y].setType(TileType.PLANT);
                    case 3 -> bookshelf.getMatrix()[x][y].setType(TileType.BOOK);
                    case 4 -> bookshelf.getMatrix()[x][y].setType(TileType.FRAME);
                    case 5 -> bookshelf.getMatrix()[x][y].setType(TileType.GAME);
                    case 6 -> bookshelf.getMatrix()[x][y].setType(TileType.TROPHY);
                }
            }
        }

        assertEquals(1, bookshelf.maxTilesBookshelf());
    }
    @Test
    void isMaxTiles_TwoTest() {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                switch (shelf[x][y]) {
                    case 0 -> bookshelf.getMatrix()[x][y].setType(TileType.NULL);
                    case 1 -> bookshelf.getMatrix()[x][y].setType(TileType.CAT);
                    case 2 -> bookshelf.getMatrix()[x][y].setType(TileType.PLANT);
                    case 3 -> bookshelf.getMatrix()[x][y].setType(TileType.BOOK);
                    case 4 -> bookshelf.getMatrix()[x][y].setType(TileType.FRAME);
                    case 5 -> bookshelf.getMatrix()[x][y].setType(TileType.GAME);
                    case 6 -> bookshelf.getMatrix()[x][y].setType(TileType.TROPHY);
                }
            }
        }

        bookshelf.getMatrix()[0][1].setType(TileType.NULL);
        bookshelf.getMatrix()[1][2].setType(TileType.NULL);
        assertEquals(2, bookshelf.maxTilesBookshelf());
    }
    @Test
    void isMaxTiles_ThreeTest() {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                switch (shelf[x][y]) {
                    case 0 -> bookshelf.getMatrix()[x][y].setType(TileType.NULL);
                    case 1 -> bookshelf.getMatrix()[x][y].setType(TileType.CAT);
                    case 2 -> bookshelf.getMatrix()[x][y].setType(TileType.PLANT);
                    case 3 -> bookshelf.getMatrix()[x][y].setType(TileType.BOOK);
                    case 4 -> bookshelf.getMatrix()[x][y].setType(TileType.FRAME);
                    case 5 -> bookshelf.getMatrix()[x][y].setType(TileType.GAME);
                    case 6 -> bookshelf.getMatrix()[x][y].setType(TileType.TROPHY);
                }
            }
        }

        bookshelf.getMatrix()[0][1].setType(TileType.NULL);
        bookshelf.getMatrix()[1][2].setType(TileType.NULL);
        bookshelf.getMatrix()[2][2].setType(TileType.NULL);
        assertEquals(3, bookshelf.maxTilesBookshelf());
    }
}