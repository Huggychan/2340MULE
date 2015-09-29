package edu.gatech.cs2340.GameObject;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.GameEngine.Turn;
import edu.gatech.cs2340.GameObject.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import javax.swing.text.html.ImageView;
import javax.swing.text.html.ListView;
import java.awt.event.InputEvent;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


/**
 * Person Configuration Screen Controller - works with SceneBuilder
 * @author Nick
 * @version 1.0
 */
public class StoreController implements Initializable {

    @FXML
    private ListView items;
    @FXML
    private ToggleButton buySell;
    @FXML
    private Button confirmTransaction;
    @FXML
    private ImageView itemImage;
    @FXML
    private Pane backingPane;

    private Game game;
    private Turn turn;



    public Pane getBackingPane() {
        return backingPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
