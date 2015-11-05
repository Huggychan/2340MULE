package edu.gatech.cs2340.Maps;

import edu.gatech.cs2340.GameObject.ResourceType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by marc on 10/5/15.
 */
public class RiverTile extends Tile {

    /**
     * Constructor for RiverTile.
     */
    public RiverTile() {
        this.setImage(new Image("/resources/River.png"));
        this.getImageView().setImage(this.getImage());
        this.getChildren().add(this.getImageView());
        this.getResourceTypeMap().put(ResourceType.FOOD, 4);
        this.getResourceTypeMap().put(ResourceType.ENERGY, 2);
        this.getResourceTypeMap().put(ResourceType.ORE, 0);
        this.getResourceTypeMap().put(ResourceType.CRYSTITE, 0);
    }

    @Override
    public void loadImageView() {
        this.setImageView(new ImageView(new Image("/resources/River.png")));
    }

}
