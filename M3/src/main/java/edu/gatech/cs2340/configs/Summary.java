package edu.gatech.cs2340.configs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Bilal
 * @version 1.0
 */
public class Summary extends Application {
    /**
     * Main method that utilizes PlayerConfigScreen
     * @param args Arguments to command line
     */
    public static void main(String[] args) {
        Application.launch(Summary.class, (java.lang.String[]) null);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root =
                FXMLLoader.load(getClass().getResource("Summary.fxml"));

        Scene scene = new Scene(root, 1600, 900);

        stage.setTitle("Summary Screen");
        stage.setScene(scene);
        stage.show();
    }
}
