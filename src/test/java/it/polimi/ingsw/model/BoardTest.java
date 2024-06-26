package it.polimi.ingsw.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class tests the Board methods
 * @author Chiara Nguyen Ba
 */
class BoardTest {

    private Board board;
    private Game game;
    private static final int ROW = 9;
    private static final int COL = 9;

    @BeforeEach
    void setUp() {
        board = new Board(3);
        game = new Game();
        game.initGame(3);
    }

    @AfterEach
    void tearDown() {
        board = null;
        game = null;
    }

    @Test
    void getMatrixTest() {
        Tile[][] matrix = board.getMatrix();
        assertNotEquals(null, board.getMatrix());
        assertTrue(Arrays.stream(matrix).flatMap(Arrays::stream).allMatch(tile -> tile.getType().equals(TileType.NULL)));
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
        game.getBoard().fillBoard(game.getBag());
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if(!game.getBoard().getMatrix()[i][j].isBlocked()){
                    assertNotEquals(TileType.NULL, game.getBoard().getMatrix()[i][j].getType());
                }
            }
        }
    }

    @Test
    void isRefillableTest_false() {
        assertFalse(game.getBoard().isRefillable());

        board.getMatrix()[3][2].setType(TileType.BOOK);
        //These two tiles are near
        board.getMatrix()[6][4].setType(TileType.CAT);
        board.getMatrix()[5][4].setType(TileType.PLANT);

        board.getMatrix()[5][2].setType(TileType.TROPHY);
        board.getMatrix()[6][6].setType(TileType.BOOK);
        assertFalse(board.isRefillable());
    }

    @Test
    void isRefillableTest_true() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                game.getBoard().getMatrix()[i][j].setType(TileType.NULL);
            }
        }
        assertTrue(game.getBoard().isRefillable());

        board.getMatrix()[3][2].setType(TileType.BOOK);
        board.getMatrix()[6][4].setType(TileType.CAT);
        board.getMatrix()[3][5].setType(TileType.PLANT);
        board.getMatrix()[5][2].setType(TileType.TROPHY);
        board.getMatrix()[6][6].setType(TileType.BOOK);
        assertTrue(board.isRefillable());
    }

    @Test
    void selectTileTest() {
    }

    @Test
    void removeTilesTest_TwoTiles() {
        ArrayList <Tile> tiles = new ArrayList<>();
        Tile t1 = new Tile(TileType.NULL, 1, 1,3);
        t1.setType(game.getBoard().getMatrix()[1][3].getType());
        tiles.add(t1);
        Tile t2 = new Tile(TileType.NULL, 1, 1,4);
        t2.setType(game.getBoard().getMatrix()[1][4].getType());
        tiles.add(t2);
        game.getBoard().removeTiles(tiles);
        assertEquals(game.getBoard().getMatrix()[1][3].getType(), TileType.NULL);
        assertEquals(game.getBoard().getMatrix()[1][4].getType(), TileType.NULL);
    }

    @Test
    void removeTilesTest_ThreeTiles() {
        game.getBoard().getMatrix()[1][4].setType(TileType.NULL);
        game.getBoard().getMatrix()[2][4].setType(TileType.NULL);
        game.getBoard().getMatrix()[2][2].setType(TileType.NULL);

        ArrayList <Tile> tiles = new ArrayList<>();
        Tile t1 = new Tile(TileType.NULL, 1, 0,3);
        t1.setType(game.getBoard().getMatrix()[0][3].getType());
        tiles.add(t1);
        Tile t2 = new Tile(TileType.NULL, 1, 2,3);
        t2.setType(game.getBoard().getMatrix()[2][3].getType());
        tiles.add(t2);
        Tile t3 = new Tile(TileType.NULL, 1, 1,3);
        t3.setType(game.getBoard().getMatrix()[1][3].getType());
        tiles.add(t3);
        game.getBoard().isRemovable(tiles);

        game.getBoard().removeTiles(tiles);
        assertEquals(game.getBoard().getMatrix()[0][3].getType(), TileType.NULL);
        assertEquals(game.getBoard().getMatrix()[2][3].getType(), TileType.NULL);
        assertEquals(game.getBoard().getMatrix()[1][3].getType(), TileType.NULL);
    }

    /**
     * test the method maxTilesBoard
     * @author Flavia Nicotri
     */

    @Test
    void maxTilesBoard_TwoTest(){

        board.getMatrix()[4][5].setType(TileType.BOOK);
        board.getMatrix()[4][6].setType(TileType.BOOK);


        board.getMatrix()[5][2].setType(TileType.TROPHY);
        board.getMatrix()[6][6].setType(TileType.BOOK);

        assertEquals(2, board.maxTilesBoard());
    }


    @Test
    void maxTilesBoard_OneTest(){
        board.getMatrix()[3][2].setType(TileType.BOOK);

        board.getMatrix()[6][4].setType(TileType.CAT);


        board.getMatrix()[5][2].setType(TileType.TROPHY);
        board.getMatrix()[6][6].setType(TileType.BOOK);

        assertEquals(1, board.maxTilesBoard());
    }

    @Test
    void maxTilesBoard_ThreeTest(){
        board.getMatrix()[3][2].setType(TileType.BOOK);

        board.getMatrix()[6][4].setType(TileType.CAT);
        board.getMatrix()[5][4].setType(TileType.PLANT);
        board.getMatrix()[4][4].setType(TileType.PLANT);

        board.getMatrix()[5][2].setType(TileType.TROPHY);

        board.getMatrix()[6][5].setType(TileType.PLANT);
        board.getMatrix()[6][6].setType(TileType.BOOK);

        assertEquals(3, board.maxTilesBoard());
    }
}