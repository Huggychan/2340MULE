package edu.gatech.cs2340;

import edu.gatech.cs2340.Maps.MapType;
import edu.gatech.cs2340.configs.GameConfigController;
import edu.gatech.cs2340.players.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

public class Game extends Application{

    private ArrayList<Person> players;
    private int numPlayers;
    private MapType mapType;
    private String Difficulty;
    private enum GameState{GAMECONFIG, PLAYERCONFIG}
    private GameState state;


    public static void main(String[] args) {
        Application.launch(Game.class, (java.lang.String[]) null);
    }


    @Override
    public void start(Stage stage) throws Exception {
        state = GameState.GAMECONFIG;
        URL location = getClass().getResource
                ("configs/GameConfig.fxml");
        //NOTE:
        // using / in front of file name denotes that file is in src/main/resources
        FXMLLoader loader = new FXMLLoader(location);
        loader.setClassLoader(this.getClass().getClassLoader());

        Parent root = (Parent) loader.load();

        GameConfigController gcfgController = (GameConfigController)loader.getController();
        gcfgController.setGame(this);
        Scene scene = new Scene(root, 1600, 900);
        stage.setTitle("MULE");
        stage.setScene(scene);
        stage.show();
    }

    public void nextState() {
    }

    public void setDifficulty(String difficulty) {
        Difficulty = difficulty;
    }

    public void setMapType(MapType mapType) {
        this.mapType = mapType;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }
}