package edu.gatech.cs2340.GameEngine;

import edu.gatech.cs2340.Game;
import javafx.scene.control.Label;

/**
 * Created by snatarajan8 on 9/29/2015.
 */
public class Timekeeper implements Runnable {

    private Turn turn;
    private long startTime;
    private Game game;

    public Timekeeper(Turn turn, Game game) {
        this.game = game;
        this.turn = turn;
        startTime = System.currentTimeMillis();
    }

    public void run() {
        System.out.println("Starting");
        Long longInt = 3000 - (System.currentTimeMillis() - startTime);
        while ((System.currentTimeMillis() - startTime) < 3000) {
            try {
                wait(1000);
                System.out.println("waiting")
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done waiting");
        turn.endPlayerTurn();
        Label label = new Label();
        game.getMap().getStackPane().getChildren().add(label);
        label.setText(longInt.toString());
    }
}
