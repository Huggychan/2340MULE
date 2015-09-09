import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FXMLExample extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root =
            FXMLLoader.load(getClass().getResource("helloWorld.fxml"));

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }
}