package edu.gatech.cs2340.GameObject;

import edu.gatech.cs2340.Game;
import edu.gatech.cs2340.GameEngine.Turn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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


    public Pane getBackingPane() {
        return backingPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isBuying = false;

        this.setToggleButtonText();
    }

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

    public void setStore(Store store) {
        this.store = store;
        if (store != null) {
            this.updateInventory();
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }

    //WHERE ALL THE SHIT HAPPENS
    public void onConfirm() {
        String product = (String) this.listView.getSelectionModel()
                .getSelectedItem();
        if (product != null) {
            if (isBuying) {
                store.buy(null, game.getCurrentPlayer());
            } else {
                store.sell(null, game.getCurrentPlayer());
            }
        }
    }
    public void updateInventory() {
        items = FXCollections.observableArrayList();
        this.items.add("MULE: " + this.store.getBASE_MULE_PRICE() + " " +
                this.store.getMuleCount());

        this.items.add("Crystite: " + this.store.getCRYSTITE_PRICE() + " " +
                this.store.getCrystiteCount());

        this.items.add("Food: " + this.store.getFOOD_PRICE() + " " +
                this.store.getFoodCount());
        this.items.add("Ore: " + this.store.getSMITHORE_PRICE() + " " +
                this.store.getOreCount());
        this.items.add("Energy: " + this.store.getENERGY_PRICE() + " " +
                this.store.getEnergyCount());

        listView.setItems(this.items);
    }
}
