package edu.gatech.cs2340;
//TODO fix stuff that violates Law of Demeter
import edu.gatech.cs2340.GameEngine.LandSelection;
import edu.gatech.cs2340.GameEngine.Turn;
import edu.gatech.cs2340.GameObject.Mule;
import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.GameObject.Store;
import edu.gatech.cs2340.Maps.*;
import edu.gatech.cs2340.configs.GameConfigController;
import edu.gatech.cs2340.configs.PlayerConfigController;
import javafx.animation.Timeline;
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
import java.util.Collections;
import java.util.List;

public class Game extends Application {

    private ArrayList<Player> players;
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
    private Mule mule;
    private MapController map;
    private EventLog log;
    private Store store;
    private Scene scene;
    private TownMapController tmc;
    private boolean townEntered = false;
    private boolean storeEntered = false;
    public Timeline timer;

    public enum GameState{GAMECONFIG, PLAYERCONFIG, LANDSELECTION, TURN,
        AUCTION, STORE, TOWN, SUMMARY, MULE}

    public enum Difficulty {
        Beginner, Standard, Tournament;

        public static ArrayList<Difficulty> getAllDifficulties() {
            return new ArrayList<>(Arrays.asList(values()));
        }
    }

    public boolean getStoreEntered() {
        return this.storeEntered;
    }

    public void toggleStoreEntered() {
        this.storeEntered = !this.storeEntered;
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
        players = new ArrayList<>();
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

        Parent root = loader.load();

        GameConfigController gcfgController = loader.getController();
        gcfgController.setGame(this);

        this.scene = new Scene(root, 1600, 900);
        stage.setTitle("MULE");
        stage.setScene(scene);
        stage.show();
    }
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    public void setState(GameState state) {
        this.state = state;
    }
    public Difficulty getDifficulty() {
        return this.difficulty;
    }
    public void setMapType(MapType mapType) {
        this.mapType = mapType;
    }
    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void addPlayer(Player Player) {
        players.add(Player);
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
                newRoot = loader.load();
            } catch (IOException e) {
                System.out.println("IOException loading PlayerConfig.fxml");
                System.out.println(e.getMessage());
            }

            PlayerConfigController pController = loader.getController();
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
        for (Player p : this.getPlayers()) {
            switch (this.getDifficulty()) {
                case Beginner:
                    p.setFood(8);
                    p.setEnergy(4);
                    p.setOre(0);
                    p.setCrystite(0);
                    break;
                case Standard:
                    p.setFood(4);
                    p.setEnergy(2);
                    p.setOre(0);
                    p.setCrystite(0);
                    break;
                case Tournament:
                    p.setFood(4);
                    p.setEnergy(2);
                    p.setOre(0);
                    p.setCrystite(0);
                    break;
            }
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/resources/Map.fxml"));
        loader.setClassLoader(this.getClass().getClassLoader());

        Parent newRoot = null;

        try {
            newRoot = loader.load();
        } catch (IOException e) {
            System.out.println("IOException loading Map.fxml");
            System.out.println(e.getMessage());
        }
        this.map = loader.getController();
        this.map.setGame(this);

        stage.getScene().setRoot(newRoot);

        log = new EventLog();
        map.getStackPane().getChildren().add(log);
        StackPane.setAlignment(log, Pos.TOP_CENTER);
        startRound();
    }

    public void startRound() {
        state = GameState.LANDSELECTION;
        Collections.sort(this.players);
        currentPlayerIndex = 0;
        landselection = new LandSelection(this);
    }

    public void startTurns() {
        state = GameState.TURN;
        turn = new Turn(this);
    }

    public void placeMule() {
        state = GameState.MULE;
        mule = this.getCurrentPlayer().getMule();
    }

    public void goToTown() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/resources/TownMap.fxml"));
        loader.setClassLoader(this.getClass().getClassLoader());

        Parent newRoot = null;

        try {
            newRoot = loader.load();
            newRoot.toFront();
        } catch (IOException e) {
            System.out.println("IOException loading TownMap.fxml");
            System.out.println(e.getMessage());
        }
        tmc = loader.getController();
        tmc.setGame(this);
        tmc.setTurn(turn);
        this.map.getStackPane().getChildren().add(newRoot);
        this.map.getStackPane().getChildren().remove(log);
        this.map.getStackPane().getChildren().add(log);
        log.setTextFill(Paint.valueOf("black"));
        townEntered = true;
    }

    public void summaryScreen() {
//        state = GameState.SUMMARY;
        System.out.println(System.getProperty("user.dir"));
    }

    /*
    public void nextTurn() {
        state = GameState.TURN;
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }*/



    public int getRoundNumber() {
        return roundNumber;
    }

    public Timeline getTimer() {
        return timer;
    }

    public void incrementRound() {
        roundNumber++;
    }

    /**
     * Compares Player to Players list
     * @param Player Player being compared
     * @return True if Player is already in list; false otherwise
     */
    public boolean comparePlayers(Player Player) {
        boolean result = true;
        if (players.size() == 0) {
            return false;
        }
        for (Player p : players) {
            if (Player.equals(p)) {
                return true;
            } else {
                result = false;
            }
        }
        return result;
    }

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
        } else if (state == GameState.MULE) {
            tile.placeMule(this.getCurrentPlayer().getMule());
        }
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public ArrayList<String> getColors() {
        return this.colors; }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void setCurrentPlayer(Player p) {
        currentPlayerIndex = players.indexOf(p);
    }

    public MapController getMap() {
        return this.map;
    }

    public TownMapController getTown() {
        return this.tmc;
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

    public boolean getTownEntered() {
        return this.townEntered;
    }

    public void setTownEntered(boolean bool) {
        this.townEntered = bool;
    }
}