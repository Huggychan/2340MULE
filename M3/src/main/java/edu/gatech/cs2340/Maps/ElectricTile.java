package edu.gatech.cs2340.Maps;

import edu.gatech.cs2340.GameObject.ResourceType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Bilal
 * @version 1.0
 */
public class ElectricTile extends Tile {

    /**
     * Constructor for ElectricTile.
     */
    public ElectricTile() {
        this.setImage(new Image("/resources/electricTile.jpg"));
        this.getImageView().setImage(getImage());
        this.getChildren().add(this.getImageView());
        this.getResourceTypeMap().put(ResourceType.FOOD, 1);
        this.getResourceTypeMap().put(ResourceType.ENERGY, 4);
        this.getResourceTypeMap().put(ResourceType.ORE, 4);
        this.getResourceTypeMap().put(ResourceType.CRYSTITE, 2);
        this.getResourceTypeMap().put(ResourceType.ELECTRICITY, 5);
        this.getResourceTypeMap().put(ResourceType.WATER, 0);
        this.getResourceTypeMap().put(ResourceType.DIAMOND, 2);
    }

    @Override
    public void loadImageView() {
        this.setImageView(new ImageView(
                new Image("/resources/electricTile.jpg")));
    }

}
