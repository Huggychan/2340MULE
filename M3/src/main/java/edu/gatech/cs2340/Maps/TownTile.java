package edu.gatech.cs2340.Maps;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by marc on 10/5/15.
 */
public class TownTile extends Tile {

    public TownTile() {
        this.getImageView().setImage(new Image("/resources/Town.png"));
        this.getChildren().add(this.getImageView());
    }

    @Override
    public void loadImageView() {
        this.setImageView(new ImageView(new Image("/resources/Town.png")));
    }

}
