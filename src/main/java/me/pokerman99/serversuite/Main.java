package me.pokerman99.serversuite;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import me.pokerman99.serversuite.Objects.closeProgramObject;
import me.pokerman99.serversuite.Windows.staffTrackerWindow;

public class Main extends Application {

    private String host = "104.236.10.120";
    private String port = "3306";
    private String db = "staff_tracker";
    private String user = "staff_tracker";
    private String pass = "hDOJQed032";


    public static Stage loginWindow;
    public static Stage stafftrackerWindow;

    public static Scene loginScene;
    public static Scene staffTrackerScene;

    //TODO Make it so when you login it closes that window to create a new one. Closes stage not just changing scene

    public static void main(String[] args) throws Exception {
        launch(args);
    }


    //TODO Make this actually work so it's secure atm it's just TEMP


    //Main login event that I want called.
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Set this so it can be closed from anywhere in the program.
        Main.loginWindow = primaryStage;
        //Set the title of the app
        Main.loginWindow.setTitle("ServerSuite");
        //Don't allow people to resize it
        Main.loginWindow.setResizable(false);

        Label nameLabel = new Label();
        Label passLabel = new Label();
        TextField nameInput = new TextField();
        TextField passInputVisable = new TextField();
        PasswordField passInput = new PasswordField();
        Button loginButton = new Button();
        Button closeButton = new Button();
        CheckBox checkBox = new CheckBox();

        //Set the gridpane properties
        GridPane gridPane = new GridPane();
        {
            gridPane.setPadding(new Insets(10, 10, 10, 10));
            gridPane.setVgap(8);
            gridPane.setHgap(8);
            gridPane.getChildren().addAll(nameLabel, passLabel, nameInput, passInput, loginButton, closeButton, checkBox);
        }

        //Give the elements properties
        {
            nameLabel.setText("Username: ");
            passLabel.setText("Password: ");

            loginButton.setText("Login");
            closeButton.setText("Close");

            checkBox.setText("Show password?");

            passInputVisable.setPromptText("password");
            passInput.setPromptText("password");
            checkBox.selectedProperty();
        }

        {
            closeButton.setOnAction(event -> this.closeButtonEvent(event));
            loginButton.setOnAction(event -> this.loginButtonEvent(event, nameInput.getText(), passInput.getText(), passInputVisable.getText()));

            checkBox.setOnAction(event -> this.checkboxEvent(event, gridPane, checkBox, passInput, passInputVisable));

            primaryStage.setOnCloseRequest(event -> this.onCloseRequest(event));
        }

        //Position the objects
        {
            GridPane.setConstraints(nameLabel, 0, 0);
            GridPane.setConstraints(nameInput, 1, 0);
            GridPane.setConstraints(passLabel, 0, 1);
            GridPane.setConstraints(passInput, 1, 1);
            GridPane.setConstraints(loginButton, 3, 0);
            GridPane.setConstraints(closeButton, 3, 1);
            GridPane.setConstraints(checkBox, 1, 3);
        }

        //Make the scene
        Main.loginScene = new Scene(gridPane, 300, 110);
        //Set the window to the scene
        Main.loginWindow.setScene(Main.loginScene);


        //Display the window
        Main.loginWindow.show();


    }

    void closeButtonEvent(ActionEvent event) {
        closeProgramObject.closeProgramNoPrompot(Main.loginWindow);
    }

    void loginButtonEvent(ActionEvent event, String username, String password, String visablePassword) {
        if (username.equals("pokerman99") && (password.equals("password") || visablePassword.equals("password"))) {
            try {
                new staffTrackerWindow().start();
                Main.loginWindow.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    void checkboxEvent(ActionEvent event, GridPane gridPane, CheckBox checkBox, PasswordField passInput, TextField passInputVisable) {
        if (checkBox.isSelected()) {
            gridPane.getChildren().remove(passInput);
            passInputVisable.setText(passInput.getText());
            gridPane.getChildren().add(passInputVisable);
            GridPane.setConstraints(passInputVisable, 1, 1);
        } else {
            gridPane.getChildren().remove(passInputVisable);
            passInput.setText(passInputVisable.getText());
            gridPane.getChildren().add(passInput);
            GridPane.setConstraints(passInput, 1, 1);
        }
    }

    void onCloseRequest(WindowEvent event) {
        event.consume();
        closeProgramObject.closeProgramNoPrompot(Main.loginWindow);
    }

}
