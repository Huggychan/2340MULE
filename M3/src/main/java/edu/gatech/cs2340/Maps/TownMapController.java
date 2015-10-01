package edu.gatech.cs2340.Maps;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.GameEngine.Turn;
import edu.gatech.cs2340.GameObject.Mule;
import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.GameObject.StoreController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.event.InputEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


/**
 * Player Configuration Screen Controller - works with SceneBuilder
 * @author Bilal, Myron, Shyam
 * @version 1.0
 */
public class TownMapController implements Initializable {

    @FXML
    private Rectangle assayRect;
    @FXML
    private Rectangle landOfficeRect;
    @FXML
    private Rectangle pubRect;
    @FXML
    private Rectangle storeRect;
    @FXML
    private Rectangle oreRect;
    @FXML
    private Rectangle energyRect;
    @FXML
    private Rectangle foodRect;
    @FXML
    private Pane backPane;
    @FXML
    private Rectangle playerSprite;
    @FXML
    private Rectangle exitRect;

    private Game game;
    private Turn turn;

    /**
     * Initializes the fxml file
     * @param fxmlFileLocation Location of fxml file
     * @param resources Resources needed
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        backPane.requestFocus();

        backPane.setOnKeyPressed(event -> {
            System.out.println("key pressed");
        });
    }

    @FXML
    private void handleOnKeyPressed(final InputEvent event) {
        System.out.println("Key pressed");
        System.out.println(event);
    }

    public void spriteMovement() {
        System.out.println("pressed");
    }

    public void onAssayClicked() {
        System.out.println("assay clicked");
    }

    public void onPubClicked() {
        Random r = new Random();
        //int money = calculate based off timer

        int roundBonus = 100; //round bonus?
        int timeLeft = 50;
        int timeBonus = (timeLeft / 50) * 150 + 50;
        int multiplier = r.nextInt(timeBonus);

        int money = roundBonus * multiplier;

        if (money > 250) {
            money = 250;
        }

        Player curr = this.game.getCurrentPlayer();

        curr.setMoney(curr.getMoney() + money);

        game.getLog().log(curr.getName() + " has won " + money + " "
                + "gambling");
        game.getTurn().endPlayerTurn();
        this.onExitClicked();
    }

    public void onLandOfficeClicked() {
        System.out.println("land office clicked");
    }

    public void onStoreClicked() {
        System.out.println("asdffdsa");
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/resources/Store.fxml"));
        loader.setClassLoader(this.getClass().getClassLoader());
        Parent newRoot = null;

        try {
            newRoot = (Parent) loader.load();
        } catch (IOException e) {
            System.out.println("IOException loading Store.fxml");
            System.out.println(e.getMessage());
            System.out.println("Cause: " + e.getCause());
            System.out.println(e.getStackTrace());
        }

        StoreController sc = (StoreController) loader.getController();
        sc.setStore(this.game.getStore());
        sc.setGame(this.game);
        this.game.getMap().getStackPane().getChildren().add(sc.getBackingPane
                ());

//        Comment code above
//        && comment out this code to see how Mules change color
//        Mule mule = new Mule();
//        Image image = mule.getImage();
//        WritableImage writableImage = mule.changeColor(game.getCurrentPlayer());
//        this.game.getScene().setCursor(new ImageCursor(writableImage));
//        System.out.println("New Mule!!");

    }

    public void onEnergyClicked() {
        System.out.println("energy");
    }

    public void onOreClicked() {
        System.out.println("ore");
    }

    public void onFoodClicked() {
        System.out.println("Food");
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
    public void onExitClicked() {
        System.out.println("exit");
        this.game.getMap().getStackPane().getChildren().remove(this.backPane);
        game.getLog().setTextFill(Paint.valueOf("white"));
    }
}
