package edu.gatech.cs2340.GameEngine;


import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.GameObject.Player;

import java.util.Random;

public class RandomEventGenerator {

    /**
     * Random object to calculate random numbers.
     */
    private Random random;
    /**
     * Game being used.
     */
    private Game game;

    /**
     * Constructor for RandomEventGenerator.
     * @param newGame Game to be used with RandomEventGenerator
     */
    public RandomEventGenerator(Game newGame) {
        this.game = newGame;
        random = new Random();
    }

    /**
     * Generates a random event.
     */
    public void generateRandom() {
        int num = random.nextInt(100);
        int m = (game.getRoundNumber() / 4 + 1) * 25;
        Player player = game.getCurrentPlayer();
        boolean lowest =
                player.equals(game.getPlayers().get(game.getNumPlayers() - 1));
        System.out.println("Score before: " + player.getScore());
        if (num < 27) {
            num %= 9;
            switch (num) {
                case 0:
                    game.log(player.getName() + " JUST RECEIVED A PACKAGE "
                            + "FROM THE GT ALUMNI "
                            + "CONTAINING 3 FOOD AND 2 ENERGY UNITS.");
                    player.setFood(player.getFood() + 3);
                    player.setEnergy(player.getEnergy() + 2);
                    break;
                case 1:
                    game.log("A WANDERING TECH STUDENT REPAID " + player.getName()
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

                //Additional Random Events

                case 7:
                    if (lowest) {
                        break;
                    }
                    m *= 4;
                    game.log(player.getName() + "'s WAREHOUSE WAS RAIDED BY "
                            + "SPACE BANDITS"
                            + "THEY STOLE $" + m + " AND DISAPPEARED");
                    player.setMoney(player.getMoney() - m);
                    break;
                case 8:
                    game.log("A SMALL METEOR MADE OF DIAMOND LANDS IN "
                            + player.getName() + "'s LAND. HE GAINS 10 DIAMOND.");
                    player.setDiamond(player.getDiamond() + 10);
                    break;
                default:
                    break;
            }
        } else if (num < 42) {

        }
//        System.out.println("Score after: " + player.getScore());
    }




}
