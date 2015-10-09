package edu.gatech.cs2340.configs;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.GameObject.Race;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Bilal
 * @version 1.0
 */
public class SummaryController implements Initializable {
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
    private ImageView firstImage;
    @FXML
    private ImageView secondImage;
    @FXML
    private ImageView thirdImage;
    @FXML
    private ImageView fourthImage;
    @FXML
    private Label firstScore;
    @FXML
    private Label secondScore;
    @FXML
    private Label thirdScore;
    @FXML
    private Label fourthScore;
    @FXML
    private Label summaryScore;


    private Game game;
    private Image firstPlace;
    private Image secondPlace;
    private Image thirdPlace;
    private Image fourthPlace;

    /**
     * Initializes the fxml file
     * @param fxmlFileLocation Location of fxml file
     * @param resources Resources needed
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        firstPlace = new Image("resources/MULE_Humanoid.png");
        firstImage.setImage(firstPlace);
        secondPlace = new Image("resources/MULE_Bonzoid.png");
        secondImage.setImage(secondPlace);
        thirdPlace = new Image("resources/MULE_Buzzite.jpg");
        thirdImage.setImage(thirdPlace);
        fourthPlace = new Image("resources/MULE_Flapper.png");
        fourthImage.setImage(fourthPlace);
//        firstScore.setText(game.getPlayers().get(0).toString());
//        System.out.println(game.getCurrentPlayer());
    }

    /**
     * @param game the game to be set
     */
    public void setGame(Game game) {
        this.game = game;
    }
}
