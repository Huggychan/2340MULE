package edu.gatech.cs2340.GameObject;
/**
 *
 * @author Bilal, Marc
 * @version 1.0
 */
public enum Race {
    HUMAN("/resources/MULE_Humanoid.png"),
    FLAPPER("/resources/MULE_Flapper.png"),
    BONZOID("/resources/MULE_Bonzoid.png"),
    UGAITE("/resources/MULE_Ugaite.jpg"),
    BUZZITE("/resources/MULE_Buzzite.jpg");

    private String imageString;

    /**
     * Constructor for Race.
     * @param newImageString String file location of image
     */
    Race(String newImageString) {
        this.imageString = imageString;
    }

    @Override
    public String toString() {
        return name().substring(0, 1) + name().toLowerCase().substring(1);
    }

    /**
     * @return String of Image location
     */
    public String getImageString() {
        return this.imageString;
    }
}
