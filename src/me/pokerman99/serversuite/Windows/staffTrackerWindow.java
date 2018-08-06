package me.pokerman99.serversuite.Windows;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import me.pokerman99.serversuite.Main;
import me.pokerman99.serversuite.Objects.closeProgramObject;


public class staffTrackerWindow {

    public void start() throws Exception {
        Main.stafftrackerWindow = new Stage();
        Main.stafftrackerWindow.setTitle("ServerSuite - StaffTracker");

        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox();

        Button button = new Button("Okay!");

        {
            borderPane.setLeft(vBox);
            vBox.setAlignment(Pos.CENTER);
            vBox.autosize();
            vBox.getChildren().add(button);
        }

        {
            Main.stafftrackerWindow.setOnCloseRequest(event -> this.closeWindowEvent(Main.stafftrackerWindow, event, "Are you sure?", "Are you sure you want to close?"));
        }

        Main.staffTrackerScene = new Scene(borderPane);
        Main.stafftrackerWindow.setMaximized(true);
        Main.stafftrackerWindow.show();

    }

    void closeWindowEvent(Stage window, WindowEvent event, String title, String message) {
        event.consume();
        closeProgramObject.closeProgramWithPrompt(window, event, title, message);
    }

}
