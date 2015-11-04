package edu.gatech.cs2340.GameObject;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created by Myron on 11/3/2015.
 */
public class DecrementMoneyTest {

    private Player p;

    @Before
    public void setUp() {
        p = new Player("Myron", Race.HUMAN, "Red");
        p.setMoney(100);
    }

    @Test
    public void testMoneyGreaterAmount() {
        p.decrementMoney(10);
        assertEquals(p.getMoney(), 90);
    }

    @Test
    public void testMoneyLessAmount() {
        p.decrementMoney(110);
        assertEquals(p.getMoney(), 0);
    }
}