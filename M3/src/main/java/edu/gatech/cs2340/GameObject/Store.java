package edu.gatech.cs2340.GameObject;

import java.io.Serializable;
import java.util.HashMap;

public class Store implements Serializable {

    private HashMap<ResourceType, Integer> inventoryStock;
    private HashMap<ResourceType, Integer> inventoryPrice;
    private final int FOOD_PRICE = 30;
    private final int ENERGY_PRICE = 25;
    private final int SMITHORE_PRICE = 50;
    private final int CRYSTITE_PRICE = 100;
    private final int BASE_MULE_PRICE = 100;

    public Store() {
        inventoryStock = new HashMap<>();
        inventoryStock.put(ResourceType.FOOD, 16);
        inventoryStock.put(ResourceType.ENERGY, 16);
        inventoryStock.put(ResourceType.ORE, 0);
        inventoryStock.put(ResourceType.CRYSTITE, 0);
        inventoryStock.put(ResourceType.MULE, 25);

        inventoryPrice = new HashMap<>();
        inventoryPrice.put(ResourceType.FOOD, FOOD_PRICE);
        inventoryPrice.put(ResourceType.ENERGY, ENERGY_PRICE);
        inventoryPrice.put(ResourceType.ORE, SMITHORE_PRICE);
        inventoryPrice.put(ResourceType.CRYSTITE, CRYSTITE_PRICE);
        inventoryPrice.put(ResourceType.MULE, BASE_MULE_PRICE);
    }

    public int getMuleCount() {
        return inventoryStock.get(ResourceType.MULE);
    }

    public int getOreCount() {
        return inventoryStock.get(ResourceType.ORE);
    }

    public int getFoodCount() {
        return inventoryStock.get(ResourceType.FOOD);
    }

    public int getEnergyCount() {
        return inventoryStock.get(ResourceType.ENERGY);
    }

    public int getCrystiteCount() {
        return inventoryStock.get(ResourceType.CRYSTITE);
    }

    public int getENERGY_PRICE() {
        return ENERGY_PRICE;
    }

    public int getSMITHORE_PRICE() {
        return SMITHORE_PRICE;
    }

    public int getBASE_MULE_PRICE() {
        return BASE_MULE_PRICE;
    }

    public int getFOOD_PRICE() {
        return FOOD_PRICE;
    }

    public int getCRYSTITE_PRICE() {
        return CRYSTITE_PRICE;
    }

    public boolean buy(ResourceType rt, Player player) {
        System.out.println("Player's inventory before: " + player.getInventory());
        int price = this.inventoryPrice.get(rt);
        int stock = this.inventoryStock.get(rt);
        if (player.getMoney() >= price && stock > 0) {
            if (rt != ResourceType.MULE) {
                this.decrementKeyInMap(rt, this.inventoryStock);
                this.incrementKeyInMap(rt, player.getInventory());
                player.decrementMoney(price);
                System.out.println("Player's inventory after: " + player.getInventory());
                return true;
            } else {
                if (player.getMule() == null && !player.getMuleBoughtThisTurn()) {
                    player.setMuleBoughtThisTurn(true);
                    this.decrementKeyInMap(rt, this.inventoryStock);
                    player.giveMule();
                    player.decrementMoney(price);
                    return true;
                }
            }
        }

        System.out.println("Player's inventory after: " + player.getInventory());
        return false;
    }

    public boolean sell(ResourceType rt, Player player) {

        if (player == null || rt == null) {
            throw new IllegalArgumentException("Either player or resource type is null");
        }

        int value = this.inventoryPrice.get(rt);
        int stock = this.inventoryStock.get(rt);

        if (rt != ResourceType.MULE) {
            if (player.getInventory().get(rt) > 0) {
                this.decrementKeyInMap(rt, player.getInventory());
                this.incrementKeyInMap(rt, this.inventoryStock);

                return true;
            }
        } else {
            if (player.getMule() != null) {
                player.takeMule();
                this.incrementKeyInMap(rt, this.inventoryStock);
                return true;
            }
        }

        return false;
    }
    //TODO add logging to buying/selling

    private void incrementKeyInMap(ResourceType key, HashMap<ResourceType,
            Integer> map) {
        int val = map.get(key);
        map.put(key, val + 1);
    }

    private void decrementKeyInMap(ResourceType key, HashMap<ResourceType,
            Integer> map) {
        int val = map.get(key);
        if (val > 0) {
            map.put(key, val - 1);
        }
    }
}


