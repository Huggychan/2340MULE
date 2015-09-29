package edu.gatech.cs2340.GameEngine;

/**
 * Created by snatarajan8 on 9/29/2015.
 */
public class Timekeeper implements Runnable {

    private Turn turn;

    public Timekeeper(Turn turn) {
        this.turn = turn;
    }

    public void run() {
        while (turn.timeRemains()) {

        }
        turn.endPlayerTurn();
    }
}
