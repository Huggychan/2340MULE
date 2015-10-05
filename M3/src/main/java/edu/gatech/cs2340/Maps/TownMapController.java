package edu.gatech.cs2340.Maps;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.GameEngine.Turn;
import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.GameObject.ResourceType;
import edu.gatech.cs2340.GameObject.StoreController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.image.WritableImage;
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
    private StoreController storeController;

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

        double roundBonus = (int) Math.ceil(game.getRoundNumber() / 4.0) * 50;

        int timeLeft = turn.getTimeRemaining();
        int timeBonus = (int) Math.ceil(timeLeft / 12.5) * 50;

        int multiplier = r.nextInt(timeBonus);

        System.out.println(timeLeft);

        int money = (int) roundBonus * multiplier;

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
        game.toggleStoreEntered();
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/resources/Store.fxml"));
        loader.setClassLoader(this.getClass().getClassLoader());
        Parent newRoot = null;

        try {
            newRoot = loader.load();
        } catch (IOException e) {
            System.out.println("IOException loading Store.fxml");
            System.out.println(e.getMessage());
            System.out.println("Cause: " + e.getCause());
            System.out.println(e.getStackTrace());
        }

        StoreController sc = loader.getController();
        this.storeController = sc;
        sc.setStore(this.game.getStore());
        sc.setGame(this.game);
        this.game.getMap().getStackPane().getChildren().add(sc.getBackingPane
                ());
    }

    public void onEnergyClicked() {
        this.setMuleResourceType(ResourceType.ENERGY);
    }

    public void onOreClicked() {
        this.setMuleResourceType(ResourceType.ORE);
    }

    public void onFoodClicked() {
        this.setMuleResourceType(ResourceType.FOOD);
    }

    public void onCrystiteClicked() {
        this.setMuleResourceType(ResourceType.CRYSTITE);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
    public void onExitClicked() {
        this.game.getMap().getStackPane().getChildren().remove(this.backPane);
        game.getLog().setTextFill(Paint.valueOf("white"));
        game.setTownEntered(false);
    }

    public StoreController getStoreController() {
        return storeController;
    }

    private void setMuleResourceType(ResourceType resource) {
        if (resource == null || this.game.getCurrentPlayer().getMule() == null) {
            this.game.log("Buy a mule first!");
        } else if (this.game.getCurrentPlayer().getMule().getResourceType()
                == null) {
            this.game.getCurrentPlayer().getMule().setResourceType(resource);
            WritableImage writableImage =
                    this.game.getCurrentPlayer().getMule().changeColor(game.getCurrentPlayer());
            this.game.getScene().setCursor(new ImageCursor(writableImage));
            System.out.println("Resource type: "
                    + this.game.getCurrentPlayer().getMule().getResourceType());
            onExitClicked();
            game.placeMule();
        } else {
            this.game.log("Your MULE's resource type is already "
                    + this.game.getCurrentPlayer().getMule().getResourceType());
        }
    }
}
