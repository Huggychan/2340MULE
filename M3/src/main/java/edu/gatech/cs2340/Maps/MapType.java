package edu.gatech.cs2340.Maps;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by marc on 9/11/15.
 */
public enum MapType {
    STANDARD, RANDOM;
    public static ArrayList<MapType> getAllMapTypes() {
        return new ArrayList<MapType>(Arrays.asList(MapType.values()));
    }

    public Tile[] getmapData() {
        return new Tile[1];
    }
}