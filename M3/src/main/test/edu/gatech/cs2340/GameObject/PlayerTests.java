package edu.gatech.cs2340.GameObject;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Bilal
 * @version 1.0
 */
public class PlayerTests {

    private Player player;

    @Before
    public void setup() {
        player = new Player("Player", Race.FLAPPER, "Red");
    }

    @Test
    public void testSetMoney() {
        player.setMoney(2000);
        assertEquals(2000, player.getMoney());
        player.setMoney(-2000);
        assertEquals(0, player.getMoney());
        player.setMoney(0);
        assertEquals(0, player.getMoney());
    }

    @Test
    public void testSetColor() {
        player.setColor("Orange");
        assertEquals(Color.ORANGE, player.getColor());
        player.setColor("RED");
        assertNotEquals(Color.RED, player.getColor());
        player.setColor("Yellow");
        assertEquals(Color.YELLOW, player.getColor());
        player.setColor("Green");
        assertEquals(Color.GREEN, player.getColor());
        player.setColor("Blue");
        assertEquals(Color.BLUE, player.getColor());
        player.setColor("Purple");
        assertEquals(Color.PURPLE, player.getColor());
        player.setColor("Red");
        assertEquals(Color.RED, player.getColor());
        player.setColor("ORANGE");
        assertNotEquals(Color.ORANGE, player.getColor());
        player.setColor("YELLOW");
        assertNotEquals(Color.YELLOW, player.getColor());
        player.setColor("GREEN");
        assertNotEquals(Color.GREEN, player.getColor());
        player.setColor("BLUE");
        assertNotEquals(Color.BLUE, player.getColor());
        player.setColor("PURPLE");
        assertNotEquals(Color.PURPLE, player.getColor());
    }

    @Test
    public void testSetFood() {
        player.setFood(2000);
        assertEquals(2000, player.getFood());
        player.setFood(-2000);
        assertEquals(0, player.getFood());
        player.setFood(0);
        assertEquals(0, player.getFood());
    }

    @Test
    public void testSetEnergy() {
        player.setEnergy(2000);
        assertEquals(2000, player.getEnergy());
        player.setEnergy(-2000);
        assertEquals(0, player.getEnergy());
        player.setEnergy(0);
        assertEquals(0, player.getEnergy());
    }

    @Test
    public void testSetOre() {
        player.setOre(2000);
        assertEquals(2000, player.getOre());
        player.setOre(-2000);
        assertEquals(0, player.getOre());
        player.setOre(0);
        assertEquals(0, player.getOre());
    }

    @Test
    public void testSetCrystite() {
        player.setCrystite(2000);
        assertEquals(2000, player.getCrystite());
        player.setCrystite(-2000);
        assertEquals(0, player.getCrystite());
        player.setCrystite(0);
        assertEquals(0, player.getCrystite());
    }

    @Test
    public void testSetElectricity() {
        player.setElectricity(2000);
        assertEquals(2000, player.getElectricity());
        player.setElectricity(-2000);
        assertEquals(0, player.getElectricity());
        player.setElectricity(0);
        assertEquals(0, player.getElectricity());
    }

    @Test
    public void testSetWater() {
        player.setWater(2000);
        assertEquals(2000, player.getWater());
        player.setWater(-2000);
        assertEquals(0, player.getWater());
        player.setWater(0);
        assertEquals(0, player.getWater());
    }

    @Test
    public void testSetDiamond() {
        player.setDiamond(2000);
        assertEquals(2000, player.getDiamond());
        player.setDiamond(-2000);
        assertEquals(0, player.getDiamond());
        player.setDiamond(0);
        assertEquals(0, player.getDiamond());
    }

}
