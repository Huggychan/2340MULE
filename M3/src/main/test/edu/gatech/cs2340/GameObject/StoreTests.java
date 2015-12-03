package edu.gatech.cs2340.GameObject;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

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
        map.put(ResourceType.CRYSTITE, 5);
        map.put(ResourceType.ENERGY, 5);
    }

    @Test
     public void testIncrementKeyInMap() {
        //THESE ARE PRIVATE METHODS WHY DID YOU TRY TO TEST THEM
        // idk im super sleepy
//        incrementKeyInMap(ResourceType.CRYSTITE, map);
//        assertEquals(map.get(ResourceType.CRYSTITE), 6);
    }

    @Test
    public void testDecrementKeyInMap() {
//        decrementKeyInMap(ResourceType.ENERGY, map);
//        assertEquals(map.get(ResourceType.ENERGY), 4);
    }
}
