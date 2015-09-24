package edu.gatech.cs2340.GameEngine;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.Maps.Tile;
import edu.gatech.cs2340.Maps.TileType;
import edu.gatech.cs2340.players.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nick on 9/22/2015.
 */
public class LandSelection {
    private Game game;
    private LinkedList<Person> playersActive;

    public LandSelection(Game game) {
        this.game = game;
        playersActive = new LinkedList<>();
        playersActive.addAll(game.getPlayers());
    }

    public void buy(Tile tile) {
        if (tile.getTileType() == TileType.TOWN) {
            return;
        }
        if (game.getRoundNumber() == 1 || game.getRoundNumber() == 2) {
            if (tile.getOwner() == null) {
                tile.setOwner(game.getCurrentPlayer());
            }
        } else {
            if (game.getCurrentPlayer().getMoney() >= 300
                    && tile.getOwner() == null) {
                tile.setOwner(game.getCurrentPlayer());
                game.getCurrentPlayer().setMoney(game.getCurrentPlayer()
                        .getMoney() - 300);
            }
            if (game.getCurrentPlayer().getMoney() >= 300) {
                playersActive.add(game.getCurrentPlayer());
            }
        }
        playersActive.remove(game.getCurrentPlayer());
        if (playersActive.isEmpty()) {
            pass();
        } else {
            game.setCurrentPlayer(playersActive.peek());
        }
    }

    public void pass() {
        if (playersActive.isEmpty()) {
            game.startTurns();
        }
    }
}
