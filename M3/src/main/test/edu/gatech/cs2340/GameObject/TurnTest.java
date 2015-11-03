package edu.gatech.cs2340;

import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.Maps.MountainOneTile;
import org.junit.Before;
import edu.gatech.cs2340.GameObject.Race;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Myron on 11/3/2015.
 */
public class TurnTest {

    private Player p;
    private int turnTime;

    @Before
    public void setUp() {

        p = new Player("Myron", Race.HUMAN, "Red");
    }

    @Test
    public void testTimeGreaterThan50Food() {
        p.setFood(13);
        assertEquals(turnTime, 50);
    }

    @Test
    public void testTimeLessThan50Food() {
        p.setFood(9);
        assertEquals(turnTime, 30);
    }

    @Test
         public void testPlayerNoFood() {
        turn.move(MountainOneTile,)
        p.setFood(0);
        assertEquals(turnTime, 50);
    }
}