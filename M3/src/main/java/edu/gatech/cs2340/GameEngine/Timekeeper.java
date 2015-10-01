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
        //for (int i = 0; i < 5; i++) {
            while ((System.currentTimeMillis() - startTime) < 5000) {
            /*try {
                wait(1000);
                System.out.println("waiting");
            } catch (Exception e) {
                e.printStackTrace();
            }
            */
            }
            //game.getTurn().getService().setI(i);
            //game.getTurn().getService().start();
        //}
        System.out.println("Done waiting");
        if (game.getTownEntered()) {
            game.getTown().onExitClicked();
        }

        game.getTurn().endPlayerTurn();

    }
}
