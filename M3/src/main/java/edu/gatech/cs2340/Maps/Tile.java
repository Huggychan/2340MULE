package edu.gatech.cs2340.Maps;

import edu.gatech.cs2340.GameObject.Mule;
import edu.gatech.cs2340.players.Person;

public class Tile {
    private String color;
    private Person owner;
    private TileType resource;
    private int food;
    private int energy;
    private int ore;
    private int crystite;
    private int xLoc;
    private int yLoc;
    private Mule mule;

    public int getxLoc() {
        return xLoc;
    }

    public void setxLoc(int xLoc) {
        this.xLoc = xLoc;
    }

    public int getyLoc() {
        return yLoc;
    }

    public void setyLoc(int yLoc) {
        this.yLoc = yLoc;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public TileType getResource() {return resource;}

    public Mule getMule() {return mule;}

    public void setMule(Mule mule) {this.mule = mule;}

    public void setResource(TileType resource) {
        this.resource = resource;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getOre() {
        return ore;
    }

    public void setOre(int ore) {this.ore = ore;}

    public int getCrystite() {
        return crystite;
    }

    public void setCrystite(int crystite) {
        this.crystite = crystite;
    }
}
