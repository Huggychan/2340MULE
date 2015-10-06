package edu.gatech.cs2340.Maps;

import javafx.scene.image.Image;

/**
 * Created by marc on 10/5/15.
 */
public class MountainTwoTile extends Tile {

    public MountainTwoTile() {
        this.getImageView().setImage(new Image("/resources/Mountain2.jpg"));
        this.getChildren().add(this.getImageView());
    }

    public int calculateProduction() {
        return 0;
    }
}
