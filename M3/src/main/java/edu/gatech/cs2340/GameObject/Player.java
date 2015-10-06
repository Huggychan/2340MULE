package edu.gatech.cs2340.GameObject;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.Maps.Tile;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

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
    private HashMap<ProductType, Integer> inventory;
    private int ore;
    private int food;
    private int energy;
    private int crystite;
    private int money;
    private Mule mule;
    private Game game;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getFood() {
        return food;
    }

    public Player(String name, Race race, String colorString, Game game) {
        this.inventory = new HashMap<>();
        inventory.put(ProductType.CRYSTITE, 0);
        inventory.put(ProductType.FOOD, 0);
        inventory.put(ProductType.ORE, 0);
        inventory.put(ProductType.ENERGY, 0);
        this.game = game;
        this.tiles = new ArrayList<>();

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
        switch (race) {
            case HUMAN:
                this.money = 600;
                break;
            case FLAPPER:
                this.money = 1600;
                break;
            default:
                this.money = 1000;
                break;
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

    public void setFood(int food) {
        inventory.put(ProductType.FOOD, food);
    }

    public void setEnergy(int energy) {
        inventory.put(ProductType.ENERGY, energy);
    }

    public void setOre(int ore) {
        inventory.put(ProductType.ORE, ore);
    }

    public void setCrystite(int crystite) {
        inventory.put(ProductType.CRYSTITE, crystite);
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

    public HashMap<ProductType, Integer> getInventory() {
        return this.inventory;
    }

    public void decrementMoney(int amount) {
        if (this.money > amount) {
            this.money = this.money - amount;
        }
    }

    public void giveMule() {
        if (this.mule == null) {
            this.mule = new Mule(this);
        }
    }

    public Mule getMule() {
        return this.mule;
    }

    public boolean hasMule() {
        return this.mule != null;
    }

    public void takeMule() {
        this.mule = null;
    }

    public void incrementMoney(int amount) {
        this.money = this.money + amount;
    }
}