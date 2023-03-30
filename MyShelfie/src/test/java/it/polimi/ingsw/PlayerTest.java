package it.polimi.ingsw;

import junit.framework.TestCase;
import org.junit.Test;

public class PlayerTest extends TestCase {
    Player mario = new Player("Mario");

    @Test
    public void testGetNickname() {
        mario.getNickname();
    }

    @Test
    public void testGetBookshelf() {
    }

    @Test
    public void testGetPersonalgoalcard() {
        System.out.println(mario.getPersonalgoalcard().getType());

    }

    @Test
    public void testSetPersonalgoalcard() {
    }

    @Test
    public void testSelectTile() {
    }
}