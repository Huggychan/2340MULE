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

    /**
     * Constructor for Mule
     * @param player Player for the Mule
     */
    public Mule(Player player) {
        this.player = player;
        this.colorString = player.getColorString();
        setImageAndColor();
    }

    /**
     * Sets image and color of mule
     */
    public void setImageAndColor() {
        this.color = player.getColor();
        image = new Image("/resources/mule.png");
    }

    /**
     * @return Gets the Resource Type of mule
     */
    public ResourceType getResourceType() {
        return resourceType;
    }

    /**
     * Sets the resource type
     * @param resourceType resource type to be set
     */
    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * Checks to see if the mule has a resource type
     * @return True if mule has a resource type
     */
    public boolean hasResourceType() {
        return resourceType != null;
    }

    /**
     * @return Color of mule
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return Color in string format
     */
    public String getColorString() {
        return colorString;
    }

    /**
     * @return Player of mule
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return Image of mule
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Changes color of Mule
     * @param player Player of the Mule
     * @return Image of the mule with changed color
     */
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