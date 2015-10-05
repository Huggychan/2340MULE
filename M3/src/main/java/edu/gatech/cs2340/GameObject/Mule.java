package edu.gatech.cs2340.GameObject;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.Maps.Tile;
import edu.gatech.cs2340.Maps.TileType;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Mule {

    private Color color;
    private String colorString;
    private Player player;
    private ResourceType resourceType;
    private Image image;
    private Game game;

    public Mule(Player player) {
        this.player = player;
        this.colorString = player.getColorString();
        this.color = player.getColor();
        image = new Image("/resources/mule.png");
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public boolean hasResourceType() {
        return resourceType != null;
    }
    public Color getColor() {
        return color;
    }

    public String getColorString() {
        return colorString;
    }

    public Player getPlayer() {
        return player;
    }

    public Image getImage() {
        return this.image;
    }

    public WritableImage changeColor(Player player) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = player.getColor();
                if (!(pixelReader.getColor(x, y).equals(Color.TRANSPARENT))) {
                    pixelWriter.setColor(x, y, color);
                }
            }
        }
        return writableImage;
    }

//    TODO fix adding mules to property
    public void placeMule(Tile tile){
        //System.out.println(tile.getMule());
        if (tile.getTileType() == TileType.TOWN) {
            System.out.println("That is town. Mule lost.");
            return;
        } else if (tile.getOwner() != player) {
            System.out.println("You do not own that property. Mule lost.");
            return;
        } else {
            System.out.println("Mule placed.");
            tile.setMuleResource(this.player.getMule().getResourceType());
            System.out.println("Your mule type on this tile is now: "
                    + tile.getMuleResource());
            this.game.getScene().setCursor(Cursor.DEFAULT);
            //this.game.getScene().getCursor();
            //game state to placeMule
            //cursor needs to be changed back, but didn't have time to find default
        }
    }
}