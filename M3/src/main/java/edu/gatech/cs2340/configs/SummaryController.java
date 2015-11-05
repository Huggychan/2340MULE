package edu.gatech.cs2340.configs;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.GameEngine.LandSelection;
import edu.gatech.cs2340.GameObject.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

//import edu.gatech.cs2340.GameObject.Race;
//import javafx.collections.FXCollections;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import java.io.IOException;

/**
 * @author Bilal
 * @version 1.0
 */
public class SummaryController implements Initializable {
    /**
     * HBox of first place.
     */
    @FXML
    private HBox first;
    /**
     * HBox of second place.
     */
    @FXML
    private HBox second;
    /**
     * HBox of third place.
     */
    @FXML
    private HBox third;
    /**
     * HBox of fourth place.
     */
    @FXML
    private HBox fourth;
    /**
     * HBox of summary.
     */
    @FXML
    private HBox summary;
    /**
     * ImageView of first place.
     */
    @FXML
    private ImageView firstImage;
    /**
     * ImageView of second place.
     */
    @FXML
    private ImageView secondImage;
    /**
     * ImageView of third place.
     */
    @FXML
    private ImageView thirdImage;
    /**
     * ImageView of fourth place.
     */
    @FXML
    private ImageView fourthImage;
    /**
     * Label of first place score.
     */
    @FXML
    private Label firstScore;
    /**
     * Label of second place score.
     */
    @FXML
    private Label secondScore;
    /**
     * Label of third place score.
     */
    @FXML
    private Label thirdScore;
    /**
     * Label of fourth place score.
     */
    @FXML
    private Label fourthScore;
    /**
     * Label of summary score.
     */
    @FXML
    private Label summaryScore;
    /**
     * Pane of summary.
     */
    @FXML
    private Pane summaryPane;
    /**
     * Button that shows the scores.
     */
    @FXML
    private Button showButton;

    /**
     * Game being used.
     */
    private Game game;
    /**
     * Image of the firstPlace.
     */
    private Image firstPlace;
    /**
     * Image of the secondPlace.
     */
    private Image secondPlace;
    /**
     * Image of the thirdPlace.
     */
    private Image thirdPlace;
    /**
     * Image of the fourthPlace.
     */
    private Image fourthPlace;
    /**
     * LandSelection of the game.
     */
    private LandSelection landSelection;
    /**
     * Array list of the players.
     */
    private List<Player> players;

    /**
     * Initializes the fxml file.
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


    /**
     * Sets Images and Scores of Players.
     */
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
     * @param newGame the game to be set
     */
    public void setGame(Game newGame) {
        this.game = newGame;
    }

    /**
     * @param index Index of player in game
     * @return Image of Player
     */
    public Image getPlayerImage(int index) {
        if (index < game.getPlayers().size()) {
            Player player = this.game.getPlayers().get(index);
            String imageString = player.getRace().getImageString();
            return new Image(imageString);
        }
        return null;
    }

    /**
     * @param index Index of player in game.
     * @return Score of player
     */
    public String getPlayerScore(int index) {
        if (index < game.getPlayers().size()) {
            Player player = this.game.getPlayers().get(index);
            return player.getMoney() + "\n" + player.getLand() + "\n"
                    + player.getGoods() + "\n" + player.getScore();
        }
        return null;
    }
}
