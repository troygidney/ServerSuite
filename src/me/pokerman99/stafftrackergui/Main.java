package me.pokerman99.stafftrackergui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import me.pokerman99.stafftrackergui.Listeners.buttonClickAction;
import me.pokerman99.stafftrackergui.Objects.alertBoxObject;
import me.pokerman99.stafftrackergui.Objects.closeProgramObject;
import me.pokerman99.stafftrackergui.Objects.confirmBoxObject;

public class Main extends Application {

    private String host = "104.236.10.120";
    private String port = "3306";
    private String db = "staff_tracker";
    private String user = "staff_tracker";
    private String pass = "hDOJQed032";


    public Stage window;
    public Scene scene;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Staff Tracker GUI");
        //Windows close X button event
        window.setOnCloseRequest(e -> {
            closeProgramObject.closeProgram(window, e);
        });

        HBox topMenu = new HBox();
        Button buttona = new Button("File");
        Button buttonb = new Button("Edit");
        Button buttonc = new Button("View");

        topMenu.getChildren().addAll(buttonc, buttona, buttonb);

        VBox leftMenu = new VBox();
        Button buttond = new Button("d");
        Button buttone = new Button("e");
        Button buttonf = new Button("f");

        leftMenu.getChildren().addAll(buttond, buttone, buttonf);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setLeft(leftMenu);




        scene = new Scene(borderPane, 300, 250);
        window.setScene(scene);
        window.show();


    }

}
