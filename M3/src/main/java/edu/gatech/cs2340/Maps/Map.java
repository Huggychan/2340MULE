package edu.gatech.cs2340.Maps;

import edu.gatech.cs2340.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
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
    @FXML
    private GridPane backingPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setUpMap();
        } catch (IllegalArgumentException iae) {
            System.out.println("Problem setting up map");
            System.out.println(iae.getMessage());
        }
    }


    public void setMaptype(MapType maptype) {
        this.maptype = maptype;
    }

    private void setUpMap() throws IllegalArgumentException {
        tiles = new Tile[5][9];
        if (maptype == MapType.STANDARD) {
            try {
                File mapfile = new File
                        ("M3/src/main/java/resources/standardmap.txt");
                Scanner scan = new Scanner(mapfile);
                int row = 0;
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    for (int i = 0; i < line.length(); i++) {
                        Tile t = setUpTile(line.charAt(i));
                        t.setMap(this);

                        GridPane.setColumnIndex(t, i);
                        GridPane.setRowIndex(t, row);

                        this.backingPane.getChildren().add(t);

                        tiles[row][i] = t;
                    }
                    row++;
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Trash tier coding");
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

    public void onMouseEntered() {
        System.out.println("asdf");
    }

    public void putBorderOnImageView(MouseEvent event) {
        DropShadow ds = new DropShadow(20, Color.BLACK);
    }

    public void setGame(Game game) {
        this.game = game;
    }
    public Game getGame() {
        return this.game;
    }
}
