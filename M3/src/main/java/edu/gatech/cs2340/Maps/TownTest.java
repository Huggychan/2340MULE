package edu.gatech.cs2340.Maps;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class TownTest extends Application {

    /**
     * Main
     * @param args Arguments passed
     */
    public static void main(String[] args) {
        Application.launch(TownTest.class, (java.lang.String[]) null);
    }


    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(System.getProperty("user.dir"));

        File f = new File("./src/main/resources/TownMap.fxml");
        System.out.println(f.exists());

        URL location = getClass().getResource("/resources/TownMap.fxml");
        //Use this path format to access things in the
        // src/main/java/resources folder
        System.out.println(location);

        FXMLLoader loader = new FXMLLoader(location);
        loader.setClassLoader(this.getClass().getClassLoader());

        Parent root = loader.load();

        Scene scene = new Scene(root, 1600, 900);
        stage.setTitle("MULE");
        stage.setScene(scene);
        stage.show();
    }
}