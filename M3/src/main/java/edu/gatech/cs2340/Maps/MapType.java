package edu.gatech.cs2340.Maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by marc on 9/11/15.
 */
public enum MapType {
    STANDARD, RANDOM;

    /**
     * @return ArrayList of MapTypes
     */
    public static List<MapType> getAllMapTypes() {
        return new ArrayList<MapType>(Arrays.asList(MapType.values()));
    }

}