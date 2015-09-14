package edu.gatech.cs2340.Maps;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by marc on 9/11/15.
 */
public enum MapType {
    MAP_1, MAP_2, MAP_3;
    public static ArrayList<MapType> getAllMapTypes() {
        return new ArrayList<MapType>(Arrays.asList(MapType.values()));
    }
}