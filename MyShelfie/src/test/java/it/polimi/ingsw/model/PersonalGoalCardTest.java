package it.polimi.ingsw.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class tests the Personal Goal Card methods
 * @author Chiara Nguyen Ba
 */
class PersonalGoalCardTest {

    private PersonalGoalCard personalgoalcard;
    private Player player;

    @BeforeEach
    void setUp() {
        //player = new Player();
        personalgoalcard = new PersonalGoalCard(PersonalGoalCardType.GOAL1, player);
    }

    @AfterEach
    void tearDown() {
        player = null;
        personalgoalcard = null;
    }

    @Test
    void getMatrixTest() {
        assertNotEquals(null, personalgoalcard.getMatrix());
    }

    @Test
    void getTypeTest() {
        assertEquals(PersonalGoalCardType.GOAL1, personalgoalcard.getType());
    }

    @Test
    void checkTest() {
        assertEquals(0, personalgoalcard.check());

        player.getBookshelf().getMatrix()[1][4].setType(TileType.CAT);
        assertEquals(1, personalgoalcard.check());

        player.getBookshelf().getMatrix()[0][0].setType(TileType.PLANT);
        assertEquals(2, personalgoalcard.check());

        player.getBookshelf().getMatrix()[2][3].setType(TileType.BOOK);
        assertEquals(4, personalgoalcard.check());

        player.getBookshelf().getMatrix()[0][2].setType(TileType.FRAME);
        assertEquals(6, personalgoalcard.check());

        player.getBookshelf().getMatrix()[5][2].setType(TileType.TROPHY);
        assertEquals(9, personalgoalcard.check());

        player.getBookshelf().getMatrix()[3][1].setType(TileType.GAME);
        assertEquals(12, personalgoalcard.check());
    }
}