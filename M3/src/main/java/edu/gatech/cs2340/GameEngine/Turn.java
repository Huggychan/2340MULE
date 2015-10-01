package edu.gatech.cs2340.GameEngine;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.Maps.Tile;
import edu.gatech.cs2340.Maps.TileType;
import edu.gatech.cs2340.GameObject.Player;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Created by Nick on 9/22/2015.
 * @author Nick, Shyam
 */
public class Turn {
    private  Game game;
    private ArrayList<Player> players;
    private Label label;
    private LogService service;
    private int turnTime = 50;
    private Timeline timer;

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
        //startTimer();
        turnTimerCreator();
    }

    public void move(Tile tile) {
        if (tile.getTileType() == TileType.TOWN) {
            game.goToTown();
        }
    }

    public int getTurnTime() {
        return turnTime;
    }

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

    public void endPlayerTurn() {
        players.remove(game.getCurrentPlayer());
        if (players.isEmpty()) {
            game.incrementRound();
            game.startRound();
            label.setText("");
        } else {
            game.setCurrentPlayer(players.get(0));
            setTurnTime();
            //startTimer();
            turnTimerCreator();
        }

    }

    public void turnTimerCreator() {
        game.timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            private int turnTime;
            public int checker;

            public void handle(ActionEvent event) {
                timerMethod();
            }

            public void timerMethod() {
                turnTime = game.getTurn().getTurnTime();
                if (!game.getCurrentPlayer().equals(players.get(0))) {
                    timer.stop();
                    game.getTurn().getLabel().setText("");
                } else {
                    game.getTurn().getLabel().setText(turnTime - checker - 1 + " seconds remaining");
                    checker++;
                    if (turnTime - checker == 0) {
                        checker = 0;
                        if (game.getTownEntered()) {
                            System.out.println("in town");
                            game.getTown().onExitClicked();
                        }
                        game.getTurn().getLabel().setText("");
                        game.getTurn().getLabel().setText("Your have run out of time");
                        game.getTurn().endPlayerTurn();
                    }
                }
            }
        }));
        timer = game.timer;
        timer.setCycleCount(turnTime);
        timer.play();
    }

    public Label getLabel() {
        return label;
    }

    public LogService getService() {
        return service;
    }

}
