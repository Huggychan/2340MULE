package edu.gatech.cs2340.GameObject;

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

    public enum ResourceType {FOOD, ENERGY, ORE, CRYSTITE};

    public Mule(/*Player player*/) {
//        this.player = player;
        player = new Player("John", Race.HUMAN, "Red");
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