package edu.gatech.cs2340.GameEngine;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.Maps.Tile;
import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.Maps.TownTile;

import java.util.LinkedList;

/**
 * @author Nick and Shyam
 * @version 1.0
 */
public class LandSelection {
    /**
     * Game being used.
     */
    private Game game;
    /**
     * Players of the game.
     */
    private LinkedList<Player> players;

    /**
     * LandSelection constructor.
     * @param newGame the game that the land selection delegated
     */
    public LandSelection(Game newGame) {
        this.game = newGame;
        players = new LinkedList<>();
        players.addAll(game.getPlayers());
        /*
        The list will have one instance of player at a time, and if the player
        buys and still has enough money, they will be added back to the end of
        the list.
        */
    }

    /**
     * Purchases a tile for the game's current player
     * @param tile the tile to buy
     * @param player player for tile
     */
    public void buy(Tile tile, Player player) {
        if (tile == null) {
            if (game.getRoundNumber() > 2) {
                pass();
            }
            return;
        }
        if (tile instanceof TownTile) {
            return;
        }
        if (game.getRoundNumber() == 1 || game.getRoundNumber() == 2) {
            if (tile.getOwner() == null) {
                tile.setOwner(player);
                player.addTile(tile);
            } else {
                //TODO can't let person be removed
                game.log("someone already owns this plot");
                return; //i'm a fuckin genius
            }
        } else {
            if (player.getMoney() >= 300
                    && tile.getOwner() == null) {
                tile.setOwner(player);
                player.setMoney(player.getMoney() - 300);
                player.addTile(tile);
                //System.out.println(game.getCurrentPlayer().getMoney());
            } else if (tile.getOwner() != null) {
                return;
            }
            if (player.getMoney() >= 300) {
                players.add(player);
            }
        }
        players.remove(player);
        if (players.isEmpty()) {
            pass();
        } else {
            game.setCurrentPlayerIndex(players.peek());
        }
    }

    /**
     * Passes for the current player during the land selection phase
     */
    public void pass() {
        if (players.isEmpty()) {
            game.startTurns();
        } else {
            game.log(game.getCurrentPlayer().getName() + "Passes.");
            players.remove(game.getCurrentPlayer());
            if (players.isEmpty()) {
                pass();
            } else {
                game.setCurrentPlayerIndex(players.peek());
            }
        }
    }
}
