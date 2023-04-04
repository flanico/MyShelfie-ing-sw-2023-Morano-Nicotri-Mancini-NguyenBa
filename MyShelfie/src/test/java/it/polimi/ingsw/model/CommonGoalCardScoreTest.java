package it.polimi.ingsw.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class tests the CommonGoalCardScore methods
 * @author Chiara Nguyen Ba
 */
class CommonGoalCardScoreTest {

    private CommonGoalCardScore commongoalcardscore;
    @BeforeEach
    void setUp() {
        commongoalcardscore = new CommonGoalCardScore(4);
    }

    @AfterEach
    void tearDown() {
        commongoalcardscore = null;
    }

    @Test
    void getStackTest() {
        assertEquals(4, commongoalcardscore.getStack().size());
        assertEquals(8, commongoalcardscore.getStack().pop());
        assertEquals(6, commongoalcardscore.getStack().pop());
        assertEquals(4, commongoalcardscore.getStack().pop());
        assertEquals(2, commongoalcardscore.getStack().pop());
        assertTrue(commongoalcardscore.getStack().isEmpty());
    }
}