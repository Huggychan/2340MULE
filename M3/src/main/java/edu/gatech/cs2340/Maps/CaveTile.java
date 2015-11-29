package edu.gatech.cs2340.Maps;

import edu.gatech.cs2340.GameObject.ResourceType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Bilal
 * @version 1.0
 */
public class CaveTile extends Tile {

    /**
     * Constructor for ElectricTile.
     */
    public CaveTile() {
        this.setImage(new Image("/resources/caveTile.jpg"));
        this.getImageView().setImage(getImage());
        this.getChildren().add(this.getImageView());
        this.getResourceTypeMap().put(ResourceType.FOOD, 0);
        this.getResourceTypeMap().put(ResourceType.ENERGY, 0);
        this.getResourceTypeMap().put(ResourceType.ORE, 5);
        this.getResourceTypeMap().put(ResourceType.CRYSTITE, 5);
        this.getResourceTypeMap().put(ResourceType.ELECTRICITY, 1);
        this.getResourceTypeMap().put(ResourceType.WATER, 1);
        this.getResourceTypeMap().put(ResourceType.DIAMOND, 5);
    }

    @Override
    public void loadImageView() {
        this.setImageView(new ImageView(
                new Image("/resources/caveTile.jpg")));
    }
}
