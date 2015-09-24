package edu.gatech.cs2340.Maps;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.GameEngine.Turn;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.awt.event.InputEvent;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Person Configuration Screen Controller - works with SceneBuilder
 * @author Bilal, Myron, Shyam
 * @version 1.0
 */
public class TownMapController implements Initializable {

    @FXML
    private Rectangle assayRect;
    @FXML
    private Rectangle landOfficeRect;
    @FXML
    private Rectangle pubRect;
    @FXML
    private Rectangle storeRect;
    @FXML
    private Rectangle oreRect;
    @FXML
    private Rectangle energyRect;
    @FXML
    private Rectangle foodRect;
    @FXML
    private Pane backPane;
    @FXML
    private Rectangle playerSprite;
    @FXML
    private Rectangle exitRect;

    private Game game;
    private Turn turn;

    /**
     * Initializes the fxml file
     * @param fxmlFileLocation Location of fxml file
     * @param resources Resources needed
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        backPane.requestFocus();

        backPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent arg0) {
                System.out.println("fffff");
            }
        });
    }

    @FXML
    private void handleOnKeyPressed(final InputEvent event) {
        System.out.println("Key pressed");
        System.out.println(event);
    }

    public void spriteMovement() {
        System.out.println("pressed");
    }

    public void onAssayClicked() {
        System.out.println("assay clicked");
    }

    public void onPubClicked() {
        System.out.println("pub clicked");
    }

    public void onLandOfficeClicked() {
        System.out.println("land office clicked");
    }

    public void onStoreClicked() {
        System.out.println("store clicked");
    }

    public void onEnergyClicked() {
        System.out.println("energy");
    }

    public void onOreClicked() {
        System.out.println("ore");
    }

    public void onFoodClicked() {
        System.out.println("Food");
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
    public void onExitClicked() {
        System.out.println("exit");
        turn.endPlayerTurn();
        this.game.getMap().getStackPane().getChildren().remove(this.backPane);
    }
}
