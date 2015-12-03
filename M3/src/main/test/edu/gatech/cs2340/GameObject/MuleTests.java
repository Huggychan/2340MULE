package edu.gatech.cs2340.GameObject;

import javafx.scene.paint.Color;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Shyam
 * @version 1.0
 */

public class MuleTests {

    private Player player;
    private Mule mule;

    @Before
    public void setup() {
        player = new Player("Player", Race.FLAPPER, "Red");
        mule = new Mule(player);
    }

    @Test
    public void testSetResourceType() {
        mule.setResourceType(ResourceType.CRYSTITE);
        assertEquals(ResourceType.CRYSTITE, mule.getResourceType());
        mule.setResourceType(ResourceType.DIAMOND);
        assertEquals(ResourceType.DIAMOND, mule.getResourceType());
        mule.setResourceType(ResourceType.ELECTRICITY);
        assertEquals(ResourceType.ELECTRICITY, mule.getResourceType());
        mule.setResourceType(ResourceType.ENERGY);
        assertEquals(ResourceType.ENERGY, mule.getResourceType());
        mule.setResourceType(ResourceType.FOOD);
        assertEquals(ResourceType.FOOD, mule.getResourceType());
        mule.setResourceType(ResourceType.MULE);
        assertEquals(ResourceType.MULE, mule.getResourceType());
        mule.setResourceType(ResourceType.ORE);
        assertEquals(ResourceType.ORE, mule.getResourceType());
    }

}