package edu.gatech.cs2340.Maps;

import edu.gatech.cs2340.Game;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by Nick on 9/20/2015.
 */
public class Map implements Initializable {
    private Tile[][] tiles;
    private MapType maptype = MapType.STANDARD;
    private Game game;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setUpMap();
        } catch (IllegalArgumentException iae) {

        }
    }


    public void setMaptype(MapType maptype) {
        this.maptype = maptype;
    }

    private void setUpMap() throws IllegalArgumentException {
        tiles = new Tile[5][9];
        if (maptype == MapType.STANDARD) {
            try {
                File mapfile = new File("standardmap.txt");
                Scanner scan = new Scanner(mapfile);
                int row = 0;
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    for (int i = 0; i < line.length(); i++) {
                        tiles[row][i] = setUpTile(line.charAt(i));
                    }
                    row++;
                }
            } catch (Exception e) {
                System.out.println("This should never happen. MAP.IO");
            }
        }
    }

    private Tile setUpTile(char c) throws IllegalArgumentException {
        if (c == 'R') {
            return new Tile(TileType.RIVER);
        } else if (c == 'P') {
            return new Tile(TileType.PLAIN);
        } else if (c == 'M') {
            return new Tile(TileType.MOUNTAIN_1);
        } else if (c == 'N') {
            return new Tile(TileType.MOUNTAIN_2);
        } else if (c == 'O') {
            return new Tile(TileType.MOUNTAIN_3);
        } else if (c == 'T') {
            return new Tile(TileType.TOWN);
        } else {
            throw new IllegalArgumentException("No Such Tile type");
        }
    }
}
