package edu.gatech.cs2340;

import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.GameObject.Race;
import edu.gatech.cs2340.GameObject.ResourceType;
import edu.gatech.cs2340.GameObject.Store;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoreSellTest {

    private Store store;
    private Player realPlayer, nullPlayer;
    private ResourceType realType, nullType;

    @Before
    public void setup() {
        Player realPlayer = new Player("Marc", Race.HUMAN, "Red");
        Player nullPlayer = null;
        ResourceType realType = ResourceType.ENERGY;
        ResourceType nullType = null;
        Store store = new Store();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullPlayer() {
        assertNull(nullPlayer);
        store.sell(realType, nullPlayer);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullProduct() {
        assertNull(nullType);
        store.sell(nullType, realPlayer);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullBoth() {
        assertNull(nullType);
        assertNull(nullPlayer);
        store.sell(nullType, nullPlayer);

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