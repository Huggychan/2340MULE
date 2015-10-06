package edu.gatech.cs2340.Maps;

import javafx.scene.image.Image;

/**
 * Created by marc on 10/5/15.
 */
public class RiverTile extends Tile {

    public RiverTile() {
        this.getImageView().setImage(new Image("/resources/River.png"));
        this.getChildren().add(this.getImageView());
    }

    public int calculateProduction() {
        return 0;
    }
}
