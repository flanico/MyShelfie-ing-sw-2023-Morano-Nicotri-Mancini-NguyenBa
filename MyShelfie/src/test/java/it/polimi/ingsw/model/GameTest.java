package it.polimi.ingsw.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class tests the Game methods
 * @author Chiara Nguyen Ba
 */
class GameTest{

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        game.initGame(3);
        game.addPlayer("Gino");
        game.addPlayer("Anna");
        game.addPlayer("Leo");
    }

    @AfterEach
    void tearDown() {
        game = null;
    }

    @Test
    void initGameTest() {
        assertNotNull(game);
    }

    @Test
    void getCurrentNumTest() {
        game.addPlayer("Bubu");
        assertEquals(4, game.getCurrentNum());
    }

    @Test
    void getNumTest() {
        assertEquals(3, game.getNum());
    }

    @Test
    void setNumTest() {
        game.setNum(2);
        assertEquals(2, game.getNum());
    }

    @Test
    void isNicknameTaken_true() {
        assertTrue(game.isNicknameTaken("Gino"));
    }

    @Test
    void isNicknameTaken_false() {
        assertFalse(game.isNicknameTaken("Sara"));
    }

    @Test
    void addPlayerTest() {
        game.addPlayer("Tina");
        assertEquals(4, game.getCurrentNum());
    }

    @Test
    void getPlayerByNicknameTest() {
        assertNotNull(game.getPlayerByNickname("Gino"));
        assertNull(game.getPlayerByNickname("Sara"));
    }

    @Test
    void getAllPlayersTest() {
        assertEquals(List.of("Gino", "Anna", "Leo"), game.getAllPlayers());
    }

    @Test
    void getPlayersTest() {
        assertNotNull(game.getPlayers());
    }

    @Test
    void getPersonalgoalcardsTest() {
        assertNotNull(game.getPersonalgoalcards());
        assertEquals(3, game.getPersonalgoalcards().size());
    }

    @Test
    void getCommongoalcardsTest() {
        assertEquals(2, game.getCommongoalcards().size());
        assertNotEquals(game.getCommongoalcards().get(0), game.getCommongoalcards().get(1));
    }

    @Test
    void getCommongoalcardscoresTest() {
        assertEquals(2, game.getCommongoalcardscores().size());
    }

    @Test
    void getBoardTest() {
        assertNotEquals(null, game.getBoard());
        assertEquals(9, game.getBoard().getCOL());
        assertEquals(9, game.getBoard().getROW());
    }

    @Test
    void getBagTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Game game = new Game();
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