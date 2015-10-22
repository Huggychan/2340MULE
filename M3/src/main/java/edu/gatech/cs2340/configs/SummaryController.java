package edu.gatech.cs2340.configs;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.GameEngine.LandSelection;
import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.GameObject.Race;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    @FXML
    private Pane summaryPane;
    @FXML
    private Button showButton;

    private Game game;
    private Image firstPlace;
    private Image secondPlace;
    private Image thirdPlace;
    private Image fourthPlace;
    private LandSelection landSelection;
    private ArrayList<Player> players;

    /**
     * Initializes the fxml file
     * @param fxmlFileLocation Location of fxml file
     * @param resources Resources needed
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
//        firstPlace = new Image("resources/MULE_Humanoid.png");
//        firstImage.setImage(getPlayerImage(0));
//        secondPlace = new Image("resources/MULE_Bonzoid.png");
//        secondImage.setImage(secondPlace);
//        thirdPlace = new Image("resources/MULE_Buzzite.jpg");
//        thirdImage.setImage(thirdPlace);
//        fourthPlace = new Image("resources/MULE_Flapper.png");
//        fourthImage.setImage(fourthPlace);

//        summaryPane.setOnKeyPressed(event -> {
//            if (event.getCode().equals(KeyCode.ENTER)) {
//                printGame();
//            }
//        });
//        firstScore.setText(game.getPlayers().get(0).toString());
//        System.out.println(game.getCurrentPlayer());
    }


    public void setOnKeyPressed() {
        firstImage.setImage(getPlayerImage(0));
        secondImage.setImage(getPlayerImage(1));
        thirdImage.setImage(getPlayerImage(2));
        fourthImage.setImage(getPlayerImage(3));

        firstScore.setText(getPlayerScore(0));
        secondScore.setText(getPlayerScore(1));
        thirdScore.setText(getPlayerScore(2));
        fourthScore.setText(getPlayerScore(3));
//        landSelection = new LandSelection(this.game);
    }


    /**
     * @param game the game to be set
     */
    public void setGame(Game game) {
        this.game = game;
    }

    public Image getPlayerImage(int index) {
        if (index < game.getPlayers().size()) {
            Player player = this.game.getPlayers().get(index);
            String imageString = player.getRace().getImageString();
            return new Image(imageString);
        }
        return null;
    }

    public String  getPlayerScore(int index) {
        if (index < game.getPlayers().size()) {
            Player player = this.game.getPlayers().get(index);
            return player.getMoney() + "\n" + player.getLand() + "\n"
                    + player.getGoods() + "\n" + player.getScore();
        }
        return null;
    }
}
