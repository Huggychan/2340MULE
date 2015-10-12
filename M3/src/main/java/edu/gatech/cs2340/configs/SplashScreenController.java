package edu.gatech.cs2340.configs;
import edu.gatech.cs2340.Game;
import javafx.animation.FillTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

// import javafx.fxml.FXMLLoader;
// import javafx.scene.control.ChoiceBox;
//import javafx.scene.paint.Color;

/**
 * Player Configuration Screen Controller - works with SceneBuilder
 * @author Bilal, Myron, Shyam
 * @version 1.1
 */
public class SplashScreenController implements Initializable {

    @FXML
    private Rectangle titleRect;
    @FXML
    private Rectangle namesRect;

    private Game game;

    /**
     * Initializes the fxml file
     * @param fxmlFileLocation Location of fxml file
     * @param resources Resources needed
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        FillTransition fillTransition = new FillTransition(
                Duration.millis(10000), titleRect, Color.RED, Color.BLUE);
        fillTransition.setCycleCount(4);
        fillTransition.setAutoReverse(true);
        fillTransition.play();

        ArrayList<Stop> stops = new ArrayList<>(4);
        stops.add(new Stop(0, Color.RED));
        stops.add(new Stop(.16, Color.ORANGE));
        stops.add(new Stop(.32, Color.YELLOW));
        stops.add(new Stop(.48, Color.GREEN));
        stops.add(new Stop(.64, Color.BLUE));
        stops.add(new Stop(.80, Color.VIOLET));

        LinearGradient linearGradient = new LinearGradient(namesRect.getX(),
                namesRect.getY(), namesRect.getX() + 1,
                namesRect.getY() + 1, true,
                CycleMethod.REPEAT, stops);
        namesRect.setFill(linearGradient);
    }

    /**
     * Set the Game bro!
     * @param game the game to be set
     */
    public void setGame(Game game) {
        this.game = game;
    }

}
