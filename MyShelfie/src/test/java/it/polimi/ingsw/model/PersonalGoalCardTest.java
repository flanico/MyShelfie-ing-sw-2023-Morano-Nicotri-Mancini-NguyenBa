package it.polimi.ingsw.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalGoalCardTest {

    private PersonalGoalCard personalGoalCard;
    private Player player;
    @BeforeEach
    void setUp() {
        player = new Player();
        personalGoalCard = new PersonalGoalCard(PersonalGoalCardType.GOAL1, player);
    }

    @AfterEach
    void tearDown() {
        player = null;
        personalGoalCard = null;
    }

    @Test
    void getMatrix() {

    }

    @Test
    void getTypeTest() {
        assertEquals(PersonalGoalCardType.GOAL1, personalGoalCard.getType());
    }

    @Test
    void assignType() {

    }

    @Test
    void check() {

    }
}