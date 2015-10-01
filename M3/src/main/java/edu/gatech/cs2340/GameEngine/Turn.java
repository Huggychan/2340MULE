package edu.gatech.cs2340.GameEngine;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.Maps.Tile;
import edu.gatech.cs2340.Maps.TileType;
import edu.gatech.cs2340.GameObject.Player;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

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
        timer = game.getTimer();

        timer.setCycleCount(turnTime);
        timer.play();
    }

    public void move(Tile tile) {
        if (tile.getTileType() == TileType.TOWN) {
            game.goToTown();
        }
    }

    public void startTimer() {
        //ExecutorService executor = Executors.newCachedThreadPool();
        //executor.submit(new Timekeeper(game));
        Timekeeper t = new Timekeeper(game);
        t.execute();
    }

    public int getTurnTime() {
        return turnTime;
    }

    public void setTurnTime() {
        Player player = players.get(0);
        if (player.getFood() > 50) {
            turnTime = 50;
        } else if (player.getFood() > 20) {
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
        } else {
            game.setCurrentPlayer(players.get(0));
            setTurnTime();
            //startTimer();
            timer.setCycleCount(turnTime);
            timer.play();
        }

    }

    public Label getLabel() {
        return label;
    }

    public LogService getService() {
        return service;
    }

}
