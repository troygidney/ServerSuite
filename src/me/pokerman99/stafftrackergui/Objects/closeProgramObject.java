package me.pokerman99.stafftrackergui.Objects;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class closeProgramObject {


    //Essentialy a save file area onClose

    public static void closeProgram(Stage window) {
        Boolean result = confirmBoxObject.dispay("Are you sure?", "Do you want to exit with out saving?");
        if (result) {
            window.close();
        }
    }

    //For when people use the provided button from windows. Default Close
    public static void closeProgram(Stage window, WindowEvent e) {
        Boolean result = confirmBoxObject.dispay("Are you sure?", "Do you want to exit with out saving?");
        e.consume();
        if (result) {
            window.close();
        }
    }

}
