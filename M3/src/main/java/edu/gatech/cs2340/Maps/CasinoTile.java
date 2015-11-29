package edu.gatech.cs2340.Maps;

import edu.gatech.cs2340.GameObject.ResourceType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * @author Bilal
 * @version 1.0
 */
public class CasinoTile extends Tile {
    /**
     * Constructor for CasinoTile.
     */
    public CasinoTile() {
        this.setImage(new Image("/resources/casinoTile.jpg"));
        this.getImageView().setImage(getImage());
        this.getChildren().add(this.getImageView());
        Random r = new Random();
        int randomNum = r.nextInt(4);
        this.getResourceTypeMap().put(ResourceType.FOOD, randomNum);
        this.getResourceTypeMap().put(ResourceType.ENERGY, randomNum);
        this.getResourceTypeMap().put(ResourceType.ORE, randomNum);
        this.getResourceTypeMap().put(ResourceType.CRYSTITE, randomNum);
        this.getResourceTypeMap().put(ResourceType.ELECTRICITY, randomNum);
        this.getResourceTypeMap().put(ResourceType.WATER, randomNum);
        this.getResourceTypeMap().put(ResourceType.DIAMOND, randomNum);
    }

    @Override
    public void loadImageView() {
        this.setImageView(new ImageView(
                new Image("/resources/casinoTile.jpg")));
    }
}
