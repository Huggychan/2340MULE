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

    private Race(String imageString) {
        this.imageString = imageString;
    }

    public String toString() {
        return name().substring(0,1) + name().toLowerCase().substring(1);
    }

    public String getImageString() {
        return this.imageString;
    }

}