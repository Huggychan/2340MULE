package edu.gatech.cs2340.GameEngine;

import edu.gatech.cs2340.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

/**
 * Created by snatarajan8 on 9/29/2015.
 * @author Shyam
 */
public class Timekeeper implements Runnable {

    private long startTime;
    private Game game;

    public Timekeeper(Game game) {
        this.game = game;
        startTime = System.currentTimeMillis();
    }

    public void run() {
        System.out.println("Starting");
        Long longInt = 1000 - (System.currentTimeMillis() - startTime);
        /*Label label = new Label();
        game.getMap().getStackPane().getChildren().add(label);
        label.setFont(javafx.scene.text.Font.font(24));
        StackPane.setAlignment(label, Pos.TOP_RIGHT);
        //label.setAlignment(Pos.TOP_RIGHT);
        label.setTextFill(Paint.valueOf("white"));
        label.setText("your time is up");
        */
        for (int i = 0; i < 30; i++) {
            while ((System.currentTimeMillis() - startTime) < 1000) {
            /*try {
                wait(1000);
                System.out.println("waiting");
            } catch (Exception e) {
                e.printStackTrace();
            }
            */
            }
            game.getLog().log(30 - i + " second(s) remaining");
        }
        System.out.println("Done waiting");
        if (game.getTownEntered()) {
            game.getTown().onExitClicked();
        }
        game.getTurn().endPlayerTurn();
    }
}
