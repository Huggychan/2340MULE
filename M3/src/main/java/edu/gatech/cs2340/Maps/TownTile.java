package edu.gatech.cs2340.Maps;

import javafx.scene.image.Image;

/**
 * Created by marc on 10/5/15.
 */
public class TownTile extends Tile {

    public TownTile() {
        this.getImageView().setImage(new Image("/resources/Town.jpg"));
        this.getChildren().add(this.getImageView());
    }
}
