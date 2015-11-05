package edu.gatech.cs2340.GameObject;

import edu.gatech.cs2340.GameObject.ResourceType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetResourceTypeFromStringTest {

    private StoreController storeController;

    @Before
    public void setUp() {
        storeController = new StoreController();
    }

    @Test (expected=IllegalArgumentException.class)
    public void testNullString() {
        storeController.getResourceTypeFromString(null);
    }

    @Test (expected=IllegalArgumentException.class)
    public void testWrongString() {
        storeController.getResourceTypeFromString("Not a resource");
    }

    @Test (timeout = TIMEOUT)
    public void testNormalString() {
        ResourceType resourceType = ResourceType.CRYSTITE;
        ResourceType stringResourceType
                = storeController.getResourceTypeFromString("CRYSTITE");
        assertEquals(resourceType, stringResourceType);
    }
}