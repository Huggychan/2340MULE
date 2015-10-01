package edu.gatech.cs2340.GameObject;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by lacay_000 on 9/29/2015.
 */
public class Store {

    private HashMap<ProductType, Integer> inventoryStock;
    private HashMap<ProductType, Integer> inventoryPrice;
    private final int FOOD_PRICE = 30;
    private final int ENERGY_PRICE = 25;
    private final int SMITHORE_PRICE = 50;
    private final int CRYSTITE_PRICE = 100;
    private final int BASE_MULE_PRICE = 100;

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
        return inventoryStock.get("MULE");
    }

    public int getOreCount() {
        return inventoryStock.get("Ore");
    }

    public int getFoodCount() {
        return inventoryStock.get("Food");
    }

    public int getEnergyCount() {
        return inventoryStock.get("Energy");
    }

    public int getCrystiteCount() {
        return inventoryStock.get("Crystite");
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
        if (player.getMoney() > inventoryPrice.get(pt) && inventoryStock
                .get(pt) > 0) {
        }
        return false;
    }

    public boolean sell(ProductType pt, Player player) {
        return false;
    }

}


