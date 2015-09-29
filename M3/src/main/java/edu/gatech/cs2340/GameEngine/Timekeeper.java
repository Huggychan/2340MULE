package edu.gatech.cs2340.GameEngine;

import edu.gatech.cs2340.Game;

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
        startTime = turn.timeStart;
    }

    public void run() {
        System.out.println("Starting");
        while ((System.currentTimeMillis() - startTime) < 3000) {
        }
        System.out.println("Done waiting");
        turn.endPlayerTurn();

    }
}
