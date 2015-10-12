package edu.gatech.cs2340.configs;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Player Configuration Screen
 * @author Bilal, Myron, Shyam
 * @version 1.0
 */
public class SplashScreen extends Application {

    /**
     * Main method that utilizes PlayerConfigScreen
     * @param args Arguments to command line
     */
    public static void main(String[] args) {
        Application.launch(SplashScreen.class, (java.lang.String[]) null);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/resources/SplashScreen.fxml"));
        loader.setClassLoader(this.getClass().getClassLoader());

        Parent newRoot = null;

        try {
            newRoot = loader.load();
            newRoot.toFront();
        } catch (IOException e) {
            System.out.println("IOException loading SplashScreen.fxml");
            System.out.println(e.getMessage());
        }

        Scene scene = new Scene(newRoot, 1600, 900);

        stage.setTitle("Splash Screen!!");
        stage.setScene(scene);
        stage.show();
    }
}