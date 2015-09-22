package edu.gatech.cs2340.players;

import javafx.scene.paint.Color;

/**
 * Makes a Person with different elements
 * @author Bilal, Marc
 * @version 1.1
 */
public class Person {

    private String name;
    private Race race;
    private Color color;
    private String colorString;

    public Person(String name, Race race, String colorString) {
        this.name = name;
        this.race = race;
        this.colorString = colorString;
        switch (colorString) {
            case "Red":
                color = Color.RED;
                break;
            case "Orange":
                color = Color.ORANGE;
                break;
            case "Yellow":
                color = Color.YELLOW;
                break;
            case "Green":
                color = Color.GREEN;
                break;
            case "Blue":
                color = Color.BLUE;
                break;
            case "Purple":
                color = Color.PURPLE;
                break;
        }
    }


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
    public Color getColor() {
        return color;
    }

    public String getColorString() {
        return colorString.toLowerCase();
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
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.name + " " + this.color + " " + this.race;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Person)) return false;
        Person that = (Person)other;
        return this.name.equals(that.name) && this.color.equals(that.color);
    }

    @Override
    public int hashCode() {
        int result = 17;
        return 31 * result + name.hashCode() + color.hashCode();
    }
}