package edu.gatech.cs2340;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class MuleJUnit {

    private String string;
    private String string2;
    private String temp;
    private String nullString;

    @Before
    public void setup() {
        string = "This was a setup! It's a trap!";
        string2 = "Is JUnit working?";
        temp = "temp";
    }

    @Test
    public void testBasic() {
        assertNull(nullString);
        assertEquals("Is JUnit working?", string2);
        assertNotEquals(string, string2);
        assertSame("temp", temp);
    }


}