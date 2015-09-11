import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PersonConfigController implements Initializable {

    @FXML
    private ComboBox race;
    @FXML
    private ComboBox color;
    @FXML
    private Button start;
    @FXML
    private TextField name;
    @FXML
    private Label welcome;

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
                "Red",
                "Orange",
                "Yellow",
                "Green",
                "Blue",
                "Purple"));
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                welcome.setText("Welcome " + color.getValue() + " "
                    + race.getValue() + " named " + name.getCharacters() + "!");
            }
        });
    }
}
