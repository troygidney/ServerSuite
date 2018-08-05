package me.pokerman99.stafftrackergui.Objects;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class closeProgramObject {


    //Essentialy a save file area onClose

    public static void closeProgram(Stage window, String title, String message) {
        Boolean result = confirmBoxObject.dispay(title, message);
        if (result) {
            window.close();
        }
    }

    //For when people use the provided button from windows. Default Close
    public static void closeProgram(Stage window, WindowEvent e, String title, String message) {
        Boolean result = confirmBoxObject.dispay(title, message);
        e.consume();
        if (result) {
            window.close();
        }
    }

    public static void closeProgramNoPrompot(Stage window) {
            window.close();
    }


}
