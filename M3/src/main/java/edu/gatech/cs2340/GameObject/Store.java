package edu.gatech.cs2340.GameObject;

import java.util.ArrayList;

/**
 * Created by lacay_000 on 9/29/2015.
 */
public class Store {

    private ArrayList<Mule> mules;
    private int oreCount;
    private int foodCount;
    private int energyCount;
    private int crystiteCount;

    private final int FOOD_PRICE = 30;
    private final int ENERGY_PRICE = 25;
    private final int SMITHORE_PRICE = 50;
    private final int CRYSTITE_PRICE = 100;
    private final int BASE_MULE_PRICE = 100;

    public Store() {
        this.foodCount = 16;
        this.energyCount = 16;
        this.oreCount = 0;
        this.crystiteCount = 0;
        this.mules = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            Mule m = new Mule();
            this.mules.add(m);
        }
    }
}
