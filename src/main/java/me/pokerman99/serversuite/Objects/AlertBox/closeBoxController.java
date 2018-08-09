package me.pokerman99.serversuite.Objects.AlertBox;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class closeBoxController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeBoxWindow.loadSettings(textObject, labelObject, buttonOneObject, buttonTwoObject);
    }

    public Text textObject = new Text();
    public Label labelObject = new Label();
    public Button buttonOneObject = new Button();
    public Button buttonTwoObject = new Button();


}
