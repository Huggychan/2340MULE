package edu.gatech.cs2340.Maps;

import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;


/**
 * Person Configuration Screen Controller - works with SceneBuilder
 * @author Bilal, Myron, Shyam
 * @version 1.0
 */

public class MapController implements Initializable {


    @FXML
    private ImageView imageView;
    @FXML
    private GridPane GridPane;
    @FXML
    private Rectangle pane00;
    @FXML
    private Rectangle pane10;
    @FXML
    private Rectangle pane20;
    @FXML
    private Rectangle pane01;
    @FXML
    private Rectangle pane11;
    @FXML
    private Rectangle pane21;
    @FXML
    private Rectangle pane02;
    @FXML
    private Rectangle pane12;
    @FXML
    private Rectangle pane22;

    /**
     * Initializes the fxml file
     * @param fxmlFileLocation Location of fxml file
     * @param resources Resources needed
     */
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        
        pane00.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent arg0) {
                if (pane00.getStroke() == Color.TRANSPARENT) {
                    System.out.println("Plot selected");
                    //for test
                    Color playerColor = Color.RED;
                    //end test
                    pane00.setStroke(playerColor);
                    int x = (int) pane00.getX();
                    int y = (int) pane00.getY();
//                    Tile tile = new Tile(person, tileType, muleType, x, y);
                    System.out.println(/*person.getName() + ", */"You have chosen plot (" + x + "," + y + ").");
                }
            }
        });
        
        pane10.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent arg0) {
                if (pane10.getStroke() == Color.TRANSPARENT) {
                    System.out.println("Plot selected");
                    //for test
                    Color playerColor = Color.BLUE;
                    //end test
                    pane10.setStroke(playerColor);
                    int x = (int) pane10.getX();
                    int y = (int) pane10.getY();
//                    Tile tile = new Tile(person, tileType, muleType, x, y);
                    System.out.println(/*person.getName() + ", */"You have chosen plot (" + x + "," + y + ").");
                }
            }
        });
        
        pane20.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent arg0) {
                if (pane20.getStroke() == Color.TRANSPARENT) {
                    System.out.println("Plot selected");
                    //for test
                    Color playerColor = Color.GREEN;
                    //end test
                    pane20.setStroke(playerColor);
                    int x = (int) pane20.getX();
                    int y = (int) pane20.getY();
//                    Tile tile = new Tile(person, tileType, muleType, x, y);
                    System.out.println(/*person.getName() + ", */"You have chosen plot (" + x + "," + y + ").");
                }
            }
        });
        
        pane01.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent arg0) {
                if (pane01.getStroke() == Color.TRANSPARENT) {
                    System.out.println("Plot selected");
                    //for test
                    Color playerColor = Color.ORANGE;
                    //end test
                    pane01.setStroke(playerColor);
                    int x = (int) pane01.getX();
                    int y = (int) pane01.getY();
//                    Tile tile = new Tile(person, tileType, muleType, x, y);
                    System.out.println(/*person.getName() + ", */"You have chosen plot (" + x + "," + y + ").");
                }
            }
        });
        
        pane11.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent arg0) {
                if (pane11.getStroke() == Color.TRANSPARENT) {
                    System.out.println("Plot selected");
                    //for test
                    Color playerColor = Color.PURPLE;
                    //end test
                    pane11.setStroke(playerColor);
                    int x = (int) pane11.getX();
                    int y = (int) pane11.getY();
//                    Tile tile = new Tile(person, tileType, muleType, x, y);
                    System.out.println(/*person.getName() + ", */"You have chosen plot (" + x + "," + y + ").");
                }
            }
        });
        pane21.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent arg0) {
                if (pane21.getStroke() == Color.TRANSPARENT) {
                    System.out.println("Plot selected");
                    //for test
                    Color playerColor = Color.YELLOW;
                    //end test
                    pane21.setStroke(playerColor);
                    int x = (int) pane21.getX();
                    int y = (int) pane21.getY();
//                    Tile tile = new Tile(person, tileType, muleType, x, y);
                    System.out.println(/*person.getName() + ", */"You have chosen plot (" + x + "," + y + ").");
                }
            }
        });
    }
}