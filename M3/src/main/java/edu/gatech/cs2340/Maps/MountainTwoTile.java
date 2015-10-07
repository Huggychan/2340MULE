package edu.gatech.cs2340.Maps;

import edu.gatech.cs2340.GameObject.ResourceType;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * Created by marc on 10/5/15.
 */
public class MountainTwoTile extends Tile {

    public MountainTwoTile() {
        this.getImageView().setImage(new Image("/resources/Mountain2.jpg"));
        this.getChildren().add(this.getImageView());
        Random r = new Random();
        int randomNum = r.nextInt(4);
        this.getResourceTypeMap().put(ResourceType.FOOD, 1);
        this.getResourceTypeMap().put(ResourceType.ENERGY, 1);
        this.getResourceTypeMap().put(ResourceType.ORE, 3);
        this.getResourceTypeMap().put(ResourceType.CRYSTITE, randomNum);
    }

    public int calculateProduction() {
        return 0;
    }
}
