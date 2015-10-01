package edu.gatech.cs2340.GameEngine;

import edu.gatech.cs2340.Game;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * Created by snatarajan8 on 10/1/2015.
 */
public class LogService extends Service {

    private Game game;
    private int i;

    public LogService(Game game) {
        this.game = game;
        i = 30;
    }

    public void setI(int i) {
        this. i = i;
    }

    @Override
    protected Task createTask() {
        return new Task() {
            protected Object call() {
                game.getTurn().getLabel().setText(5 - i + " second(s) remaining");
                return null;
            }
        };
    }
}
