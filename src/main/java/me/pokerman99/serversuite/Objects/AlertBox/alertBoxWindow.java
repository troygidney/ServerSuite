package me.pokerman99.serversuite.Objects.AlertBox;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class alertBoxWindow {

    public static Stage alertBoxWindow;

    //This is a jump start essentially
    public void start() throws IOException {

        AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("alertBox.fxml"));

        alertBoxWindow = new Stage();

        alertBoxWindow.setOnCloseRequest(event -> {
            event.consume();
            alertBoxWindow.close();
        });

        alertBoxWindow.setTitle("Test1");
        alertBoxWindow.setScene(new Scene(root));
        alertBoxWindow.setResizable(false);
        alertBoxWindow.show();

    }

    //Gets Called once the scenes controller is called at start up then returning values here.
    public static void loadSettings(Text text, Label label, Button buttonOne, Button buttonTwo) {

        {
            buttonOne.setText("Yes");
            buttonOne.setLayoutX(buttonOne.getLayoutX() + 20);
            buttonOne.setOnAction(event -> {
                alertBoxWindow.close();
                System.exit(0);
            });
        }

        {
            buttonTwo.setText("No");
            buttonTwo.setOnAction(event -> {
                alertBoxWindow.close();
            });
        }

        {
            text.setText("Are you\n  sure?");
        }

        {
            label.setText("Do you want to close?");
        }


    }

}
