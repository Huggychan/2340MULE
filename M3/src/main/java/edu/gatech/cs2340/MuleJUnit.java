package edu.gatech.cs2340;

import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.GameObject.Race;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class MuleJUnit {

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
        //player1 = new Player("Player 1", Race.HUMAN, "Red", game);
        //player2 = new Player("Player 1", Race.HUMAN, "Red", game);
    }

    @Test
    public void testBasic() {
        assertNull(nullString);
        assertEquals("Is JUnit working?", string2);
        assertNotEquals(string, string2);
        assertSame("temp", temp);
    }

    @Test
    public void testPlayerEquals() {
//        Bilal JUnit Test

//        this == itself returns true
        assertEquals(player1.equals(player1), true);

//        this compared with Object returns false
        assertFalse(player1.equals(new Object()));

//        this compared with String returns false
        assertFalse(player1.equals(new String("Bob")));

//        this compared with other Player (same color && same name) returns true
        assertTrue(player1.equals(player2));

        player2.setName("Player 2");
//        this compared with other Player (same color && diff name) returns true
        assertTrue(player1.equals(player2));

        player2.setColor("Blue");
//        this compare w other Player (diff color && diff name) returns false
        assertNotEquals(player1.equals(player2), true);

        player2.setRace(Race.BONZOID);
//        this compare w other Player (diff color, diff name, diff Race) false
        assertFalse(player1.equals(player2));
    }


}