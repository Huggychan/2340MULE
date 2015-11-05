package edu.gatech.cs2340.GameObject;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * Mule in the Game.
 * @author Myron, Nick, Marc, Bilal, Shyam
 */
public class Mule implements Serializable {

    private transient Color color;
    private String colorString;
    private Player player;
    private ResourceType resourceType;
    private transient Image image;
//    private Game game;
    private static final long serialVersionUID = 2L;

    /**
     * Constructor for Mule.
     * @param newPlayer Player for the Mule
     */
    public Mule(Player newPlayer) {
        this.player = newPlayer;
        this.colorString = player.getColorString();
        setImageAndColor();
    }

    /**
     * Sets image and color of mule.
     */
    public final void setImageAndColor() {
        this.color = player.getColor();
        image = new Image("/resources/mule.png");
    }

    /**
     * @return Gets the Resource Type of mule.
     */
    public ResourceType getResourceType() {
        return resourceType;
    }

    /**
     * Sets the resource type.
     * @param newResourceType resource type to be set
     */
    public void setResourceType(ResourceType newResourceType) {
        this.resourceType = newResourceType;
    }

    /**
     * Checks to see if the mule has a resource type.
     * @return True if mule has a resource type
     */
    public boolean hasResourceType() {
        return resourceType != null;
    }

    /**
     * @return Color of mule.
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
     * Changes color of Mule.
     * @param newPlayer Player of the Mule
     * @return Image of the mule with changed color
     */
    public WritableImage changeColor(Player newPlayer) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color newColor = newPlayer.getColor();
                if (!(pixelReader.getColor(x, y).equals(Color.TRANSPARENT))) {
                    pixelWriter.setColor(x, y, newColor);
                }
            }
        }
        return writableImage;
    }

}
