package edu.gatech.cs2340.GameObject;

import edu.gatech.cs2340.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Person Configuration Screen Controller - works with SceneBuilder.
 * @author Nick
 * @version 1.0
 */
public class StoreController implements Initializable {

    @FXML
    private Label buySellLabel;
    @FXML
    private ListView listView;
    @FXML
    private Button buySell;
    @FXML
    private Pane backingPane;
    @FXML
    private Label playerInventoryLabel;

    private Store store;
    private Game game;
    private boolean isBuying;

    /**
     * @return Pane which is the backing pane.
     */
    public Pane getBackingPane() {
        return backingPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isBuying = false;

        this.setToggleButtonText();
    }

    /**
     * Sets the toggle button text.
     */
    public void setToggleButtonText() {
        this.isBuying = !this.isBuying;

        if (this.isBuying) {
            this.buySellLabel.setText("Buying");
            this.buySell.setText("Sell");
        } else {
            this.buySellLabel.setText("Selling");
            this.buySell.setText("Buy");
        }
    }

    /**
     * Toggles that you are out of store and removes the top pane.
     */
    public void onStoreLeave() {
        game.toggleStoreEntered();
        game.getMap().getStackPane().getChildren().remove(this.backingPane);
    }

    /**
     * Sets the store.
     * @param newStore Store to be set
     */
    public void setStore(Store newStore) {
        this.store = newStore;
        if (store != null) {
            this.updateInventory();
        }
    }

    /**
     * Sets the game.
     * @param newGame Game to be set
     */
    public void setGame(Game newGame) {
        this.game = newGame;

        this.updatePlayerInventory();
    }

    /**
     * Confirms for buying and selling.
     */
    public void onConfirm() {
        String resource = (String) this.listView.getSelectionModel()
                .getSelectedItem();

        ResourceType resourceType
                = this.getResourceTypeFromString(resource);

        System.out.println("Money: "
                + this.game.getCurrentPlayer().getMoney());

        boolean success = false;

        if (resource != null) {
            if (isBuying) {
                success = store.buy(resourceType, game.getCurrentPlayer());

                if (success && resource.contains("MULE")) {
                    Mule mule = this.game.getCurrentPlayer().getMule();
                    Image image = mule.getImage();
                    this.game.getScene().setCursor(new ImageCursor(image));
                    this.onStoreLeave();
                } else if (game.getCurrentPlayer().getMuleBoughtThisTurn()) {
                    game.getLog()
                            .log("You have already bought a mule this turn");
                }
            } else {
                success = store.sell(resourceType, game.getCurrentPlayer());
            }
        }
        if (success) {
            this.updateInventory();
            this.updatePlayerInventory();
        }
    }

    /**
     * Updates the inventory.
     */
    public void updateInventory() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.add("MULE: $" + this.store.getBaseMulePrice() + " Qty: "
                + this.store.getMuleCount());

        items.add("Crystite: $" + this.store.getCrystitePrice() + " Qty: "
                + this.store.getCrystiteCount());

        items.add("Food: $" + this.store.getFoodPrice() + " Qty: "
                + this.store.getFoodCount());
        items.add("Ore: $" + this.store.getSmithorePrice() + " Qty: "
                + this.store.getOreCount());
        items.add("Energy: $" + this.store.getEnergyPrice() + " Qty: "
                + this.store.getEnergyCount());
        items.add("Electricity: $" + this.store.getElectricityPrice() + " Qty: "
                + this.store.getElectricityCount());
        items.add("Water: $" + this.store.getWaterPrice() + " Qty: "
                + this.store.getWaterCount());
        items.add("Diamond: $" + this.store.getDiamondPrice() + " Qty: "
                + this.store.getDiamondCount());

        listView.setItems(items);
    }

    public void updatePlayerInventory() {
        String crystite = "Crystite: " + this.game.getCurrentPlayer().getCrystite() + "\n";
        String food = "Food: " + this.game.getCurrentPlayer().getFood() + "\n";
        String ore = "Ore: " + this.game.getCurrentPlayer().getOre() + "\n";
        String energy = "Energy: " + this.game.getCurrentPlayer().getEnergy() + "\n";
        String elec = "Electricity: " + this.game.getCurrentPlayer().getElectricity() + "\n";
        String water = "Water: " + this.game.getCurrentPlayer().getWater()  + "\n";
        String diamond = "Diamond: " + this.game.getCurrentPlayer().getDiamond() + "\n";

        playerInventoryLabel.setText(crystite + food + ore + energy + elec + water + diamond);
    }


    /**
     * @param prodTypeString Product resource type to search for
     * @return the ResourceType from the possible values
     */
    public ResourceType getResourceTypeFromString(String prodTypeString) {
        for (ResourceType pt : ResourceType.values()) {
            if (prodTypeString != null
                    && prodTypeString.toUpperCase().contains(pt.toString())) {
                return pt;
            }
        }

        throw new IllegalArgumentException("Product Type not allowed");
    }
}
