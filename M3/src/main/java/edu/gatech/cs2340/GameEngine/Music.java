package edu.gatech.cs2340.GameEngine;

import sun.audio.*;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;

/**
 * Created by Nick on 12/1/2015.
 */
public class Music extends Thread {

    public void playSong() {
        try {
            System.out.println(System.getProperty("user.dir"));
            URL file = getClass().getResource("/resources/music.wav");
            AudioInputStream aIS = AudioSystem.getAudioInputStream(file);
            AudioFormat af = aIS.getFormat();
            Clip clip = AudioSystem.getClip();
            DataLine.Info info = new DataLine.Info(Clip.class, af);

            Line line = AudioSystem.getLine(info);

            if (! line.isOpen()) {
                clip.open(aIS);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (UnsupportedAudioFileException uafe) {
            System.out.println("unsupported audio file");
        } catch(LineUnavailableException lue) {
            System.out.println("Line Unavailable");
        }
    }
}
