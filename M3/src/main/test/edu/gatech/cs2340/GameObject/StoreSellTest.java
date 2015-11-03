package edu.gatech.cs2340.GameObject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoreSellTest {

    private Store store;
    private Player realPlayer;
    private ResourceType realType;

    @Before
    public void setUp() {
        Player realPlayer = new Player("Marc", Race.HUMAN, "Red");
        ResourceType realType = ResourceType.ENERGY;
        Store store = new Store();
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

    @Test
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

        realPlayer.giveMule();
        assertNotNull(realPlayer.getMule());
        success = store.sell(ResourceType.MULE, realPlayer);
        assertTrue(success);
    }

    @Test
    public void testNormalSell() {
        boolean success = store.sell(realType, realPlayer);
        assertTrue(success);
    }
}