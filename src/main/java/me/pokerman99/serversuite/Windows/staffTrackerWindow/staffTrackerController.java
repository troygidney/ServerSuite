package me.pokerman99.serversuite.Windows.staffTrackerWindow;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import me.pokerman99.serversuite.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class staffTrackerController implements Initializable {

    public AnchorPane AnchorPane;

    public TabPane tabPane;

    public Tab tabMenu1;
    public Tab tabMenu2;
    public TextArea staffTrackerTextArea;
    public Button submitButton;
    public ChoiceBox<String> staffTrackerServerChoiceBox;
    public ChoiceBox<String> staffTrackerDateRangeLowerChoiceBox;
    public ChoiceBox<String> staffTrackerDateRangeHigherChoiceBox;

    public Tab tabMenu3;
    public Tab tabMenu4;

    //This is fired when the window starts so I can execute anything that needs to be added via code
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //staffTrackerWindow.passAllObjects(AnchorPane, tabPane, tabMenu1, tabMenu2, tabMenu3 ,tabMenu4, staffTrackerChoiceBox);
        staffTrackerWindow.staffTrackerTab(tabMenu2, staffTrackerTextArea, submitButton, staffTrackerServerChoiceBox, staffTrackerDateRangeLowerChoiceBox, staffTrackerDateRangeHigherChoiceBox);
    }

    //Do this so I can pass the event to a diff class
    public void serverSelected() {
        staffTrackerWindow.serverSelected(staffTrackerServerChoiceBox, staffTrackerDateRangeLowerChoiceBox, staffTrackerDateRangeHigherChoiceBox);
    }

    //Do this so I can pass the event to a diff class
    public void submitButtonClicked() {
        staffTrackerWindow.submitButtonClicked(staffTrackerServerChoiceBox, staffTrackerDateRangeLowerChoiceBox, staffTrackerDateRangeHigherChoiceBox, staffTrackerTextArea);
    }


    public void onTabViewMenu1() {
        try {
            Main.stafftrackerWindow.setTitle("ServerSuite - Home");
        } catch (NullPointerException e) {
            //This throws an error when it's loaded for the first time. not sure why but it's the only instance it happens.
        }
    }

    public void onTabViewMenu2() {

        Main.stafftrackerWindow.setTitle("ServerSuite - Staff Tracker");


    }

    public void onTabViewMenu3() {

        Main.stafftrackerWindow.setTitle("ServerSuite - Staff Tracker12345");


    }

    public void onTabViewMenu4() {

        Main.stafftrackerWindow.setTitle("ServerSuite - Staff Tracker12346");


    }
}
