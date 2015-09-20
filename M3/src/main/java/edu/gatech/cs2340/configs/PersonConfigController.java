package edu.gatech.cs2340.configs;
import java.net.URL;
import java.util.ResourceBundle;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.players.Person;
import edu.gatech.cs2340.players.Race;
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

/**
 * Person Configuration Screen Controller - works with SceneBuilder
 * @author Bilal, Myron, Shyam
 * @version 1.0
 */
public class PersonConfigController implements Initializable {

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
    private Person person;
    /**
     * Initializes the fxml file
     * @param fxmlFileLocation Location of fxml file
     * @param resources Resources needed
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        race.setItems(
                FXCollections.observableArrayList(
                        Race.HUMAN,
                        Race.FLAPPER,
                        Race.BONZOID,
                        Race.UGAITE,
                        Race.BUZZITE));
        race.getSelectionModel().selectFirst();
        color.setItems(
                FXCollections.observableArrayList(
                        "Red",
                        "Orange",
                        "Yellow",
                        "Green",
                        "Blue",
                        "Purple"));
        color.getSelectionModel().selectFirst();
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println(game.getPlayers());
                if (name.getText() == null || name.getText().trim().isEmpty()) {
                    welcome.setText("Name must include at least one character"
                            + "\nPlease enter a valid name");
                } else {
//                    welcome.setText("Welcome " + color.getValue() + " "
//                            + race.getValue() + " named "
//                            + name.getCharacters() + "!");
                    person = new Person(name.getCharacters().toString(),
                            race.getValue(), color.getValue());
                    //game.addPlayer(person);
                    //if someone could figure out how to delay before
                    //moving onto next part that would be gr8 m8
                    game.nextState(playerNumber);
                }
            }

        });
    }

    /**
     * Set the Game bro!
     * @param game the game to be set
     */
    public void setGame(Game game) {
        this.game = game;
    }
    public void setPlayerNumber(int i) {
        playerNumber = i;
        title.setText("Welcome Player " + playerNumber + "\n select your "
                + "stuff");
    }
}
