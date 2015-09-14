package edu.gatech.cs2340.configs;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Nick on 9/10/2015.
 */
public class GameConfigController implements Initializable {

    @FXML
    private ChoiceBox<String> difficultyChoiceBox;
    @FXML
    private ChoiceBox<Integer> numPlayersBox;
    @FXML
    private ChoiceBox<String> mapBox;

    /**
     * Initialize method
     * @param fxmlFileLocation Location of file
     * @param resources Resources
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        difficultyChoiceBox.setItems(FXCollections.observableArrayList(
            "Easy", "Medium", "Hard"));
        difficultyChoiceBox.getSelectionModel().selectFirst();
        numPlayersBox.setItems(FXCollections.observableArrayList(1, 2, 3, 4));
        numPlayersBox.getSelectionModel().selectFirst();
        mapBox.setItems(FXCollections.observableArrayList(
            "Map1", "Map2", "Map3"));
        mapBox.getSelectionModel().selectFirst();
    }
}
