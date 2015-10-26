package edu.gatech.cs2340.GameEngine;


import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.GameObject.Player;

import java.util.Random;

public class RandomEventGenerator {

    private Random random;
    private Game game;

    /**
     * Constructor for RandomEventGenerator
     * @param game Game to be used with RandomEventGenerator
     */
    public RandomEventGenerator(Game game) {
        this.game = game;
        random = new Random();
    }

    /**
     * Gernerates a random event
     */
    public void generateRandom() {
        int num = random.nextInt(100);
        int m = (game.getRoundNumber() / 4 + 1) * 25;
        Player player = game.getCurrentPlayer();
        boolean lowest =
                player.equals(game.getPlayers().get(game.getNumPlayers() - 1));
        System.out.println("Score before: " + player.getScore());
        if (num < 27) {
            num %= 7;
            switch (num) {
            case 0:
                game.log(player.getName() + " JUST RECEIVED A PACKAGE "
                        + "FROM THE GT ALUMNI "
                        + "CONTAINING 3 FOOD AND 2 ENERGY UNITS.");
                player.setFood(player.getFood() + 3);
                player.setEnergy(player.getEnergy() + 2);
                break;
            case 1:
                game.log("A WANDERING TECH STUDENT REPAID " +  player.getName()
                        + "'s HOSPITALITY BY LEAVING TWO BARS OF ORE.");
                player.setOre(player.getOre() + 2);
                break;
            case 2:
                m *= 8;
                game.log("THE MUSEUM BOUGHT " + player.getName()
                        + "'s ANTIQUE PERSONAL COMPUTER FOR $" + m);
                player.setMoney(player.getMoney() + m);
                break;
            case 3:
                m *= 2;
                game.log(player.getName() + " FOUND A DEAD MOOSE RAT AND "
                                + "SOLD THE HIDE FOR $" + m);
                player.setMoney(player.getMoney() + m);
                break;
            case 4:
                if (lowest) {
                    break;
                }
                m *= 4;
                game.log("FLYING CAT-BUGS ATE THE ROOF OFF " + player.getName()
                        + "'s HOUSE. REPAIRS COST $" + m);
                player.setMoney(player.getMoney() - m);
                break;
            case 5:
                if (lowest) {
                    break;
                }
                game.log("MISCHIEVOUS UGA STUDENTS BROKE INTO "
                        + player.getName()
                        + "'s STORAGE SHED AND STOLE HALF YOUR FOOD.");
                player.setFood(player.getFood() / 2);
                break;
            case 6:
                if (lowest) {
                    break;
                }
                m *= 4;
                game.log(player.getName() + "'s SPACE GYPSY INLAWS MADE A "
                        + "MESS OF THE TOWN. "
                        + "TO CLEAN IT UP, IT COST YOU $" + m);
                player.setMoney(player.getMoney() - m);
                break;
            default:
                break;
            }
        }
        System.out.println("Score after: " + player.getScore());
    }




}
