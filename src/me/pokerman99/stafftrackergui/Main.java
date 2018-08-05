package me.pokerman99.stafftrackergui;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import me.pokerman99.stafftrackergui.Objects.alertBoxObject;
import me.pokerman99.stafftrackergui.Objects.closeProgramObject;

public class Main {

    private String host = "104.236.10.120";
    private String port = "3306";
    private String db = "staff_tracker";
    private String user = "staff_tracker";
    private String pass = "hDOJQed032";


    public static Stage window;

    public static Scene loginScene;
    public static Scene staffTrackerScene;

    //TODO Make it so when you login it closes that window to create a new one. Closes stage not just changing scene

    public static void main(String[] args) throws Exception{
        loginPopup.launch(args);
    }

    public static void setStaffTrackerScene(boolean allowed) {
        if (allowed == false) {
            alertBoxObject.display("Error!", "Incorrect password or username!");
            return;
        }

        GridPane gridPane = new GridPane();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        staffTrackerScene = new Scene(gridPane);

        window.setOnCloseRequest(e -> {
            closeProgramObject.closeProgram(window, e, "Are you sure?", "Do you want to close?");
        });

        window.setScene(staffTrackerScene);
        window.setResizable(true);
        window.setMaximized(true);
    }

}
