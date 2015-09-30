package edu.gatech.cs2340;

import edu.gatech.cs2340.GameEngine.LandSelection;
import edu.gatech.cs2340.GameEngine.Turn;
import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.GameObject.Store;
import edu.gatech.cs2340.Maps.*;
import edu.gatech.cs2340.configs.GameConfigController;
import edu.gatech.cs2340.configs.PlayerConfigController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game extends Application {

    private ArrayList<Player> GameObject;
    private ArrayList<String> colors;
    private int numPlayers;
    private MapType mapType;
    private Difficulty difficulty;
    private Stage stage;
    private int currentPlayerIndex;
    private GameState state;
    private int roundNumber;
    private LandSelection landselection;
    private Turn turn;
    private MapController map;
    private EventLog log;
    private Store store;
    private Scene scene;

    private enum GameState{GAMECONFIG, PLAYERCONFIG, LANDSELECTION, TURN,
        AUCTION}

    public enum Difficulty {
        Beginner, Standard, Tournament;

        public static ArrayList<Difficulty> getAllDifficulties() {
            return new ArrayList<>(Arrays.asList(values()));
        }
    }

    /**
     * Main
     * @param args Arguments passed
     */
    public static void main(String[] args) {
        Application.launch(Game.class, (java.lang.String[]) null);
    }
    @Override
    public void start(Stage stage) throws Exception {
        this.store = new Store();
        roundNumber = 1;
        GameObject = new ArrayList<>();
        colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Orange");
        colors.add("Yellow");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Purple");
        state = GameState.GAMECONFIG;
        URL location = getClass().getResource
                ("configs/GameConfig.fxml");
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(location);
        loader.setClassLoader(this.getClass().getClassLoader());

        Parent root = (Parent) loader.load();

        GameConfigController gcfgController = (GameConfigController)loader.getController();
        gcfgController.setGame(this);

        this.scene = new Scene(root, 1600, 900);
        stage.setTitle("MULE");
        stage.setScene(scene);
        stage.show();
    }
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    public void setMapType(MapType mapType) {
        this.mapType = mapType;
    }
    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void addPlayer(Player Player) {
        GameObject.add(Player);
    }

    /**
     * Setting configs only next state. passed in the state that just has been
     * finished. i.e. Gameconfig is state 0 player 1 is state 1, player 2 is
     * state 2
     * @param i the state number just completed
     */
    public void nextState(int i) {
        if (i == 0) {
            state = GameState.PLAYERCONFIG;
        }
        if (i < numPlayers) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("configs/PlayerConfigScreen.fxml"));
            loader.setClassLoader(this.getClass().getClassLoader());

            Parent newRoot = null;

            try {
                newRoot = (Parent) loader.load();
            } catch (IOException e) {
                System.out.println("IOException loading PlayerConfig.fxml");
                System.out.println(e.getMessage());
            }

            PlayerConfigController pController = (PlayerConfigController) loader.getController();
            pController.setGame(this);
            pController.setPlayerNumber(i + 1);
            stage.getScene().setRoot(newRoot);
        } else {
            startGame();
        }
    }


    /**
     * loads the map fxml and controller, starts the first round
     */
    private void startGame() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/resources/Map.fxml"));
        loader.setClassLoader(this.getClass().getClassLoader());

        Parent newRoot = null;

        try {
            newRoot = (Parent) loader.load();
        } catch (IOException e) {
            System.out.println("IOException loading Map.fxml");
            System.out.println(e.getMessage());
        }
        this.map = (MapController) loader.getController();
        this.map.setGame(this);

        stage.getScene().setRoot(newRoot);

        log = new EventLog();
        map.getStackPane().getChildren().add(log);
        StackPane.setAlignment(log, Pos.TOP_CENTER);
        startRound();
    }

    public void startRound() {
        state = GameState.LANDSELECTION;
        currentPlayerIndex = 0;
        landselection = new LandSelection(this);
    }

    public void startTurns() {
        state = GameState.TURN;
        turn = new Turn(this);
    }

    public void goToTown() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/resources/TownMap.fxml"));
        loader.setClassLoader(this.getClass().getClassLoader());

        Parent newRoot = null;

        try {
            newRoot = (Parent) loader.load();
            newRoot.toFront();
        } catch (IOException e) {
            System.out.println("IOException loading TownMap.fxml");
            System.out.println(e.getMessage());
        }
        TownMapController tmc = (TownMapController) loader.getController();
        tmc.setGame(this);
        tmc.setTurn(turn);
        this.map.getStackPane().getChildren().add(newRoot);
        this.map.getStackPane().getChildren().remove(log);
        this.map.getStackPane().getChildren().add(log);
        log.setTextFill(Paint.valueOf("black"));
    }

    /*
    public void nextTurn() {
        state = GameState.TURN;
        currentPlayerIndex = (currentPlayerIndex + 1) % GameObject.size();
    }*/



    public int getRoundNumber() {
        return roundNumber;
    }
    public void incrementRound() {
        roundNumber++;
    }

    /**
     * Compares Player to GameObject list
     * @param Player Player being compared
     * @return True if Player is already in list; false otherwise
     */
    public boolean comparePlayers(Player Player) {
        boolean result = true;
        if (GameObject.size() == 0) {
            return false;
        }
        for (Player p : GameObject) {
            if (Player.equals(p)) {
                return true;
            } else {
                result = false;
            }
        }
        return result;
    }

//temp change text text here.
    public void log(String s) {
        log.log(s);
    }
    /**
     * player has clicked on a tile delegates work to other methods
     * @param tile the tile that was clicked
     */
    public void pingFromTile(Tile tile) {
        if (state == GameState.LANDSELECTION) {
            landselection.buy(tile);
        } else if (state == GameState.TURN) {
            turn.move(tile);
        }
    }

    public List<Player> getPlayers() {
        return this.GameObject;
    }

    public ArrayList<String> getColors() {
        return this.colors; }

    public Player getCurrentPlayer() {
        return GameObject.get(currentPlayerIndex);
    }

    public void setCurrentPlayer(Player p) {
        currentPlayerIndex = GameObject.indexOf(p);
    }

    public MapController getMap() {
        return this.map;
    }

    public Turn getTurn() {
        return this.turn;
    }

    public EventLog getLog() {
        return this.log;
    }

    public Store getStore() {
        return this.store;
    }

    public Scene getScene() { return this.scene; }
}