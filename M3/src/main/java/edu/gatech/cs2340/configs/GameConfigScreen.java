package edu.gatech.cs2340.configs;
// import java.awt.*;
// import java.net.URL;
// import java.util.ResourceBundle;
// import java.util.logging.Level;
// import java.util.logging.Logger;
import javafx.application.Application;
// import javafx.collections.FXCollections;
// import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
// import javafx.scene.control.ComboBox;
// import javafx.fxml.Initializable;
// import javafx.scene.control.ChoiceBox;
// import javafx.scene.control.ComboBox;
// import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//Nick and Marc

public class GameConfigScreen extends Application {

    public static void main(String[] args) {
        Application.launch(GameConfigScreen.class, (java.lang.String[]) null);
    }


    @Override
    public void start(Stage stage) throws Exception {
        Parent root =
                FXMLLoader.load(getClass().getResource("GameConfig.fxml"));
        Scene scene = new Scene(root, 1600, 900);

        stage.setTitle("Rare Pepe");
        stage.setScene(scene);
        stage.show();
    }


}