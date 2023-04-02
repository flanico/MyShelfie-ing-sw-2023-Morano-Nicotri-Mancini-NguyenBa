package it.polimi.ingsw.model;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.Tile;
import it.polimi.ingsw.TileType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class tests the Game methods
 * @author Chiara Nguyen Ba
 */
class GameTest{

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(3);
    }

    @AfterEach
    void tearDown() {
        game = null;
    }

    @Test
    void getNumTest() {
        assertEquals(3, game.getNum());
    }

    @Test
    void getPlayersTest() {
    }

    @Test
    void getPersonalgoalcardsTest() {
    }

    @Test
    void getCommongoalcardsTest() {
    }

    @Test
    void getCommongoalcardscoresTest() {
    }

    @Test
    void getBoardTest() {
    }

    @Test
    void getBagTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Game game = new Game(2);
        Method method = Game.class.getDeclaredMethod("initBag");
        method.setAccessible(true);
        method.invoke(game);
        Stack<Tile> bag = game.getBag();
        // assert that the tiles are 132
        assertEquals(132, bag.size());
        // assert that there are 22 tiles of each type
        assertEquals(22, bag.stream().filter(t -> t.getType() == TileType.CAT).count());
        assertEquals(22, bag.stream().filter(t -> t.getType() == TileType.GAME).count());
        assertEquals(22, bag.stream().filter(t -> t.getType() == TileType.BOOK).count());
        assertEquals(22, bag.stream().filter(t -> t.getType() == TileType.PLANT).count());
        assertEquals(22,bag.stream().filter(t -> t.getType() == TileType.TROPHY).count());
        assertEquals(22,bag.stream().filter(t -> t.getType() == TileType.FRAME).count());
        // assert that the tiles are shuffled
        Stack<Tile> originalbag = new Stack<>();
        originalbag.addAll(bag);
        Collections.shuffle(originalbag);
        assertNotEquals(bag, originalbag);
    }
}