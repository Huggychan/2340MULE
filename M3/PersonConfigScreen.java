import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
// import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PersonConfigScreen extends Application {

    public static void main(String[] args) {
        Application.launch(PersonConfigScreen.class, (java.lang.String[]) null);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root =
            FXMLLoader.load(getClass().getResource("PersonConfigScreen.fxml"));

        Scene scene = new Scene(root, 1600, 900);

        stage.setTitle("Person Configuration Screen");
        stage.setScene(scene);
        stage.show();
    }
}