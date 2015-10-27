package edu.gatech.cs2340;

import javafx.stage.Stage;

import java.io.*;

/**
 * @author Bilal
 * @version 1.0
 */
public class SerializableUtil {

    public void saveGame(Game game) {
        try {
            System.out.println(System.getProperty("user.dir"));
            File file = new File("src/main/java/saves/game.ser");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOut =
                    new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in "
                    + "src/main/java/saves/game.ser\n");
        } catch(IOException i)  {
            i.printStackTrace();
        }
    }

    public void loadGame(File fileName, Stage stage) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Game obj = (Game) ois.readObject();
        ois.close();
//        obj.startRound();
        obj.loadGame(stage);
    }
}
