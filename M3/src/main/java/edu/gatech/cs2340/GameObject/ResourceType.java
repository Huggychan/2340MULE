package edu.gatech.cs2340.GameObject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum ResourceType {

    MULE(),
    FOOD("/resources/MULE_Food.png"),
    ENERGY("/resources/MULE_Energy.png"),
    ORE("/resources/MULE_Ore.png"),
    CRYSTITE("/resources/MULE_Crystite.png");

    private String imageString;

    private ResourceType() {}

    private ResourceType(String string) {
        this.imageString = string;
    }

    public String getImageString() {
        return this.imageString;
    }
};