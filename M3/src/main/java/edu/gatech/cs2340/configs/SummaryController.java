package edu.gatech.cs2340.configs;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.players.Person;
import edu.gatech.cs2340.players.Race;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Bilal
 * @version 1.0
 */
public class SummaryController {
    @FXML
    private HBox first;
    @FXML
    private HBox second;
    @FXML
    private HBox third;
    @FXML
    private HBox fourth;
    @FXML
    private HBox summary;
    @FXML
    private Label firstText;
    @FXML
    private Label secondText;
    @FXML
    private Label thirdText;
    @FXML
    private Label fourthText;
    @FXML
    private Label summaryText;


    private Game game;

    /**
     * Initializes the fxml file
     * @param fxmlFileLocation Location of fxml file
     * @param resources Resources needed
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
//        firstText.setText(game.getPlayers().get(0).toString());
//        secondText.setText(game.getPlayers().get(1).toString());
//        thirdText.setText(game.getPlayers().get(2).toString());
//        fourthText.setText(game.getPlayers().get(3).toString());
    }

    /**
     * Set the Game bro!
     * @param game the game to be set
     */
    public void setGame(Game game) {
        this.game = game;
    }
}
