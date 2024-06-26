package it.polimi.ingsw.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class tests the Tile methods
 * @author Chiara Nguyen Ba
 */
class TileTest {
    private Tile tile;
    private Tile tile2;

    @BeforeEach
    void setUp() {
        tile = new Tile(TileType.BOOK, 1);
        tile2 = new Tile(TileType.GAME, 1,2, 3);
    }

    @AfterEach
    void tearDown() {
        tile = null;
        tile2 = null;
    }

    @Test
    void getTypeTest() {
        assertEquals(TileType.BOOK, tile.getType());
    }

    @Test
    void setTypeTest() {
        tile.setType(TileType.PLANT);
        assertEquals(TileType.PLANT, tile.getType());
    }

    @Test
    void getXTest() {
        assertEquals(2, tile2.getX());
    }

    @Test
    void setXTest() {
        tile2.setX(5);
        assertEquals(5, tile2.getX());
    }

    @Test
    void getYTest() {
        assertEquals(3, tile2.getY());
    }

    @Test
    void setYTest() {
        tile2.setY(6);
        assertEquals(6, tile2.getY());
    }

    @Test
    void isBlockedTest() {
        assertFalse(tile.isBlocked());
    }

    @Test
    void setBlockedTest() {
        tile.setBlocked(true);
        assertTrue(tile.isBlocked());
    }

    @Test
    void isCountedTest() {
        assertFalse(tile.isCounted());
    }

    @Test
    void setCountedTest() {
        tile.setCounted(true);
        assertTrue(tile.isCounted());
    }

    @Test
    void toStringTest() {
        assertEquals("\u001B[48;5;230m B \u001B[0m", tile.toString());
    }
}