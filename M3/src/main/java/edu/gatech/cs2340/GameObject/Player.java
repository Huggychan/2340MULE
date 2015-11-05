package edu.gatech.cs2340.GameObject;

import edu.gatech.cs2340.Maps.Tile;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Makes a Player with different elements.
 * @author Bilal, Marc, Shyam
 * @version 1.1
 */
public class Player implements Comparable<Player>, Serializable {

    private String name;
    private Race race;
    private transient Color color;
    private String colorString;
    private List<Tile> tiles;
    private Map<ResourceType, Integer> inventory;
    private int money;
    private Mule mule;
    private boolean muleBoughtThisTurn;
    private static final long serialVersionUID = 2L;

    /**
     * @return Money of player
     */
    public int getMoney() {
        return money;
    }

    /**
     * Sets money of person.
     * @param amount Money to set for the person
     */
    public void setMoney(int amount) {
        this.money = amount;
        if (amount < 0) {
            this.money = 0;
        }
    }

    /**
     * @return Food of player
     */
    public int getFood() {
        return inventory.get(ResourceType.FOOD);
    }

    /**
     * @return Ore of player
     */
    public int getOre() {
        return inventory.get(ResourceType.ORE);
    }

    /**
     * @return Energy of player
     */
    public int getEnergy() {
        return inventory.get(ResourceType.ENERGY);
    }

    /**
     * @return Crystite of player
     */
    public int getCrystite() {
        return inventory.get(ResourceType.CRYSTITE);
    }

    /**
     * @return Number of Land tiles player owns
     */
    public int getLand() {
        return tiles.size();
    }

    /**
     * @return Sum of food, energy, crystite, and ore
     */
    public int getGoods() {
        return getFood() + getEnergy() + getCrystite() + getOre();
    }

    /**
     * @return True if Mule was bought during the turn
     */
    public boolean getMuleBoughtThisTurn() {
        return this.muleBoughtThisTurn;
    }

    /**
     * Sets Mule Bought This Turn.
     * @param bool Boolean to set muleBoughtThisTurn
     */
    public void setMuleBoughtThisTurn(boolean bool) {
        this.muleBoughtThisTurn = bool;
    }

    /**
     * @return Gets the image of the player
     */
    public Image getPlayerImage() {
        String imageString = "/resources/" + this.getRace() + ".png";
        return new Image(imageString);
    }

    /**
     * Player constructor.
     * @param playerName Name of player
     * @param playerRace Race of player
     * @param playerColorString Color of player in string format
     */
    public Player(String playerName, Race playerRace,
                  String playerColorString) {
        this.inventory = new HashMap<>();
        inventory.put(ResourceType.CRYSTITE, 0);
        inventory.put(ResourceType.FOOD, 0);
        inventory.put(ResourceType.ORE, 0);
        inventory.put(ResourceType.ENERGY, 0);
        this.tiles = new ArrayList<>();
        this.muleBoughtThisTurn = false;

        this.name = playerName;
        this.race = playerRace;
        this.colorString = playerColorString;
        this.setColor(this.colorString);
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
     * Adds the tile to the player's collection of tiles.
     * @param t tile to add to player's collection
     */
    public void addTile(Tile t) {
        tiles.add(t);
    }

    /**
     * Gets the name.
     * @return Name of Player
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the race.
     * @return Race of Player
     */
    public Race getRace() {
        return race;
    }

    /**
     * Gets String form of Race.
     * @return String form of Race
     */
    public String getRaceString() {
        return race.toString();
    }

    /**
     * Gets the color.
     * @return Color of Player
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets color in string format.
     * @return Color in string format
     */
    public String getColorString() {
        return colorString;
    }

    /**
     * Sets the Name of Player.
     * @param newName Name to be set for Player
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Sets the Race of Player.
     * @param newRace Race to be set for Player
     */
    public void setRace(Race newRace) {
        this.race = newRace;
    }

    /**
     * Sets the Color of Player.
     * @param newColorString Color to be set for Player
     */
    public void setColor(String newColorString) {
        switch (newColorString) {
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
        default:
            break;
        }
    }

    /**
     * Sets food of person.
     * @param food Food to set for the person
     */
    public void setFood(int food) {
        inventory.put(ResourceType.FOOD, food);
        if (food < 0) {
            inventory.put(ResourceType.FOOD, food);
        }
    }

    /**
     * Sets energy of person.
     * @param energy Energy to set for the person
     */
    public void setEnergy(int energy) {
        inventory.put(ResourceType.ENERGY, energy);
        if (energy < 0) {
            inventory.put(ResourceType.ENERGY, energy);
        }
    }

    /**
     * Sets ore of person.
     * @param ore Ore to set for the person
     */
    public void setOre(int ore) {
        inventory.put(ResourceType.ORE, ore);
        if (ore < 0) {
            inventory.put(ResourceType.ORE, ore);
        }
    }

    /**
     * Sets crystite of person.
     * @param crystite Crystite to set for the person
     */
    public void setCrystite(int crystite) {
        inventory.put(ResourceType.CRYSTITE, crystite);
        if (crystite < 0) {
            inventory.put(ResourceType.CRYSTITE, crystite);
        }
    }


    @Override
    public String toString() {
        return this.name + " " + this.color + " " + this.race;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Player)) {
            return false;
        }
        Player that = (Player) other;
        return this.name.equals(that.name) || this.color.equals(that.color);
    }

    @Override
    public int hashCode() {
        int result = 17;
        return 31 * result + name.hashCode() + color.hashCode();
    }

    /**
     * @return Score of player
     */
    public int getScore() {
        int returnValue = this.getMoney() + this.tiles.size();
        for (Integer i : inventory.values()) {
            returnValue += i;
        }
        return returnValue;
    }

    @Override
    public int compareTo(Player other) {
        return other.getScore() - this.getScore();
    }

    /**
     * @return Inventory of player
     */
    public Map<ResourceType, Integer> getInventory() {
        return this.inventory;
    }

    /**
     * Decrements money of player.
     * @param amount amount to decrement by
     */
    public void decrementMoney(int amount) {
        if (this.money >= amount) {
            this.money = this.money - amount;
        }
    }

    /**
     * Gives mule to player.
     */
    public void giveMule() {
        if (this.mule == null) {
            this.mule = new Mule(this);
        }
    }

    /**
     * Calculates production for the player.
     */
    public void calcProduction() {
        System.out.println(this.getName() + " " + inventory);
        for (Tile t : tiles) {
            ResourceType resource = t.getMuleResource();
            if (resource != null) {
                System.out.println(resource);
                Integer currentValue = inventory.get(resource);
                inventory.put(resource, currentValue + t.calculateProduction());
            }
        }
        System.out.println(this.getName() + " " + inventory);
    }

    /**
     * @return Mule belonging to player.
     */
    public Mule getMule() {
        return this.mule;
    }

    /**
     * Sets mule for player.
     * @param newMule Mule to be set to player
     */
    public void setMule(Mule newMule) {
        this.mule = newMule;
    }

    /**
     * @return True if player has a mule
     */
    public boolean hasMule() {
        return this.mule != null;
    }

    /**
     * Takes mule from player.
     */
    public void takeMule() {
        this.mule = null;
    }

    /**
     * Increments money of player.
     * @param amount amount to increment by
     */
    public void incrementMoney(int amount) {
        this.money = this.money + amount;
    }
}
