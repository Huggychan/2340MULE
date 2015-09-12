package edu.gatech.cs2340.configs;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
// import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Person Configuration Screen
 * @author Bilal, Myron, Shyam
 * @version 1.0
 */
public class PersonConfigScreen extends Application {

    /**
     * Main method that utilizes PersonConfigScreen
     * @param args Arguments to command line
     */
    public static void main(String[] args) {
        Application.launch(PersonConfigScreen.class, (java.lang.String[]) null);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root =
            FXMLLoader.load(getClass().getResource("../../../../../resources/PersonConfigScreen.fxml"));

        Scene scene = new Scene(root, 1600, 900);

        stage.setTitle("Person Configuration Screen");
        stage.setScene(scene);
        stage.show();
    }
}