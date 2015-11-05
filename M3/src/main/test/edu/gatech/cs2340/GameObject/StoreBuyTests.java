package edu.gatech.cs2340.GameObject;

/**
 * Created by Nick on 11/3/2015.
 */
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

//import edu.gatech.cs2340.GameObject.Player;
//import edu.gatech.cs2340.GameObject.Race;
//import edu.gatech.cs2340.GameObject.ResourceType;
//import edu.gatech.cs2340.GameObject.Store;

public class StoreBuyTests {
    private Store store;
    private Player p1;
    private Player p2;
    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        store = new Store();
        p1 = new Player("1", Race.BONZOID, "Red");
        p2 = new Player("2", Race.BONZOID, "Blue");
    }

    @Test(timeout = TIMEOUT)
    public void testNotEnoughMoneyOrNotInStock() {
        p1.setMoney(0);
        p1.setFood(0);
        store.buy(ResourceType.FOOD, p1);
        assertEquals(0, p1.getFood());
        assertEquals(16, store.getFoodCount());
        //enough money, in stock

        p1.setMoney(store.getFoodPrice() * store.getFoodCount());
        while (store.getFoodCount() > 0) {
            store.buy(ResourceType.FOOD, p1);
        }
        p2.setMoney(30000);
        p2.setFood(0);
        store.buy(ResourceType.FOOD, p2);
        assertEquals(0, p2.getFood());
        //enough money, not in stock
    }

    @Test(timeout = TIMEOUT)
    public void testIfNotMule() {
        p1.setMoney(store.getFoodPrice());
        //System.out.println(store.getFoodCount()); 16
        p1.setFood(0);
        store.buy(ResourceType.FOOD, p1);
        System.out.println(p1.getMoney());
        assertEquals(0, p1.getMoney());
        assertEquals(1, p1.getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testIfMuleEnoughMoney() { //the graphics are not initialized
        p1.setMoney(2 * store.getBaseMulePrice());
        assertTrue(p1.getMule() == null);
        store.buy(ResourceType.MULE, p1);
        assertTrue(p1.getMule() != null);
        p1.setMule(null);
        store.buy(ResourceType.MULE, p1);
        assertTrue(p1.getMuleBoughtThisTurn());
        assertNull(p1.getMule());
        assertEquals(store.getBaseMulePrice(), p1.getMoney());
    }
    //TestMuleNotEnoughMoney

}

