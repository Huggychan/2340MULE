package edu.gatech.cs2340.GameEngine;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.Maps.Tile;
import edu.gatech.cs2340.Maps.TileType;
import edu.gatech.cs2340.players.Player;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Nick on 9/22/2015.
 */
public class Turn {
    private  Game game;
    private ArrayList<Player> players;
    public long timeStart;

    public Turn(Game game) {
        this.game = game;
        players = new ArrayList<>();
        players.addAll(game.getPlayers());
        game.setCurrentPlayer(players.get(0));
        timeStart = System.currentTimeMillis();
        //ExecutorService executor = Executors.newCachedThreadPool();
        //executor.submit(new Timekeeper(this));
    }

    public void move(Tile tile) {
        if (tile.getTileType() == TileType.TOWN) {
            game.goToTown();
        }
    }

    public void endPlayerTurn() {
        players.remove(game.getCurrentPlayer());
        if (players.isEmpty()) {
            game.incrementRound();
            game.startRound();
        } else {
            game.setCurrentPlayer(players.get(0));
        }
    }
}
