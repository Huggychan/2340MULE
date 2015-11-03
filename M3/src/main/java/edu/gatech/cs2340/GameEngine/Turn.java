package edu.gatech.cs2340.GameEngine;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.Maps.Tile;
import edu.gatech.cs2340.Maps.TownTile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import javafx.geometry.Rectangle2D;
//import javafx.stage.Screen;

/**
 * Created by Nick on 9/22/2015.
 * @author Nick, Shyam
 */
public class Turn implements Serializable {
    private  Game game;
    private ArrayList<Player> players;
    private transient Label label;
    private int turnTime = 50;
    private int timeRemaining;
    private transient Timeline timer;

    public boolean mule;
    private Stage stage;

    /**
     * Turn Constructor
     * @param game Game being played
     */
    public Turn(Game game) {
    this.game = game;
    players = new ArrayList<>();
    players.addAll(game.getPlayers());
    game.setCurrentPlayer(players.get(0));
    setTurnTime();
    label = new Label();
    game.getMap().getStackPane().getChildren().add(label);
    label.setFont(Font.font(24));
    StackPane.setAlignment(label, Pos.TOP_RIGHT);
    label.setTextFill(Paint.valueOf("white"));
    turnTimerCreator();
    mule = false;
}

    /**
     * moves to the selected tile
     * @param tile the tile to be moved to
     */
    public void move(Tile tile) {
        if (tile instanceof TownTile) {
            game.goToTown();
        }
    }

    /**
     * Gets the total turn time
     * @return the total turn time
     */
    public int getTurnTime() {
        return turnTime;
    }

    /**
     * sets the turn timer
     */
    public void setTurnTime() {
        Player player = players.get(0);
        if (player.getFood() >= 12) {
            turnTime = 50;
        } else if (player.getFood() > 8) {
            turnTime = 30;
        } else {
            turnTime = 5;
        }
    }

    /**
     * ends the current player's turn
     */
    public void endPlayerTurn() {
        game.setState(Game.GameState.TURN);
        game.getCurrentPlayer().setMule(null);
        game.getCurrentPlayer().setMuleBoughtThisTurn(false);
        this.game.getScene().setCursor(Cursor.DEFAULT);
        players.remove(game.getCurrentPlayer());
        game.getTimer().stop();

        if (players.isEmpty()) {
            calcProduction();
            game.incrementRound();
            game.startRound();
            label.setText("");
        } else {
            game.setCurrentPlayer(players.get(0));
            setTurnTime();
            turnTimerCreator();
            game.generateRandomEvent();
        }

    }

    /**
     * Gets the time remaining
     * @return the amount of time remaining
     */
    public int getTimeRemaining() {
        return this.timeRemaining;
    }

    /**
     * creates the timer
     */
    public void turnTimerCreator() {
        Timeline t = new Timeline(new KeyFrame(Duration.seconds(1), new
                EventHandler<ActionEvent>() {

            private int turnTime;
            public int checker;
            private Player player = game.getCurrentPlayer();

            public void handle(ActionEvent event) {
                timerMethod();
            }

            public void timerMethod() {
                turnTime = game.getTurn().getTurnTime();
                if (!game.getCurrentPlayer().equals(player)) {
                    game.getTurn().getLabel().setText("");
                    timer.stop();
                    if (players.size() != 0) {
                        turnTimerCreator();
                    }
                } else {
                    timeRemaining = turnTime - checker - 1;
                    game.getTurn().getLabel().setText(timeRemaining + " seconds "
                            + "remaining");
                    checker++;
                    if (turnTime - checker == 0) {
                        checker = 0;
                        if (game.getStoreEntered()) {
                            game.getTown().getStoreController().onStoreLeave();
                        }

                        if (game.getTownEntered()) {
                            game.getTown().onExitClicked();
                        }
                        game.getTurn().getLabel().setText("");
                        game.getTurn().getLabel().setText("Your have run out of time");
                        game.getTurn().endPlayerTurn();
                    }
                }
            }
        }));
        game.setTimer(t);
        timer = game.getTimer();
        timer.setCycleCount(turnTime);
        timer.play();
    }

    /**
     * @return Gets the label
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Calculates production for each player
     */
    private void calcProduction() {
        List<Player> players = game.getPlayers();
        for (Player p : players) {
            p.calcProduction();
        }
    }
}
