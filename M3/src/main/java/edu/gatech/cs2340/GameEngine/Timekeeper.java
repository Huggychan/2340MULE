package edu.gatech.cs2340.GameEngine;

/**
 * Created by snatarajan8 on 9/29/2015.
 */
public class Timekeeper implements Runnable {

    private Turn turn;
    private long startTime;

    public Timekeeper(Turn turn) {
        //this.setPriority(MIN_PRIORITY);
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
