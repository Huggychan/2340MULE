package edu.gatech.cs2340.Maps;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.GameEngine.Turn;
import edu.gatech.cs2340.GameObject.Mule;
import edu.gatech.cs2340.GameObject.Player;
import edu.gatech.cs2340.GameObject.ResourceType;
import edu.gatech.cs2340.GameObject.StoreController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

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
    private Rectangle crystiteRect;
    @FXML
    private Rectangle electricityRect;
    @FXML
    private Rectangle waterRect;
    @FXML
    private Rectangle diamondRect;
    @FXML
    private Pane backPane;
    @FXML
    private Rectangle exitRect;

    private Game game;
    private Turn turn;
    private StoreController storeController;

    /**
     * Initializes the fxml file.
     * @param fxmlFileLocation Location of fxml file
     * @param resources Resources needed
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        backPane.requestFocus();

        backPane.setOnKeyPressed(event -> {
                System.out.println("key pressed");
            });
    }


    /**
     * Tests if the assay was clicked
     */
    public void onAssayClicked() {
        if (this.game.getCurrentPlayer().hasMule() && !muleHasResourceType()) {
            this.game.log("Select a resource type!");
        } else {
            System.out.println("assay clicked");
        }
    }

    /**
     * Calculates money for current player and gives them that much money
     */
    public void onPubClicked() {
        if (this.game.getCurrentPlayer().hasMule() && !muleHasResourceType()) {
            this.game.log("Select a resource type!");
        } else {
            Random r = new Random();

            double roundBonus
                    = (int) Math.ceil(game.getRoundNumber() / 4.0) * 50;

            int timeLeft = turn.getTimeRemaining();
            int timeBonus = (int) Math.ceil(timeLeft / 12.5) * 50;

            int multiplier = r.nextInt(timeBonus);

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
    }

    /**
     * Method that is run when Land Office is clicked
     */
    public void onLandOfficeClicked() {
        if (this.game.getCurrentPlayer().hasMule() && !muleHasResourceType()) {
            this.game.log("Select a resource type!");
        } else {
            System.out.println("land office clicked");
        }
    }

    /**
     * Loads the store interface
     */
    public void onStoreClicked() {
        if (this.game.getCurrentPlayer().hasMule() && !muleHasResourceType()) {
            this.game.log("Select your Resource Type!");
        } else {
            game.toggleStoreEntered();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/resources/Store.fxml"));
            loader.setClassLoader(this.getClass().getClassLoader());
            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("Exception loading store");
                System.out.println(e.getMessage());
            }
            StoreController sc = loader.getController();
            this.storeController = sc;
            sc.setGame(this.game);
            sc.setStore(this.game.getStore());
            this.game.getMap().getStackPane()
                    .getChildren().add(sc.getBackingPane());
        }
    }

    /**
     * Sets the mule's resource as energy
     */
    public void onEnergyClicked() {
        this.setMuleResourceType(ResourceType.ENERGY,
                game.getCurrentPlayer().getMule());
    }

    /**
     * Sets the mule's resource as ore
     */
    public void onOreClicked() {
        this.setMuleResourceType(ResourceType.ORE,
                game.getCurrentPlayer().getMule());
    }

    /**
     * Sets the mule's resource as food
     */
    public void onFoodClicked() {
        this.setMuleResourceType(ResourceType.FOOD,
                game.getCurrentPlayer().getMule());
    }

    /**
     * Sets the mule's resource as crystite
     */
    public void onCrystiteClicked() {
        this.setMuleResourceType(ResourceType.CRYSTITE,
                game.getCurrentPlayer().getMule());
    }

    /**
     * Sets the mule's resource as electricity
     */
    public void onElectricityClicked() {
        this.setMuleResourceType(ResourceType.ELECTRICITY,
                game.getCurrentPlayer().getMule());
    }

    /**
     * Sets the mule's resource as water
     */
    public void onWaterClicked() {
        this.setMuleResourceType(ResourceType.WATER,
                game.getCurrentPlayer().getMule());
    }

    /**
     * Sets the mule's resource as diamond
     */
    public void onDiamondClicked() {
        this.setMuleResourceType(ResourceType.DIAMOND,
                game.getCurrentPlayer().getMule());
    }

    /**
     * Sets the game
     * @param game Game to be set
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Sets the turn
     * @param turn Turn to be set
     */
    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    /**
     * Loads the map again
     */
    public void onExitClicked() {
        if (this.game.getCurrentPlayer().hasMule() && !muleHasResourceType()) {
            this.game.log("Please select a Resource Type before exiting");
        } else {
            this.game.getMap().getStackPane()
                    .getChildren().remove(this.backPane);
            game.getLog().setTextFill(Paint.valueOf("white"));
            game.setTownEntered(false);
        }
    }

    /**
     * @return True if the current mule has a resource type
     */
    public boolean muleHasResourceType() {
        return this.game.getCurrentPlayer().getMule().hasResourceType();
    }

    /**
     * get Store controller
     * @return Store Controller of the game
     */
    public StoreController getStoreController() {
    return storeController;
    }


    /**
     * Sets the mule resource type
     * @param resource Resource to set for the current mule
     * @param mule Mule to set resource type as
     */
    private void setMuleResourceType(ResourceType resource, Mule mule) {
        boolean canAfford = false;
        Player curr = game.getCurrentPlayer();

        if (curr.hasMule()) {
            if (resource == ResourceType.CRYSTITE && curr.getMoney() >= 100) {
                curr.decrementMoney(100);
                canAfford = true;
            } else if (resource == ResourceType.ENERGY
                    && curr.getMoney() >= 50) {
                curr.decrementMoney(50);
                canAfford = true;
            } else if (resource == ResourceType.FOOD
                    && curr.getMoney() >= 25) {
                curr.decrementMoney(25);
                canAfford = true;
            } else if (resource == ResourceType.ORE
                    && curr.getMoney() >= 75) {
                curr.decrementMoney(75);
                canAfford = true;
            } else if (resource == ResourceType.ELECTRICITY
                    && curr.getMoney() >= 60) {
                curr.decrementMoney(60);
                canAfford = true;
            } else if (resource == ResourceType.WATER
                    && curr.getMoney() >= 35) {
                curr.decrementMoney(35);
                canAfford = true;
            } else if (resource == ResourceType.DIAMOND
                    && curr.getMoney() >= 120) {
                curr.decrementMoney(120);
                canAfford = true;
            }


            if (resource == null || !this.game.getCurrentPlayer().hasMule()) {
                this.game.log("Buy a mule first!");

            } else if (mule.getResourceType()
                    == null && canAfford) {
                mule.setResourceType(resource);
                WritableImage writableImage =
                        mule.changeColor(game.getCurrentPlayer());
                this.game.getScene().setCursor(new ImageCursor(writableImage));
                mule.setResourceType(resource);
                System.out.println("Resource type: "
                        + this.game.getCurrentPlayer()
                        .getMule().getResourceType());
                onExitClicked();
                game.setState(Game.GameState.MULE);
            } else {
                this.game.log("Your MULE's resource type is already "
                        + this.game.getCurrentPlayer()
                        .getMule().getResourceType());
            }
        }
    }
}
