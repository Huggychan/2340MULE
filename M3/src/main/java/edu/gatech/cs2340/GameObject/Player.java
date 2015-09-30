package edu.gatech.cs2340.GameObject;

import edu.gatech.cs2340.Maps.Tile;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Makes a Player with different elements
 * @author Bilal, Marc, Shyam
 * @version 1.1
 */
public class Player implements Comparable<Player> {

    private String name;
    private Race race;
    private Color color;
    private String colorString;
    private ArrayList<Tile> tiles;
    private int ore;
    private int food;
    private int energy;
    private int crystite;
    private Mule mule;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    private int money;

    public Player(String name, Race race, String colorString) {
        money = 300;
        this.name = name;
        this.race = race;
        this.colorString = colorString;
        switch (colorString) {
            case "Red":
                color = Color.RED;
                break;
            case "Orange":
                color = Color.ORANGE;
                break;
            case "Yellow":
                color = Color.YELLOW;
                break;
            case "Green":
                color = Color.GREEN;
                break;
            case "Blue":
                color = Color.BLUE;
                break;
            case "Purple":
                color = Color.PURPLE;
                break;
        }
        if (color == Color.ORANGE || color == Color.BLUE) {
            money = 3000;
        }
    }


    /**
     * Gets the name
     * @return Name of Player
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the race
     * @return Race of Player
     */
    public Race getRace() {
        return race;
    }

    /**
     * Gets String form of Race
     * @return String form of Race
     */
    public String getRaceString() {
        return race.toString();
    }

    /**
     * Gets the color
     * @return Color of Player
     */
    public Color getColor() {
        return color;
    }

    public String getColorString() {
        return colorString;
    }
    /**
     * Sets the Name of Player
     * @param name Name to be set for Player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the Race of Player
     * @param race RaCe to be set for Player
     */
    public void setRace(Race race) {
        this.race = race;
    }

    /**
     * Sets the Color of Player
     * @param color Color to be set for Player
     */
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.name + " " + this.color + " " + this.race;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Player)) return false;
        Player that = (Player)other;
        return this.name.equals(that.name) && this.color.equals(that.color);
    }

    @Override
    public int hashCode() {
        int result = 17;
        return 31 * result + name.hashCode() + color.hashCode();
    }

    public int getScore() {
        return this.getMoney() + this.tiles.size() * 500 + this.ore + this
                .energy + this.crystite + this.food;
    }

    public int compareTo(Player other) {
        return other.getScore() - this.getScore();
    }
}