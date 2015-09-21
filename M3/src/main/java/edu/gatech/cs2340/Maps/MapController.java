package edu.gatech.cs2340.Maps;

import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import java.awt.event.InputEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
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
    private GridPane mapPane;
    @FXML
    private Rectangle rectzerozero;
    
    /**
     * Initializes the fxml file
     * @param fxmlFileLocation Location of fxml file
     * @param resources Resources needed
     */
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        mapPane.requestFocus();
        
        rectzerozero.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @FXML
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.PRIMARY) {
                    System.out.println("mouse clicked");
                }
            }
        });
    }

    @FXML
    private void handleOnMouseClicked(final InputEvent event) {
        System.out.println("mouse clicked");
        rectzerozero.setStroke(Color.BLUE);
    }

    @FXML
    public void tileClicked() {
        System.out.println("tile clicked");
        rectzerozero.setStroke(Color.BLUE);
    }
}