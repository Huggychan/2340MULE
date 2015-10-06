package edu.gatech.cs2340.GameObject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum ResourceType {

    FOOD("/resources/MULE_Food.png"),
    ENERGY("/resources/MULE_Energy.png"),
    ORE("/resources/MULE_Ore.png"),
    CRYSTITE("/resources/MULE_Crystite.png");

    private Image image;
    private ImageView imageView;

    private ResourceType(String string) {
        this.image = new Image(string);
        this.imageView = new ImageView(image);
    }

    public ImageView getImageView() {
        return this.imageView;
    }
};