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
import javafx.scene.control.ToggleButton;
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
    private ToggleButton buySell;
    @FXML
    private Button confirmTransaction;
    @FXML
    private ImageView itemImage;
    @FXML
    private Pane backingPane;
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

    public void setStore(Store store) {
        this.store = store;
        this.updateInventory();
    }

    public void updateInventory() {
        items = FXCollections.observableArrayList();

        if (this.store.getMules().size() > 0) {
            this.items.add("MULE: " + this.store.getBASE_MULE_PRICE());
        }

        if (this.store.getCrystiteCount() > 0) {
            this.items.add("Crystite: " + this.store.getCRYSTITE_PRICE());
        }

        if (this.store.getFoodCount() > 0) {
            this.items.add("Food: " + this.store.getFOOD_PRICE());
        }

        if (this.store.getEnergyCount() > 0) {
            this.items.add("Energy: " + this.store.getENERGY_PRICE());
        }

        if (this.store.getOreCount() > 0) {
            this.items.add("Ore: " + this.store.getENERGY_PRICE());
        }

        listView.setItems(this.items);
    }
}
