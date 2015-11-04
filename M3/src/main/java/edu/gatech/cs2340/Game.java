package edu.gatech.cs2340;
//TODO fix stuff that violates Law of Demeter
import edu.gatech.cs2340.GameEngine.LandSelection;
import edu.gatech.cs2340.GameEngine.RandomEventGenerator;
import edu.gatech.cs2340.GameEngine.Turn;
import edu.gatech.cs2340.GameObject.Mule;
import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.GameObject.Store;
import edu.gatech.cs2340.Maps.EventLog;
import edu.gatech.cs2340.Maps.MapController;
import edu.gatech.cs2340.Maps.MapType;
import edu.gatech.cs2340.Maps.Tile;
import edu.gatech.cs2340.Maps.TownMapController;
import edu.gatech.cs2340.configs.GameConfigController;
import edu.gatech.cs2340.configs.PlayerConfigController;
import edu.gatech.cs2340.configs.SummaryController;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Game class
 */
public class Game extends Application implements Serializable {

    private ArrayList<Player> players;
    private ArrayList<String> colors;
    private int numPlayers;
    private MapType mapType;
    private Difficulty difficulty;
    private transient Stage stage;
    private int currentPlayerIndex;
    private GameState state;
    private int roundNumber;
    private transient LandSelection landselection;
    private Turn turn;
    private MapController map;
    private transient EventLog log;
    private Store store;
    private transient Scene scene;
    private transient TownMapController tmc;
    private transient SummaryController summaryController;
    private boolean townEntered = false;
    private boolean storeEntered = false;
    private transient Timeline timer;
    private transient RandomEventGenerator randomEventGenerator;
    private transient SerializableUtil serializableUtil;

    /**
     * This enum documents state of the game
     */
    public enum GameState {
        GAMECONFIG, PLAYERCONFIG, LANDSELECTION, TURN,
        AUCTION, STORE, TOWN, SUMMARY, MULE
    }

    /**
     * This enum documents game difficulty
     */
    public enum Difficulty {
        Beginner, Standard, Tournament;

        /**
         * @return Difficulties as an array
         */
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

    /**
     * The start method for the JavaFX window
     * @param stage the window
     * @throws Exception throws exceptions
     */
    @Override
    public void start(Stage stage) throws Exception {
        randomEventGenerator = new RandomEventGenerator(this);
        serializableUtil = new SerializableUtil();
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
        URL location = getClass().getResource("/resources/GameConfig.fxml");
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(location);
        loader.setClassLoader(this.getClass().getClassLoader());

        Parent root = loader.load();

        GameConfigController gcfgController = loader.getController();
        gcfgController.setGame(this);

        this.scene = new Scene(root, 1600, 900);
        stage.getIcons().add(new Image(
                Game.class.getResourceAsStream("/resources/mule.png")));
        stage.setTitle("MULE");
        stage.setScene(scene);
        stage.show();
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
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/resources/PlayerConfigScreen.fxml"));
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
     * Loads a previously saved game
     * @param stage the stage to put the loaded game into
     */
    public void loadGame(Stage stage) {
        log = new EventLog();
        randomEventGenerator = new RandomEventGenerator(this);
        serializableUtil = new SerializableUtil();
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/resources/Map.fxml"));
        loader.setClassLoader(this.getClass().getClassLoader());

        Parent newRoot = null;

        try {
            newRoot = loader.load();
        } catch (IOException e) {
            System.out.println("IOException loading Map.fxml");
            System.out.println(e.getMessage());
        }
        Tile[][] oldData = map.getTiles();
        this.map = loader.getController();
        this.map.setGame(this);
        this.scene = new Scene(newRoot, 1600, 900);
        stage.setScene(scene);
        stage.getScene().setRoot(newRoot);

        log = new EventLog();
        map.getStackPane().getChildren().add(log);
        StackPane.setAlignment(log, Pos.TOP_CENTER);
        stage.getScene().setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.SPACE) {
                    if (this.state == GameState.LANDSELECTION) {
                        landselection.buy(null, getCurrentPlayer());
                    }
                }
            });
        for (Player p : players) {
            p.setColor(p.getColorString());
        }
        for (int i = 0; i < map.getTiles().length; i++) {
            for (int j = 0; j < map.getTiles()[0].length; j++) {
                if (oldData[i][j].getOwner() != null) {
                    map.getTiles()[i][j].setOwner(oldData[i][j].getOwner());
                    if (oldData[i][j].getMule() != null) {
                        Mule mule = oldData[i][j].getMule();
                        mule.setImageAndColor();
                        map.getTiles()[i][j].placeMule(mule);
                    }
                }

            }
        }

