package edu.gatech.cs2340;

import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

/**
 * @author Bilal
 * @version 1.0
 */
public class SerializableUtil {

    /**
     * Saves the game.
     * @param game Game to be saved
     */
    public void saveGame(Game game) {
        try {
            System.out.println(System.getProperty("user.dir"));
            File file = new File("src/main/java/saves/game.ser");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ioe) {
                    System.out.println("file DNE");
                } catch (SecurityException se) {
                    System.out.println("You've been put down by the system");
                }
            }
            FileOutputStream fileOut =
                    new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in "
                    + "src/main/java/saves/game.ser\n");
        } catch (IOException i)  {
            System.out.println(i.getMessage());
        }
    }

    /**
     * Loads the game.
     * @param fileName File to be loaded
     * @param stage Stage of the game
     * @throws IOException IOException for file
     * @throws ClassNotFoundException ClassNotFoundException for class not found
     */
    public void loadGame(File fileName, Stage stage) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Game obj = (Game) ois.readObject();
        ois.close();
        obj.loadGame(stage);
    }
}
