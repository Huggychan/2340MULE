package edu.gatech.cs2340.players;
/**
 * Makes a Person with different elements
 * @author Bilal, Marc
 * @version 1.0
 */
public class Person {

    private String name;
    private Race race;
    private String color;

    /**
     * Gets the name
     * @return Name of Person
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the race
     * @return Race of Person
     */
    public Race getRace() {
        return race;
    }

    /**
     * Gets String form of Race
     * @return String form of Race
     */
    public String getRaceString() {
        return race.toString();
    }

    /**
     * Gets the color
     * @return Color of Person
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the Name of Person
     * @param name Name to be set for Person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the Race of Person
     * @param race RaCe to be set for Person
     */
    public void setRace(Race race) {
        this.race = race;
    }

    /**
     * Sets the Color of Person
     * @param color Color to be set for Person
     */
    public void setColor(String color) {
        this.color = color;
    }
}