package edu.gatech.cs2340.GameObject;

import edu.gatech.cs2340.Game;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * @author Bilal
 * @version 1.0
 */
public class PlayerEqualsTest {

    private String string;
    private String string2;
    private String temp;
    private String nullString;
    private Game game;
    private Player player1;
    private Player player2;

    @Before
    public void setup() {
        string = "This was a setup! It's a trap!";
        string2 = "Is JUnit working?";
        temp = "temp";
        player1 = new Player("Player 1", Race.HUMAN, "Red");
        player2 = new Player("Player 1", Race.HUMAN, "Red");
    }

    @Test
    public void testBasic() {
        assertNull(nullString);
        assertEquals("Is JUnit working?", string2);
        assertNotEquals(string, string2);
        assertSame("temp", temp);
    }

    @Test
    public void testPlayerEqualsSelf() {
        assertEquals(player1.equals(player1), true);

    }

    @Test
    public void testPlayerEqualsObject() {
        assertFalse(player1.equals(new Object()));
    }

    @Test
    public void testPlayerEqualsString() {
        assertFalse(player1.equals(new String("Bob")));
    }

    @Test
    public void testPlayerEqualsSameColorSameName() {
        assertTrue(player1.equals(player2));
    }

    @Test
    public void testPlayerEqualsSameColorDiffName() {
        player2.setName("Player 2");
        assertTrue(player1.equals(player2));
    }

    @Test
    public void testPlayerEqualsDiffColorDiffName() {
        player2.setName("Player 2");
        player2.setColor("Blue");
        assertNotEquals(player1.equals(player2), true);
    }

    @Test
    public void testPlayerEqualsDiffColorDiffNameDiffRace() {
        player2.setName("Player 2");
        player2.setColor("Blue");
        player2.setRace(Race.BONZOID);
        assertFalse(player1.equals(player2));
    }

}