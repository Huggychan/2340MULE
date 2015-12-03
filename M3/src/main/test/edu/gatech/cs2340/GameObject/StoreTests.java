package edu.gatech.cs2340.GameObject;

import java.util.HashMap;

import edu.gatech.cs2340.GameObject.ResourceType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * @author Shyam
 * @version 1.0
 */

public class StoreTests {

    private Store store;
    private Player player;
    private ResourceType type;
    HashMap<ResourceType, Integer> map;

    @Before
    public void setUp() {
        player = new Player("Tester", Race.BONZOID, "Yellow");
        type = ResourceType.CRYSTITE;
        store = new Store();
        map = new HashMap<ResourceType, Integer>();
        map.add(ResourceType.CRYSTITE, 5);
        map.add(ResourceType.ENERGY, 5);
    }

    @Test
     public void testIncrementKeyInMap() {
        incrementKeyInMap(ResourceType.CRYSTITE, map);
        assertEquals(map.get(ResourceType.CRYSTITE), 6);
    }

    @Test
    public void testDecrementKeyInMap() {
        decrementKeyInMap(ResourceType.ENERGY, map);
        assertEquals(map.get(ResourceType.ENERGY), 4);
    }
}
