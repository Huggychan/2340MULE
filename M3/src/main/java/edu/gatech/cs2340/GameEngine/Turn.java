package edu.gatech.cs2340.GameEngine;

import edu.gatech.cs2340.Game;

/**
 * Created by Nick on 9/22/2015.
 */
public class Turn {
    private  Game game;

    public Turn(Game game) {
        this.game = game;
        game.incrementRound();
        game.startRound();
    }
}
