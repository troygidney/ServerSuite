package me.pokerman99.stafftrackergui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import me.pokerman99.stafftrackergui.Objects.closeProgramObject;

public class loginPopup extends Application {

    public static void launch(String... args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.window = primaryStage;
        Main.window.setTitle("Staff Tracker GUI");
        Main.window.setResizable(false);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(8);

        //Name label
        Label nameLabel = new Label("Username: ");
        GridPane.setConstraints(nameLabel,0,0);

        //Name Input
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        //Name label
        Label passlabel = new Label("Password: ");
        GridPane.setConstraints(passlabel,0,1);

        //Pass Input
        PasswordField passInput = new PasswordField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 1, 1);
        //Pass Toggle
        TextField passTextInput = new TextField();
        passTextInput.setPromptText("password");
        //Check the login make sure it's good.
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = nameInput.getText();
            String password = passInput.getText();
            String passwordText = passTextInput.getText();
            if (username.equals("pokerman99") && (password.equals("password") || passwordText.equals("password"))) {
                Main.setStaffTrackerScene(true);
            } else {
                Main.setStaffTrackerScene(false);
            }
        });
        GridPane.setConstraints(loginButton, 3, 0);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> closeProgramObject.closeProgramNoPrompot(Main.window));
        GridPane.setConstraints(closeButton, 3, 1);

        CheckBox checkBox = new CheckBox("Show password?");
        checkBox.selectedProperty();
        checkBox.setOnAction(event -> {
            if (checkBox.isSelected()) {
                gridPane.getChildren().remove(passInput);

                passTextInput.setText(passInput.getText());
                gridPane.getChildren().add(passTextInput);
                GridPane.setConstraints(passTextInput, 1, 1);

            } else {
                gridPane.getChildren().remove(passTextInput);

                passInput.setText(passTextInput.getText());
                gridPane.getChildren().add(passInput);
                GridPane.setConstraints(passInput ,1, 1);
            }
        });
        GridPane.setConstraints(checkBox, 1, 3);

        //Windows close X button event
        Main.window.setOnCloseRequest(e -> {
            closeProgramObject.closeProgramNoPrompot(Main.window);
        });

        gridPane.getChildren().addAll(nameLabel, nameInput, passlabel, passInput, loginButton, closeButton, checkBox);

        Main.loginScene = new Scene(gridPane, 300, 110);
        Main.window.setScene(Main.loginScene);


        Main.window.show();


    }
}
