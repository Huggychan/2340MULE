package edu.gatech.cs2340.Maps;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

/**
 * Created by Nick on 9/28/2015.
 */
public class EventLog extends Label {
    private String s = "";

    /**
     * Constructor for EventLog
     */
    public EventLog() {
        super();
        setTextFill(Paint.valueOf("white"));
        setFont(javafx.scene.text.Font.font(24));
        setAlignment(Pos.TOP_CENTER);
    }

    /**
     * Logs string to logger
     * @param s String to be logged
     */
    public void log(String s) {
        this.s = s;
        setText(s);
    }
}
