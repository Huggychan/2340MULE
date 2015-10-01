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
        for (int i = 0; i < game.getTurn().getTurnTime(); i++) {
            while ((System.currentTimeMillis() - startTime) < 1000 + 1000 * i) {
            /*try {
                wait(1000);
                System.out.println("waiting");
            } catch (Exception e) {
                e.printStackTrace();
            }
            */
            }
            System.out.println(game.getTurn().getTurnTime() - 1 - i + " seconds remaining");
            //game.getTurn().getService().start();
        }
        System.out.println("Done waiting");
        if (game.getTownEntered()) {
            System.out.println("in town");
            game.getTown().onExitClicked();
        }
        //game.getTurn().getLabel().setText("your turn is over");
        game.getTurn().endPlayerTurn();
    }

    public void execute() {
        new Thread(this, "timer").start();
    }
}
