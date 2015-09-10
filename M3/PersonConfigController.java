import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonConfigController implements Initializable {

    @FXML
    private ComboBox race;

    @FXML
    private ComboBox color;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        race.setItems(
            FXCollections.observableArrayList(
                "Packer",
                "Spheroid",
                "Humanoid",
                "Leggite",
                "Flapper",
                "Bonzoid",
                "Mechtron",
                "Gollumer"));
        color.setItems(
            FXCollections.observableArrayList(
                "red",
                "orange",
                "yellow",
                "green",
                "blue",
                "purple"));
    }
}
