package edu.gatech.cs2340.GameEngine;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.Maps.Tile;
import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.Maps.TownTile;

import java.util.LinkedList;

public class LandSelection {
    private Game game;
    private LinkedList<Player> GameObjectActive;

    public LandSelection(Game game) {
        this.game = game;
        GameObjectActive = new LinkedList<>();
        GameObjectActive.addAll(game.getPlayers());
    }

    public void buy(Tile tile) {
        if (tile instanceof TownTile) {
            return;
        }
        if (game.getRoundNumber() == 1 || game.getRoundNumber() == 2) {
            if (tile.getOwner() == null) {
                tile.setOwner(game.getCurrentPlayer());
                game.getCurrentPlayer().addTile(tile);
            } else {
                //TODO can't let person be removed
                game.log("someone already owns this plot");
            }
        } else {
            if (game.getCurrentPlayer().getMoney() >= 300
                    && tile.getOwner() == null) {
                tile.setOwner(game.getCurrentPlayer());
                game.getCurrentPlayer().setMoney(game.getCurrentPlayer()
                        .getMoney() - 300);
                game.getCurrentPlayer().addTile(tile);
                //System.out.println(game.getCurrentPlayer().getMoney());
            }
            if (game.getCurrentPlayer().getMoney() >= 300) {
                GameObjectActive.add(game.getCurrentPlayer());
            }
        }
        GameObjectActive.remove(game.getCurrentPlayer());
        if (GameObjectActive.isEmpty()) {
            pass();
        } else {
            game.setCurrentPlayer(GameObjectActive.peek());
        }
    }

    public void pass() {
        if (GameObjectActive.isEmpty()) {
            game.startTurns();
        }
    }
}
