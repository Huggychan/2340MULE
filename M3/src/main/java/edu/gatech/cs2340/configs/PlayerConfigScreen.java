package edu.gatech.cs2340.configs;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
// import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Player Configuration Screen
 * @author Bilal, Myron, Shyam
 * @version 1.0
 */
public class PlayerConfigScreen extends Application {

    /**
     * Main method that utilizes PlayerConfigScreen
     * @param args Arguments to command line
     */
    public static void main(String[] args) {
        Application.launch(PlayerConfigScreen.class, (java.lang.String[]) null);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root =
            FXMLLoader.load(getClass().getResource("PlayerConfigScreen.fxml"));

        Scene scene = new Scene(root, 1600, 900);

        stage.setTitle("Player Configuration Screen");
        stage.setScene(scene);
        stage.show();
    }
}