package edu.gatech.cs2340.configs;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.GameObject.Race;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
// import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
//import javafx.scene.paint.Color;

/**
 * Player Configuration Screen Controller - works with SceneBuilder
 * @author Bilal, Myron, Shyam
 * @version 1.1
 */
public class PlayerConfigController implements Initializable {

    @FXML
    private ComboBox<Race> race;
    @FXML
    private ComboBox<String> color;
    @FXML
    private Button start;
    @FXML
    private TextField name;
    @FXML
    private Label welcome;
    @FXML
    private Label title;

    private int playerNumber;
    private Game game;
    private Player Player;
    private ArrayList<String> tempColor;

    /**
     * Initializes the fxml file
     * @param fxmlFileLocation Location of fxml file
     * @param resources Resources needed
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        this.tempColor = new ArrayList<>();

        race.setItems(
                FXCollections.observableArrayList(
                        Race.HUMAN,
                        Race.FLAPPER,
                        Race.BONZOID,
                        Race.UGAITE,
                        Race.BUZZITE));
        race.getSelectionModel().selectFirst();

        start.setOnAction(event -> {
            if (name.getText() == null || name.getText().trim().isEmpty()) {
                welcome.setText("Name must include at least one character"
                        + "\nPlease enter a valid name");
            } else {
                Player = new Player(name.getCharacters().toString(),
                        race.getValue(), color.getValue());
                if (game.comparePlayers(Player)) {
                    welcome.setText("Please enter a different name or color");
                } else {
                    game.addPlayer(Player);
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
     * @param game the game to be set
     */
    public void setGame(Game game) {
        this.game = game;
        this.tempColor = game.getColors();


        for (Player p : game.getPlayers()) {
            tempColor.remove(p.getColorString());
        }

        color.setItems(
                FXCollections.observableArrayList(this.tempColor));
        color.getSelectionModel().selectFirst();
    }
    public void setPlayerNumber(int i) {
        playerNumber = i;
        title.setText("Welcome Player " + playerNumber + "\n select your "
                + "stuff");
    }
}
