package me.pokerman99.serversuite;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import me.pokerman99.serversuite.Objects.closeProgramObject;
import me.pokerman99.serversuite.Windows.staffTrackerWindow.staffTrackerWindow;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

public class Main extends Application {


    public static Stage loginWindow;
    public static Stage stafftrackerWindow;

    public static Scene loginScene;
    public static Scene staffTrackerScene;

    public static void main(String[] args) throws Exception {
        launch(args);
    }


    //TODO Make it more secure atm it works for closed testing


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
            gridPane.setPadding(new Insets(7, 5, 0, 10));
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

        Rectangle2D screen = Screen.getPrimary().getVisualBounds();

        //Make the scene
        //Main.loginScene = new Scene(gridPane, 300, 110);
        Main.loginScene = new Scene(gridPane);
        Main.loginWindow.setX(screen.getWidth() - Main.loginWindow.getWidth() / 2);
        Main.loginWindow.setY(screen.getHeight() - Main.loginWindow.getHeight() / 4);
        //Set the window to the scene
        Main.loginWindow.setScene(Main.loginScene);


        //Display the window
        Main.loginWindow.show();


    }

    void closeButtonEvent(ActionEvent event) {
        closeProgramObject.closeProgramNoPrompot(Main.loginWindow);
    }

    void loginButtonEvent(ActionEvent event, String username, String password, String visablePassword) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getUserAccount(username));

            String DBP = null;

            while (resultSet.next()) {
                DBP = resultSet.getString(3);
            }

            if (DigestUtils.sha256Hex(password).equals(DBP)) {
                try {
                    new staffTrackerWindow().start();
                    Main.loginWindow.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException | NullPointerException e) {
            //alertBox.display("Error!", "Cannot connect to database to authorize login");
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

    public String getUserAccount(String username) {
        String SQL = "SELECT * FROM users WHERE username='" + username + "';";
        return SQL;
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://8.26.94.197:3306/ServerSuite", "ServerSuite", "C4O2ULec12CC86Nv");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            try {
                //new closeBoxWindow().start();
                System.out.println("yyet");
                e.printStackTrace();

            } catch (Exception e1) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
