package edu.gatech.cs2340.configs;
import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.GameObject.Race;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

// import javafx.fxml.FXMLLoader;
// import javafx.scene.control.ChoiceBox;
//import javafx.scene.paint.Color;

/**
 * Player Configuration Screen Controller - works with SceneBuilder.
 * @author Bilal, Myron, Shyam
 * @version 1.1
 */
public class PlayerConfigController implements Initializable {

    /**
     * Combo box for the Race.
     */
    @FXML
    private ComboBox<Race> race;
    /**
     * Combo box for the colors.
     */
    @FXML
    private ComboBox<String> color;
    /**
     * Button to start game.
     */
    @FXML
    private Button start;
    /**
     * Text field for the name.
     */
    @FXML
    private TextField name;
    /**
     * Label for the welcome.
     */
    @FXML
    private Label welcome;
    /**
     * Label for the title.
     */
    @FXML
    private Label title;

    /**
     * Number of players playing.
     */
    private int playerNumber;
    /**
     * The actual game.
     */
    private Game game;
    /**
     * Player of the game.
     */
    private Player player;
    /**
     * Array list of the colors.
     */
    private ArrayList<String> tempColor;

    /**
     * Initializes the fxml file.
     * @param fxmlFileLocation Location of fxml file
     * @param resources Resources needed
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        this.tempColor = new ArrayList<>();

        race.setItems(
                FXCollections.observableArrayList(Race.getAllRaces()));
        race.getSelectionModel().selectFirst();

        start.setOnAction(event -> {
                if (name.getText() == null || name.getText().trim().isEmpty()) {
                    welcome.setText("Name must include at least one character"
                            + "\nPlease enter a valid name");
                } else {
                    player = new Player(name.getCharacters().toString(),
                            race.getValue(), color.getValue());
                    if (game.comparePlayers(player)) {
                        welcome.setText("Please enter a different "
                                + "name or color");
                    } else {
                        game.addPlayer(player);
                        game.nextState(playerNumber);
                    }
                    //if someone could figure out how to delay before
                    //moving onto next part that would be gr8 m8
                }
            });
        start.defaultButtonProperty().bind(name.focusedProperty());
    }

    /**
     * Set the Game bro!
     * @param newGame the game to be set
     */
    public void setGame(Game newGame) {
        this.game = newGame;
        this.tempColor = game.getColors();

        for (Player p : game.getPlayers()) {
            tempColor.remove(p.getColorString());
        }

        color.setItems(
                FXCollections.observableArrayList(this.tempColor));
        color.getSelectionModel().selectFirst();
    }

    /**
     * Sets player number on Person Config Screen.
     * @param i Number for player
     */
    public void setPlayerNumber(final int i) {
        playerNumber = i;
        title.setText("Welcome Player " + playerNumber + "\n select your "
                + "stuff");
    }
}
