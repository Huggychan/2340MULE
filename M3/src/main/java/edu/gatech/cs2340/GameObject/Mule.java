package edu.gatech.cs2340.GameObject;

import edu.gatech.cs2340.Game;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class Mule implements Serializable {

    private transient Color color;
    private String colorString;
    private Player player;
    private ResourceType resourceType;
    private transient Image image;
    private Game game;

    public Mule(Player player) {
        this.player = player;
        this.colorString = player.getColorString();
        setImageAndColor();
    }

    public void setImageAndColor() {
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

}