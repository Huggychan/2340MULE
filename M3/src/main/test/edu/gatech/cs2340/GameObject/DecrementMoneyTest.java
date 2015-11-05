package edu.gatech.cs2340.GameObject;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Myron on 11/3/2015.
 */
public class DecrementMoneyTest {

    private Player p;

    @Before
    public void setUp() {
        p = new Player("Myron", Race.HUMAN, "Red");
        p.setMoney(100);
        p.setOre(100);
        p.setEnergy(100);
        p.setCrystite(100);
        p.setFood(100);
    }

    @Test
    public void testMoneyGreaterAmount() {
        p.decrementMoney(10);
        assertEquals(p.getMoney(), 90);
        assertEquals(p.getOre(), 100);
        assertEquals(p.getFood(), 100);
        assertEquals(p.getEnergy(), 100);
        assertEquals(p.getCrystite(), 100);
        // player has enough money to buy,
        // so should decrement only money by 10 to 90
        // no change to resources
    }

    @Test
    public void testMoneyLessAmount() {
        p.decrementMoney(110);
        assertEquals(p.getMoney(), 100);
        assertEquals(p.getOre(), 100);
        assertEquals(p.getFood(), 100);
        assertEquals(p.getEnergy(), 100);
        assertEquals(p.getCrystite(), 100);
        // player does not have enough money to buy,
        // so should not decrement anything
        // no change to resources
    }

    @Test
    public void testMoneyEqualAmount() {
        p.decrementMoney(100);
        assertEquals(p.getMoney(), 0);
        assertEquals(p.getOre(), 100);
        assertEquals(p.getFood(), 100);
        assertEquals(p.getEnergy(), 100);
        assertEquals(p.getCrystite(), 100);
        // player has exact amount of money,
        // so should show money equal to 0
        // no change to resources
    }
}
