package edu.gatech.cs2340.Maps;

import javafx.scene.image.Image;

/**
 * Created by marc on 10/5/15.
 */
public class PlainsTile extends Tile {

    public PlainsTile() {
        this.getImageView().setImage(new Image("/resources/Plain.png"));
        this.getChildren().add(this.getImageView());
    }

    public int calculateProduction() {
        return 0;
    }
}
