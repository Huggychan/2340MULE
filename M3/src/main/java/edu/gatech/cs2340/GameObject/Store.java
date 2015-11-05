package edu.gatech.cs2340.GameObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Team Industrial Grade Salt
 */
public class Store implements Serializable {

    private static final int INIT_FOOD = 16;
    private static final int INIT_ENERGY = 16;
    private static final int INIT_MULE = 16;


    private Map<ResourceType, Integer> inventoryStock;
    private Map<ResourceType, Integer> inventoryPrice;
    private static final int FOOD_PRICE = 30;
    private static final int ENERGY_PRICE = 25;
    private static final int SMITHORE_PRICE = 50;
    private static final int CRYSTITE_PRICE = 100;
    private static final int BASE_MULE_PRICE = 100;

    /**
     * Constructor for Store.
     */
    public Store() {
        inventoryStock = new HashMap<>();
        inventoryStock.put(ResourceType.FOOD, INIT_FOOD);
        inventoryStock.put(ResourceType.ENERGY, INIT_FOOD);
        inventoryStock.put(ResourceType.ORE, 0);
        inventoryStock.put(ResourceType.CRYSTITE, 0);
        inventoryStock.put(ResourceType.MULE, INIT_MULE);

        inventoryPrice = new HashMap<>();
        inventoryPrice.put(ResourceType.FOOD, FOOD_PRICE);
        inventoryPrice.put(ResourceType.ENERGY, ENERGY_PRICE);
        inventoryPrice.put(ResourceType.ORE, SMITHORE_PRICE);
        inventoryPrice.put(ResourceType.CRYSTITE, CRYSTITE_PRICE);
        inventoryPrice.put(ResourceType.MULE, BASE_MULE_PRICE);
    }

    /**
     * @return Gets how many mules are in inventory.
     */
    public int getMuleCount() {
        return inventoryStock.get(ResourceType.MULE);
    }

    /**
     * @return Gets how much ore in inventory
     */
    public int getOreCount() {
        return inventoryStock.get(ResourceType.ORE);
    }

    /**
     * @return Gets how much food in inventory
     */
    public int getFoodCount() {
        return inventoryStock.get(ResourceType.FOOD);
    }

    /**
     * @return Gets how much energy in inventory
     */
    public int getEnergyCount() {
        return inventoryStock.get(ResourceType.ENERGY);
    }

    /**
     * @return Gets how much crystite in inventory
     */
    public int getCrystiteCount() {
        return inventoryStock.get(ResourceType.CRYSTITE);
    }

    /**
     * @return Price of energy
     */
    public int getEnergyPrice() {
        return ENERGY_PRICE;
    }

    /**
     * @return Price of smith ore
     */
    public int getSmithorePrice() {
        return SMITHORE_PRICE;
    }

    /**
     * @return Price of base mule
     */
    public int getBaseMulePrice() {
        return BASE_MULE_PRICE;
    }

    /**
     * @return Price of food
     */
    public int getFoodPrice() {
        return FOOD_PRICE;
    }

    /**
     * @return Price of crystite
     */
    public int getCrystitePrice() {
        return CRYSTITE_PRICE;
    }

    /**
     * Buys the resource type and gives it to Player.
     * @param rt ResourceType to buy
     * @param player Player buying items
     * @return True if successful
     */
    public boolean buy(ResourceType rt, Player player) {
        System.out.println("Player's inventory before: "
                + player.getInventory());
        int price = this.inventoryPrice.get(rt);
        int stock = this.inventoryStock.get(rt);
        if (player.getMoney() >= price && stock > 0) {
            if (rt != ResourceType.MULE) {
                this.decrementKeyInMap(rt, this.inventoryStock);
                this.incrementKeyInMap(rt, player.getInventory());
                player.decrementMoney(price);
                System.out.println("Player's inventory after: "
                        + player.getInventory());
                return true;
            } else {
                if (player.getMule() == null
                        && !player.getMuleBoughtThisTurn()) {
                    player.setMuleBoughtThisTurn(true);
                    this.decrementKeyInMap(rt, this.inventoryStock);
                    player.giveMule();
                    player.decrementMoney(price);
                    return true;
                }
            }
        }

        System.out.println("Player's inventory after: "
                + player.getInventory());
        return false;
    }

    /**
     * Sells the resource type and gives it to Player.
     * @param rt ResourceType to buy
     * @param player Player buying items
     * @return True if successful
     */
    public boolean sell(ResourceType rt, Player player) {

        if (player == null || rt == null) {
            throw new IllegalArgumentException(
                    "Either player or resource type is null");
        }

        int value = this.inventoryPrice.get(rt);

        if (rt != ResourceType.MULE) {
            if (player.getInventory().get(rt) > 0) {
                this.decrementKeyInMap(rt, player.getInventory());
                this.incrementKeyInMap(rt, this.inventoryStock);
                player.incrementMoney(value);

                return true;
            }
        } else {
            if (player.getMule() != null) {
                player.takeMule();
                this.incrementKeyInMap(rt, this.inventoryStock);

                player.incrementMoney(value);
                return true;
            }
        }

        return false;
    }
    //TODO add logging to buying/selling

    /**
     * Increments resourceType in the map.
     * @param key resourceType to be incremented
     * @param map HashMap map of resourceType
     */
    private void incrementKeyInMap(ResourceType key, Map<ResourceType,
            Integer> map) {
        int val = map.get(key);
        map.put(key, val + 1);
    }

    /**
     * Decrements resourceType in the map.
     * @param key resourceType to be incremented
     * @param map HashMap map of resourceType
     */
    private void decrementKeyInMap(ResourceType key, Map<ResourceType,
            Integer> map) {
        int val = map.get(key);
        if (val > 0) {
            map.put(key, val - 1);
        }
    }
}
