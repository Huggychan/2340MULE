package edu.gatech.cs2340.configs;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.Maps.MapType;
import edu.gatech.cs2340.SerializableUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// import javafx.fxml.FXMLLoader;

/**
 * Created by Nick on 9/10/2015. not marc
 */
public class GameConfigController implements Initializable {

    /**
     * Choice box for difficulties.
     */
    @FXML
    private ChoiceBox<Game.Difficulty> difficultyChoiceBox;
    /**
     * Choice box for Number of players.
     */
    @FXML
    private ChoiceBox<Integer> numPlayersBox;
    /**
     * Choice box for map type.
     */
    @FXML
    private ChoiceBox<MapType> mapBox;
    /**
     * Pane for the game.
     */
    @FXML
    private Pane gameConfigPane;
    /**
     * Button used to load the saved game.
     */
    @FXML
    private Button loadGameButton;
    @FXML
    private ImageView standardMapImageView;

    /**
     * Game being used.
     */
    private Game game;

    /**
     * Initialize method.
     * @param fxmlFileLocation Location of file
     * @param resources Resources
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        difficultyChoiceBox.setItems(FXCollections.observableArrayList(Game
                .Difficulty.getAllDifficulties()));
        difficultyChoiceBox.getSelectionModel().selectFirst();

        numPlayersBox.setItems(FXCollections.observableArrayList(2, 3, 4));
        numPlayersBox.getSelectionModel().selectFirst();

        mapBox.setItems(FXCollections.observableArrayList(MapType
                .getAllMapTypes()));
        mapBox.getSelectionModel().selectFirst();
        mapBox.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable,
                                        Number oldValue, Number newValue) {
                        if (newValue.equals(0)) {
                            standardMapImageView.setVisible(true);
                        } else {
                            standardMapImageView.setVisible(false);
                        }
                    }
        });

        gameConfigPane.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    passToGame();
                }
            });
    }

    /**
     * Set the Game bro!
     * @param newGame the game to be set
     */
    public void setGame(Game newGame) {
        this.game = newGame;
    }

    /**
     * pass the game data back to the game class bro!
     */
    private void passToGame() {
        if (difficultyChoiceBox.getValue() != null
                && numPlayersBox.getValue() != null
                && mapBox.getValue() != null) {
            game.setDifficulty(difficultyChoiceBox.getValue());
            //IMPLEMENTING MAP TYPE LATER
//            game.setMapType(mapBox.getValue());
            game.setNumPlayers(numPlayersBox.getValue());
            game.nextState(0);
        }
    }

    /**
     * Used for loading a previously saved game.
     */
    public void loadGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Game Save");
        fileChooser.setInitialDirectory(new File("src/main/java/saves/"));
        File file = fileChooser.showOpenDialog(this.game.getStage());
        SerializableUtil serializableUtil = new SerializableUtil();
        try {
            serializableUtil.loadGame(file, game.getStage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