        System.out.println(roundNumber);
        startRound();
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
            default:
                break;
            }
        }

        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/resources/Map.fxml"));
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
        stage.getScene().setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.SPACE) {
                    if (this.state == GameState.LANDSELECTION) {
                        landselection.buy(null, getCurrentPlayer());
                    }
                }
            });
        startRound();
    }

    /**
     * starts a round
     */
    public void startRound() {
        log.log("Round: " + roundNumber);
        state = GameState.LANDSELECTION;
        Collections.sort(this.players);
        currentPlayerIndex = 0;
//        TODO fix this section
//        summary();
        serializableUtil.saveGame(this);
        landselection = new LandSelection(this);
    }

    /**
     * starts turns after land selection is over
     */
    public void startTurns() {
        state = GameState.TURN;
        turn = new Turn(this);
        generateRandomEvent();
    }

    /**
     * Puts the town on top of the view stack
     */
    public void goToTown() {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/resources/TownMap.fxml"));
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

    /**
     * Puts the summary on top of the view stack
     */
    public void summary() {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/resources/Summary.fxml"));
        loader.setClassLoader(this.getClass().getClassLoader());

        Parent root = null;

        try {
            root = loader.load();
            root.toFront();
        } catch (IOException e) {
            System.out.println("IOException loading Summary.fxml");
            System.out.println(e.getMessage());
        }

        summaryController = loader.getController();
        summaryController.setGame(this);
        stage.getScene().setRoot(root);
        stage.setTitle("Summary!");
        stage.show();
    }

    /**
     * Compares player to Players list
     * @param player Player being compared
     * @return True if Player is already in list; false otherwise
     */
    public boolean comparePlayers(Player player) {
        boolean result = true;
        if (players.size() == 0) {
            return false;
        }
        for (Player p : players) {
            if (player.equals(p)) {
                return true;
            } else {
                result = false;
            }
        }
        return result;
    }

    /**
     * Logs a string to the logging label
     * @param s the string to log
     */
    public void log(String s) {
        log.log(s);
    }

    /**
     * player has clicked on a tile delegates work to other methods
     * @param tile the tile that was clicked
     */
    public void pingFromTile(Tile tile) {
        if (state == GameState.LANDSELECTION) {
            landselection.buy(tile, getCurrentPlayer());
        } else if (state == GameState.TURN) {
            turn.move(tile);
        } else if (state == GameState.MULE) {
            tile.placeMule(this.getCurrentPlayer().getMule());
        }
    }

    /**
     * Adds players to a game
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Gets the list of colors available
     * @return the list of colors
     */
    public ArrayList<String> getColors() {
        return this.colors;
    }

    /**
     * Gets the difficutly of the game
     * @return the difficulty of the game
     */
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     * Sets the game's difficulty
     * @param difficulty the difficulty to set
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gets the player whose turn it is currently
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     * Sets the currentPlayerIndex to the player passed in
     * @param p the player to set the currentPlayerIndex to
     */
    public void setCurrentPlayerIndex(Player p) {
        currentPlayerIndex = players.indexOf(p);
    }

    /**
     * Generates a random event
     */
    public void generateRandomEvent() {
        this.randomEventGenerator.generateRandom();
    }

    /**
     * increments the game's round
     */
    public void incrementRound() {
        roundNumber++;
    }

    /**
     * Gets the game's event log
     * @return the game's event log
     */
    public EventLog getLog() {
        return this.log;
    }

    /**
     * Gets the MapController
     * @return the game's MapController
     */
    public MapController getMap() {
        return this.map;
    }

    /**
     * Sets the game's maptype
     * @param mapType the type of map
     */
    public void setMapType(MapType mapType) {
        this.mapType = mapType;
    }

    /**
     * sets the number of players
     * @param numPlayers the number of players to set
     */
    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    /**
     * Gets the number of players
     * @return the number of players
     */
    public int getNumPlayers() {
        return this.numPlayers;
    }

    /**
     * Gets the game's players
     * @return the list of players
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Gets the round number
     * @return the round number
     */
    public int getRoundNumber() {
        return roundNumber;
    }

    /**
     * Sets the current state of the game
     * @param state the state to set it to
     */
    public void setState(GameState state) {
        this.state = state;
    }

    /**
     * Gets the game's store
     * @return the game's store
     */
    public Store getStore() {
        return this.store;
    }

    /**
     * Gets the game's scene
     * @return the game's scene
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Gets the game's stage
     * @return the game's stage
     */
    public Stage getStage() {
        return this.stage;
    }

    /**
     * gets whether the store is currently on top of the view stack
     * @return whether or not the store is currently on top of the view stack
     */
    public boolean getStoreEntered() {
        return this.storeEntered;
    }

    /**
     * toggles when the store is put on or taken off the top of the view stack
     */
    public void toggleStoreEntered() {
        this.storeEntered = !this.storeEntered;
    }
    /**
     * Gets the timer
     * @return the game's timer
     */
    public Timeline getTimer() {
        return timer;
    }

    /**
     * Sets the game's timer
     * @param timeline the timer to set it to
     */
    public void setTimer(Timeline timeline) {
        this.timer = timeline;
    }

    /**
     * Gets the TownMapController
     * @return the game's TownMapController
     */
    public TownMapController getTown() {
        return this.tmc;
    }

    /**
     * Get's the curren turn
     * @return the current turn
     */
    public Turn getTurn() {
        return this.turn;
    }

    /**
     * Gets whether or not the town is on the view stack
     * @return whether or not the town is on the view stack
     */
    public boolean getTownEntered() {
        return this.townEntered;
    }

    /**
     * Sets whether the town is on the view stack or not
     * @param bool whether or not the town is on the view stack
     */
    public void setTownEntered(boolean bool) {
        this.townEntered = bool;
    }
}