package edu.gatech.cs2340.Maps;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.GameObject.Mule;
import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.GameObject.ResourceType;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
//note to self, the hashmap is inefficient because every tile has its own "map"
//wherease all MOUNTAINTWO will have same values


/**
 * Tile class.
 * @author Bilal, Marc, Nick
 * @version 1.0
 */
public abstract class Tile extends StackPane implements Serializable {
    private String color;
    private Player owner;
    private Mule mule;
    private transient ImageView iv;
    private MapController map;
    private Map<ResourceType, Integer> resourceTypeMap;
    private transient Image image;

    /**
     * Tile construct on Instantiation, we will not handle who owns it.
     * The constructor call will be made only on new map, and we will
     * set owners and other things as the game goes on
     */
    public Tile() {
        this.mule = null;

        this.iv = new ImageView();

        resourceTypeMap = new HashMap<>();

        this.setOnMouseEntered(event -> {
                this.toFront();
                if (this.owner == null && !(this instanceof TownTile)) {
                    this.setStyle("-fx-border-color:" + map.getGame()
                            .getCurrentPlayer().getColorString() + "; \n"
                            //#090a0c
                            + "-fx-border-insets:-5;\n"
                            + "-fx-border-radius:0;\n"
                            + "-fx-border-width:5.0");
                }
            });

        this.setOnMouseExited(event -> {
                this.setStyle(null);
            });

        this.setOnMouseClicked(event -> {
                map.getGame().pingFromTile(this); //sends a message to game
            });
    }

    /**
     * Calculates production for the tile.
     * @return Integer value of how much production was done for the tile
     */
    public int calculateProduction() {
        if (this.getOwner().getEnergy() > 0 && this.tileHasMule()) {
            this.getOwner().setEnergy(this.getOwner().getEnergy() - 1);
            ResourceType resourceType = this.getMuleResource();
            return this.getResourceTypeMap().get(resourceType);
        }

        return 0;
    }

    /**
     * @return Gets the Resource Type Map
     */
    public Map<ResourceType, Integer> getResourceTypeMap() {
        return this.resourceTypeMap;
    }

    /**
     * @return String form of color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param thisColor color to be set
     */
    public void setColor(String thisColor) {
        this.color = thisColor;
    }

    /**
     * Player returned.
     * @return Player owner of tile
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * @param newOwner set owner to this owner
     */
    public void setOwner(Player newOwner) {
        this.owner = newOwner;
        Image borderImage = new Image("/resources/ownerBorder"
                + ".png");
        WritableImage ownedBorderImage = new WritableImage(
                (int) borderImage.getWidth(), (int) borderImage.getHeight());
        PixelWriter ownedImageWriter = ownedBorderImage.getPixelWriter();
        Color ownerColor = owner.getColor();
        for (int i = 0; i < borderImage.getWidth(); i++) {
            for (int j = 0; j < borderImage.getHeight(); j++) {
                if (i < 9 || i > borderImage.getWidth() - 11) {
                    ownedImageWriter.setColor(i, j, ownerColor);
                } else if (j < 9 || j > borderImage.getHeight() - 9) {
                    ownedImageWriter.setColor(i, j, ownerColor);
                }
            }
        }
        ImageView border = new ImageView();
        border.setImage(ownedBorderImage);
        border.toFront();
        this.getChildren().add(border);
    }

    /**
     * @return get Mule of tile
     */
    public Mule getMule() {
        return mule;
    }

    /**
     * @return True if the tile has a mule
     */
    public boolean tileHasMule() {
        return this.mule != null;
    }

    /**
     * Places a mule on the tile.
     * @param mule1 Mule to be placed on tile
     */
    public void placeMule(Mule mule1) {
        //System.out.println(tile.getMule());
        if (this instanceof TownTile) {
            map.getGame().log("That is town. Mule lost.");
            map.getGame().getScene().setCursor(Cursor.DEFAULT);
            map.getGame().setState(Game.GameState.TURN);
        } else if (this.getOwner() != mule1.getPlayer()) {
            map.getGame().log("You do not own that property. Mule lost.");
            map.getGame().getScene().setCursor(Cursor.DEFAULT);
            map.getGame().setState(Game.GameState.TURN);
        } else if (this.getMule() != null) {
            map.getGame().log("Mule already place. You lose mule!!");
            map.getGame().getScene().setCursor(Cursor.DEFAULT);
            map.getGame().setState(Game.GameState.TURN);
        } else {
            map.getGame().log("Mule placed.");
            this.mule = mule1;
            ImageView iv2 = new ImageView(
                    this.getMuleResource().getImageString());
            iv2.toFront();
            this.getChildren().add((iv2));
            map.getGame().getScene().setCursor(Cursor.DEFAULT);
            map.getGame().setState(Game.GameState.TURN);
        }
        this.map.getGame().getCurrentPlayer().setMule(null);
    }

    /**
     * @return Gets the mule's resource
     */
    public ResourceType getMuleResource() {
        if (this.tileHasMule()) {
            return this.getMule().getResourceType();
        } else {
            return null;
        }
    }

    /**
     * @return food count
     */
    public int getFood() {
        return this.resourceTypeMap.get(ResourceType.FOOD);
    }

    /**
     * @param food set food amount
     */
    public void setFood(int food) {
        this.resourceTypeMap.put(ResourceType.FOOD, food);
    }

    /**
     * @return energy amount
     */
    public int getEnergy() {
        return this.resourceTypeMap.get(ResourceType.ENERGY);
    }

    /**
     * @param energy set energy to tile
     */
    public void setEnergy(int energy) {
        this.resourceTypeMap.put(ResourceType.ENERGY, energy);
    }

    /**
     * @return ore of tile
     */
    public int getOre() {
        return this.resourceTypeMap.get(ResourceType.ORE);
    }

    /**
     * @param ore set ore amount to tile
     */
    public void setOre(int ore) {
        this.resourceTypeMap.put(ResourceType.ORE, ore);
    }

    /**
     * @return crystite of tile
     */
    public int getCrystite() {
        return this.resourceTypeMap.get(ResourceType.CRYSTITE);
    }

    /**
     * @param crystite set crystite of tile
     */
    public void setCrystite(int crystite) {
        this.resourceTypeMap.put(ResourceType.CRYSTITE, crystite);
    }

    /**
     * Returns the Image View of the tile
     * @return the image view of the tile
     */
    public ImageView getImageView() {
        return iv;
    }

    /**
     * Sets the ImageView.
     * @param imageView ImageView to be set
     */
    public void setImageView(ImageView imageView) {
        this.iv = imageView;
    }

    /**
     * Sets the map.
     * @param map Map to be set
     */
    public void setMap(MapController map) {
        this.map = map;
    }

    /**
     * Loads the ImageView of the Tile.
     */
    public abstract void loadImageView();

    /**
     * Sets the image.
     * @param newImage Image to be set as.
     */
    public void setImage(Image newImage) {
        this.image = newImage;
    }

    /**
     * @return The image of tile.
     */
    public Image getImage() {
        return this.image;
    }

}
