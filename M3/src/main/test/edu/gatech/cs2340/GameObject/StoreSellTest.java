package edu.gatech.cs2340.GameObject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoreSellTest {

    private Store store;
    private Player realPlayer;
    private ResourceType realType;
    public final static int TIMEOUT = 200;

    @Before
    public void setUp() {
        realPlayer = new Player("Marc", Race.HUMAN, "Red");
        realType = ResourceType.ENERGY;
        store = new Store();
    }

    @Test (expected=IllegalArgumentException.class)
    public void testNullPlayer() {
        store.sell(realType, null);
    }

    @Test (expected=IllegalArgumentException.class)
    public void testNullProduct() {
        store.sell(null, realPlayer);
    }

    @Test (expected=IllegalArgumentException.class)
    public void testNullBoth() {
        store.sell(null, null);
    }

    @Test (timeout = TIMEOUT)
    public void testSellEmpty() {
        while (realPlayer.getEnergy() > 0) {
            store.sell(realType, realPlayer);
        }

        assertEquals(0, realPlayer.getEnergy());
        boolean success = store.sell(realType, realPlayer);
        assertFalse(success);
    }

    @Test
    public void testSellMule() {
        assertNull(realPlayer.getMule());
        boolean success = store.sell(ResourceType.MULE, realPlayer);
        assertFalse(success);
    }

    @Test (timeout = TIMEOUT)
    public void testNormalSell() {
        realPlayer.setEnergy(2);
        boolean success = store.sell(realType, realPlayer);
        assertTrue(success);
        assertEquals(1, realPlayer.getEnergy());
    }
}