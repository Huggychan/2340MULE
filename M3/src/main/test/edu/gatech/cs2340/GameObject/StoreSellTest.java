package edu.gatech.cs2340.GameObject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * @author Marc
 */
public class StoreSellTest {

    private Store store;
    private Player realPlayer;
    private ResourceType realType;
    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        realPlayer = new Player("Marc", Race.HUMAN, "Red");
        realType = ResourceType.ENERGY;
        store = new Store();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullPlayer() {
        store.sell(realType, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullProduct() {
        store.sell(null, realPlayer);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullBoth() {
        store.sell(null, null);
    }

    @Test (timeout = TIMEOUT)
    public void testSellEmpty() {
        while (realPlayer.getEnergy() > 0) {
            store.sell(realType, realPlayer);
        }

        assertEquals(0, realPlayer.getEnergy());

        int playerMoney = realPlayer.getMoney();
        boolean success = store.sell(realType, realPlayer);

        assertEquals(playerMoney, realPlayer.getMoney());
        assertFalse(success);
    }

    @Test
    public void testSellMule() {
        assertNull(realPlayer.getMule());

        boolean success = store.sell(ResourceType.MULE, realPlayer);

        assertFalse(success);

        realPlayer.giveMule(); //NEED TO COMENT OUT GRAPHICS STUFF IN MULE
        assertNotNull(realPlayer.getMule());
        success = store.sell(ResourceType.MULE, realPlayer);

        assertTrue(success);
        assertNull(realPlayer.getMule());
    }

    @Test (timeout = TIMEOUT)
    public void testNormalSell() {
        realPlayer.setEnergy(2);
        int storeEnergy = store.getEnergyCount();
        int playerMoney = realPlayer.getMoney();
        boolean success = store.sell(realType, realPlayer);

        assertTrue(success);
        assertEquals(1, realPlayer.getEnergy());
        assertEquals(storeEnergy + 1, store.getEnergyCount());
        assertEquals(playerMoney + store.getEnergyPrice(),
                realPlayer.getMoney());
    }
}
