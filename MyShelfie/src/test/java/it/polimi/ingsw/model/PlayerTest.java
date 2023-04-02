package it.polimi.ingsw.model;

import it.polimi.ingsw.Bookshelf;
import it.polimi.ingsw.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class tests the Player methods
 * @author Chiara Nguyen Ba
 */
class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    @AfterEach
    void tearDown() {
        player = null;
    }

    @Test
    void getBookshelfTest() {
        Bookshelf bookshelf = player.getBookshelf();
        Bookshelf bookshelf1 = new Bookshelf();
        assertEquals(bookshelf, player.getBookshelf());
        assertNotEquals(bookshelf1, player.getBookshelf());
    }

    @Test
    void getScoreTest() {
        player.setScore(3);
        assertEquals(3, player.getScore());
    }

    @Test
    void setScoreTest() {
        player.setScore(8);
        assertEquals(8, player.getScore());
    }
}