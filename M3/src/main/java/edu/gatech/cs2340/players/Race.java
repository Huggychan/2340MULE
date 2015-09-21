package edu.gatech.cs2340.players;
/**
 *
 * @author Bilal, Marc
 * @version 1.0
 */
public enum Race {
    HUMAN, FLAPPER, BONZOID, UGAITE, BUZZITE;

    public String toString() {
        return name().substring(0,1) + name().toLowerCase().substring(1);
    }

}
