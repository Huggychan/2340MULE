package edu.gatech.cs2340.Maps;

import edu.gatech.cs2340.GameObject.ResourceType;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * Created by marc on 10/5/15.
 */
public class RiverTile extends Tile {

    public RiverTile() {
        this.getImageView().setImage(new Image("/resources/River.png"));
        this.getChildren().add(this.getImageView());
        Random r = new Random();
        int randomNum = r.nextInt(4);
        this.getChildren().add(this.getImageView());
        this.getResourceTypeMap().put(ResourceType.FOOD, 4);
        this.getResourceTypeMap().put(ResourceType.ENERGY, 2);
    }

    public int calculateProduction() {
        return 0;
    }
}
