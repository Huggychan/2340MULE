package edu.gatech.cs2340.GameObject;

import java.util.HashMap;

public class Store {

    private HashMap<ProductType, Integer> inventoryStock;
    private HashMap<ProductType, Integer> inventoryPrice;
    private final int FOOD_PRICE = 30;
    private final int ENERGY_PRICE = 25;
    private final int SMITHORE_PRICE = 50;
    private final int CRYSTITE_PRICE = 100;
    private final int BASE_MULE_PRICE = 100;

    public boolean muleBoughtThisTurn = false;

    public Store() {
        inventoryStock = new HashMap<>();
        inventoryStock.put(ProductType.FOOD, 16);
        inventoryStock.put(ProductType.ENERGY, 16);
        inventoryStock.put(ProductType.ORE, 0);
        inventoryStock.put(ProductType.CRYSTITE, 0);
        inventoryStock.put(ProductType.MULE, 25);

        inventoryPrice = new HashMap<>();
        inventoryPrice.put(ProductType.FOOD, FOOD_PRICE);
        inventoryPrice.put(ProductType.ENERGY, ENERGY_PRICE);
        inventoryPrice.put(ProductType.ORE, SMITHORE_PRICE);
        inventoryPrice.put(ProductType.CRYSTITE, CRYSTITE_PRICE);
        inventoryPrice.put(ProductType.MULE, BASE_MULE_PRICE);
    }

    public int getMuleCount() {
        return inventoryStock.get(ProductType.MULE);
    }

    public int getOreCount() {
        return inventoryStock.get(ProductType.ORE);
    }

    public int getFoodCount() {
        return inventoryStock.get(ProductType.FOOD);
    }

    public int getEnergyCount() {
        return inventoryStock.get(ProductType.ENERGY);
    }

    public int getCrystiteCount() {
        return inventoryStock.get(ProductType.CRYSTITE);
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

    public boolean buy(ProductType pt, Player player) {
        System.out.println("Player's inventory before: " + player.getInventory());
        int price = this.inventoryPrice.get(pt);
        int stock = this.inventoryStock.get(pt);
        if (player.getMoney() > price && stock > 0) {
            if (pt != ProductType.MULE) {
                this.decrementKeyInMap(pt, this.inventoryStock);
                this.incrementKeyInMap(pt, player.getInventory());
                player.decrementMoney(price);
                System.out.println("Player's inventory after: " + player.getInventory());
                return true;
            } else {
                if (player.getMule() == null && !muleBoughtThisTurn) {
                    muleBoughtThisTurn = true;
                    this.decrementKeyInMap(pt, this.inventoryStock);
                    player.giveMule();
                    player.decrementMoney(price);
                    return true;
                }
            }
        }

        System.out.println("Player's inventory after: " + player.getInventory());
        return false;
    }

    public boolean sell(ProductType pt, Player player) {
        System.out.println(player.getInventory());
        System.out.println(player.getMoney());
        int value = this.inventoryPrice.get(pt);
        int stock = this.inventoryStock.get(pt);
        this.incrementKeyInMap(pt, this.inventoryStock);
        player.incrementMoney(value);


        if (pt != ProductType.MULE) {
            if (player.getInventory().get(pt) > 0) {
                this.decrementKeyInMap(pt, player.getInventory());

                System.out.println(player.getInventory());
                System.out.println(player.getMoney());

                return true;
            }
        } else {
            if (player.getMule() != null) {
                player.takeMule();
                return true;
            }
        }

        return false;
    }
    //TODO add logging to buying/selling

    private void incrementKeyInMap(ProductType key, HashMap<ProductType,
            Integer> map) {
        int val = map.get(key);
        map.put(key, val + 1);
    }

    private void decrementKeyInMap(ProductType key, HashMap<ProductType,
            Integer> map) {
        int val = map.get(key);
        if (val > 0) {
            map.put(key, val - 1);
        }
    }
}


