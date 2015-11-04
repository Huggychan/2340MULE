package edu.gatech.cs2340.configs;
import edu.gatech.cs2340.Game;
import javafx.animation.FillTransition;
import javafx.animation.PathTransition;
//import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
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
 * Player Configuration Screen Controller - works with SceneBuilder.
 * @author Bilal, Myron, Shyam
 * @version 1.1
 */
public class SplashScreenController implements Initializable {

    @FXML
    private Rectangle titleRect;
    @FXML
    private Rectangle namesRect;
    @FXML
    private Pane splashPane;
    @FXML
    private ImageView muleImage;

    private Game game;

    /**
     * Initializes the fxml file.
     * @param fxmlFileLocation Location of fxml file
     * @param resources Resources needed
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        FillTransition fillTransition = new FillTransition(
                Duration.millis(5000), titleRect, Color.RED, Color.BLUE);
        fillTransition.setCycleCount(2);
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

        Path path = new Path();
        path.getElements().add(new MoveTo(muleImage.getX() + 50,
                muleImage.getY() + 50));
        path.getElements().add(new LineTo(muleImage.getX() + 50, muleImage
                .getY() + 350));
        path.getElements().add(new LineTo(muleImage.getX()
                + 1450, muleImage.getY() + 350));
        path.getElements().add(new LineTo(muleImage.getX()
                + 1450, muleImage.getY() - 300));
        path.getElements().add(new LineTo(muleImage.getX()
                + 50, muleImage.getY() - 300));
        path.getElements().add(new LineTo(muleImage.getX()
                + 50, muleImage.getY() - 50));

        PathTransition pathTransition
                = new PathTransition(Duration.millis(10000), path, muleImage);
//        FillTransition mulePath = new FillTransition(Duration.millis(10000),
//                path, Color.RED, Color.BLUE);

        pathTransition.play();
//        mulePath.play();

//        FillTransition muleTransition1 = new FillTransition(
//                Duration.millis(10000), muleImage, Color.RED, Color.YELLOW);



//        System.out.println(game.getPlayers().toString());

//        splashPane.setOnKeyPressed(event -> {
//            if (event.getCode().equals(KeyCode.ENTER)) {
//                System.out.println("pressing enter");
//            }
//        });
    }

    /**
     * Set the Game bro!
     * @param newGame the game to be set
     */
    public void setGame(Game newGame) {
        this.game = newGame;
    }

}
