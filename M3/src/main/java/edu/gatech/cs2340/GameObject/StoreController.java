package edu.gatech.cs2340.GameObject;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.GameEngine.Turn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Person Configuration Screen Controller - works with SceneBuilder
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
    private Button confirmTransaction;
    @FXML
    private ImageView itemImage;
    @FXML
    private Pane backingPane;
    @FXML
    private Button leaveStore;

    private ObservableList<String> items;
    private Store store;

    private Game game;
    private Turn turn;
    private boolean isBuying;

    /**
     * @return Pane which is the backing pane
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
     * Sets the toggle button text
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

    public void onStoreLeave() {
        game.toggleStoreEntered();
        game.getMap().getStackPane().getChildren().remove(this.backingPane);
    }

    /**
     * Sets the store
     * @param store Store to be set
     */
    public void setStore(Store store) {
        this.store = store;
        if (store != null) {
            this.updateInventory();
        }
    }

    /**
     * Sets the game
     * @param game Game to be set
     */
    public void setGame(Game game) {
        this.game = game;
    }

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
        }
    }

    public void updateInventory() {
        items = FXCollections.observableArrayList();
        this.items.add("MULE: " + this.store.getBaseMulePrice() + " "
                + this.store.getMuleCount());

        this.items.add("Crystite: " + this.store.getCrystitePrice() + " "
                + this.store.getCrystiteCount());

        this.items.add("Food: " + this.store.getFoodPrice() + " "
                + this.store.getFoodCount());
        this.items.add("Ore: " + this.store.getSmithorePrice() + " "
                + this.store.getOreCount());
        this.items.add("Energy: " + this.store.getEnergyPrice() + " "
                + this.store.getEnergyCount());

        listView.setItems(this.items);
    }

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
