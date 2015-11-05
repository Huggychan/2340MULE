package edu.gatech.cs2340.GameObject;

public enum ResourceType {

    MULE(),
    FOOD("/resources/MULE_Food.png"),
    ENERGY("/resources/MULE_Energy.png"),
    ORE("/resources/MULE_Ore.png"),
    CRYSTITE("/resources/MULE_Crystite.png");

    private String imageString;

    /**
     * Default constructor.
     */
    ResourceType() {
    }

    /**
     * Constructor for ResourceType
     * @param string String to set as imageString
     */
    ResourceType(String string) {
        this.imageString = string;
    }

    /**
     * @return String of image of ResourceType
     */
    public String getImageString() {
        return this.imageString;
    }
}
