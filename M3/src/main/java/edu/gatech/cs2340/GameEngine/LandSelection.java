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
    private Game game;
    private LinkedList<Player> gameObjectActive;

    /**
     * LandSelection constructor
     * @param game Game for LandSelection
     */
    public LandSelection(Game game) {
        this.game = game;
        gameObjectActive = new LinkedList<>();
        gameObjectActive.addAll(game.getPlayers());
        /*this is a terrible name lol
        its also capitalized
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
                gameObjectActive.add(player);
            }
        }
        gameObjectActive.remove(player);
        if (gameObjectActive.isEmpty()) {
            pass();
        } else {
            game.setCurrentPlayerIndex(gameObjectActive.peek());
        }
    }

    /**
     * Passes for the current player during the land selection phase
     */
    public void pass() {
        if (gameObjectActive.isEmpty()) {
            game.startTurns();
        } else {
            game.log(game.getCurrentPlayer().getName() + "Passes.");
            gameObjectActive.remove(game.getCurrentPlayer());
            if (gameObjectActive.isEmpty()) {
                pass();
            } else {
                game.setCurrentPlayerIndex(gameObjectActive.peek());
            }
        }
    }
}
