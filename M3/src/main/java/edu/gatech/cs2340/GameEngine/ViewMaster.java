package edu.gatech.cs2340.GameEngine;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.Maps.EventLog;
import edu.gatech.cs2340.Maps.TownMapController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

import java.io.IOException;

/**
 * Class: ViewMaster created on 10/9/15
 *
 * @author Marc Lacayo
 * @version 1.0
 */

public class ViewMaster {

    private final String RESOURCES = "/resources/";

    private Game game;
    private StackPane backingStackPane;
    private EventLog eventLog;

    /**
     * Pushes town UI onto view stack, returns controller so game can use it
     * @param currentTurn the current turn, needs it for some reason
     * @return the TownMapController that will be attached to game
     */
    public TownMapController goToTowm(Turn currentTurn) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                (RESOURCES + "TownMap.fxml"));
        loader.setClassLoader(this.getClass().getClassLoader());

        Parent newRoot = null;
        try {
            newRoot = loader.load();
            newRoot.toFront();
        } catch (IOException e) {
            System.out.println("IOException loading TownMap.fxml");
            System.out.println(e.getMessage());
        }

        TownMapController tmc = loader.getController();
        tmc.setGame(this.game);
        tmc.setTurn(currentTurn);

        this.addToStackPane(newRoot);
        this.logToFront();
        eventLog.setTextFill(Paint.valueOf("black"));
        this.game.setTownEntered(true);

        return tmc;
    }

    /**
     * shows summary screen
     */
    public void showSummarry() {
        //TODO do this shit
    };

    public void nextState() {};

    /**
     * adds somethign to front of stackpane
     * @param node
     */
    public void addToStackPane(Parent node) {
        node.toFront();
        this.backingStackPane.getChildren().addAll(node);
    }

    public void logToFront() {
        this.eventLog.toFront();
    }
}
