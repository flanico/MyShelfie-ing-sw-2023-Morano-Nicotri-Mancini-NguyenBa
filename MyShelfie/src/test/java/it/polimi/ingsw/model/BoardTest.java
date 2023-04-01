package it.polimi.ingsw.model;

import it.polimi.ingsw.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class tests the Board methods
 * @author Chiara Nguyen Ba
 */
class BoardTest {

    private Board board;
    @BeforeEach
    void setUp() {
        board = new Board(3);
    }

    @AfterEach
    void tearDown() {
        board = null;
    }

    @Test
    void getMatrixTest() {
    }

    @Test
    void getROWTest() {
        assertEquals(9, board.getROW());
    }

    @Test
    void getCOLTest() {
        assertEquals(9, board.getCOL());
    }

    @Test
    void fillBoardTest() {
    }

    @Test
    void isRefillableTest() {
    }

    @Test
    void selectTileTest() {
    }

    @Test
    void removeTilesTest() {
    }

}