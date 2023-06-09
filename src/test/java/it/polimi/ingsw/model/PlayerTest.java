package it.polimi.ingsw.model;

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
        player = new Player("Gino");
    }

    @AfterEach
    void tearDown() {
        player = null;
    }

    @Test
    void getBookshelfTest() {
        assertNotEquals(null, player.getBookshelf());
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

    @Test
    void getPersonalGoalCardTest() {
        player.setPersonalGoalCard(new PersonalGoalCard(PersonalGoalCardType.GOAL3));
        assertEquals(PersonalGoalCardType.GOAL3, player.getPersonalGoalCard().getType());
    }

    @Test
    void getNicknameTest() {
        assertEquals("Gino", player.getNickname());
    }
}