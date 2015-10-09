package edu.gatech.cs2340.Maps;

import edu.gatech.cs2340.GameObject.ResourceType;
import javafx.scene.image.Image;

/**
 * Created by marc on 10/5/15.
 */
public class RiverTile extends Tile {

    public RiverTile() {
        this.getImageView().setImage(new Image("/resources/River.png"));
        this.getChildren().add(this.getImageView());
        this.getResourceTypeMap().put(ResourceType.FOOD, 4);
        this.getResourceTypeMap().put(ResourceType.ENERGY, 2);
        this.getResourceTypeMap().put(ResourceType.ORE, 0);
        this.getResourceTypeMap().put(ResourceType.CRYSTITE, 0);
    }

    public int calculateProduction() {
        int amount = -1;
        if (this.getOwner().getEnergy() > 0 && this.tileHasMule()) {
            this.getOwner().setEnergy(this.getOwner().getEnergy() - 1);
            ResourceType resourceType = this.getMuleResource();
            amount = this.getResourceTypeMap().get(resourceType);
        }
        return amount;
    }
}
