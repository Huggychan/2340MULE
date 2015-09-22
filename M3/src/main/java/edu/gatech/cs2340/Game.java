package edu.gatech.cs2340;

import edu.gatech.cs2340.GameEngine.LandSelection;
import edu.gatech.cs2340.GameEngine.Turn;
import edu.gatech.cs2340.Maps.*;
import edu.gatech.cs2340.configs.GameConfigController;
import edu.gatech.cs2340.configs.PersonConfigController;
import edu.gatech.cs2340.players.Person;
import edu.gatech.cs2340.players.Race;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game extends Application {

    private ArrayList<Person> players;
    private int numPlayers;
    private MapType mapType;
    private Difficulty difficulty;
    private enum GameState{GAMECONFIG, PLAYERCONFIG, LANDSELECTION, TURN,
        AUCTION}
    public enum Difficulty {
        Beginner, Standard, Tournament;

        public static ArrayList<Difficulty> getAllDifficulties() {
            return new ArrayList<>(Arrays.asList(values()));
        }
    }
    private Stage stage;


    private int currentPlayerIndex;
    private GameState state;
    private int roundNumber;
    private LandSelection landselection;
    private Turn turn;

    /**
     * Main
     * @param args Arguments passed
     */
    public static void main(String[] args) {
        Application.launch(Game.class, (java.lang.String[]) null);
    }
    @Override
    public void start(Stage stage) throws Exception {
        roundNumber = 1;
        players = new ArrayList<>();
        state = GameState.GAMECONFIG;
        URL location = getClass().getResource
                ("configs/GameConfig.fxml");
        this.stage = stage;
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
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    public void setMapType(MapType mapType) {
        this.mapType = mapType;
    }
    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
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
                    ("configs/PersonConfigScreen.fxml"));
            loader.setClassLoader(this.getClass().getClassLoader());

            Parent newRoot = null;

            try {
                newRoot = (Parent) loader.load();
            } catch (IOException e) {
                System.out.println("IOException loading PersonConfig.fxml");
                System.out.println(e.getMessage());
            }

            PersonConfigController pController = (PersonConfigController) loader.getController();
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
            System.out.println("IOException loading PersonConfig.fxml");
            System.out.println(e.getMessage());
        }
        Map map = (Map) loader.getController();
        map.setGame(this);

        loader.getController();
        stage.getScene().setRoot(newRoot);
        startRound();
    }
    public void startRound() {
        state = GameState.LANDSELECTION;
        currentPlayerIndex = 0;
        landselection = new LandSelection(this);
    }
    public void startTurns() {
        turn = new Turn(this);
    }
    public void goToTown() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/resources/Town.fxml"));
        loader.setClassLoader(this.getClass().getClassLoader());

        Parent newRoot = null;

        try {
            newRoot = (Parent) loader.load();
        } catch (IOException e) {
            System.out.println("IOException loading Town.fxml");
            System.out.println(e.getMessage());
        }
        TownMapController tmc = (TownMapController) loader.getController();
        tmc.setGame(this);

        stage.getScene().setRoot(newRoot);
    }
    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
    public int getRoundNumber() {
        return roundNumber;
    }



    public void incrementRound() {
        roundNumber++;
    }
    public void addPlayer(Person person) {
        players.add(person);
    }

    /**
     * Compares person to players list
     * @param person Person being compared
     * @return True if person is already in list; false otherwise
     */
    public boolean comparePlayers(Person person) {
        boolean result = true;
        if (players.size() == 0) {
            return false;
        }
        for (Person p : players) {
            if (person.equals(p)) {
                return true;
            } else {
                result = false;
            }
        }
        return result;
    }

    /**
     * player has clicked on a tile delegates work to other methods
     * @param tile the tile that was clicked
     */
    public void pingFromTile(Tile tile) {
        if (state == GameState.LANDSELECTION) {
            landselection.buy(tile);
        }
    }

    public List<Person> getPlayers() {
        return this.players;
    }

    public Person getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
    public void setCurrentPlayer(Person p) {
        currentPlayerIndex = players.indexOf(p);
    }
}