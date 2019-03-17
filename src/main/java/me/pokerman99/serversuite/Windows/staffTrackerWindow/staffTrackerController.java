package me.pokerman99.serversuite.Windows.staffTrackerWindow;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import me.pokerman99.serversuite.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class staffTrackerController implements Initializable {

    public AnchorPane AnchorPane;

    public TabPane tabPane;

    public Tab tabMenu1;
    public Tab tabMenu2;
    //public TextArea staffTrackerTextArea;
    public TableView<addItemsToColumn> staffTrackerTableView;
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
        staffTrackerWindow.loadDefaults(tabMenu2, staffTrackerTableView, submitButton, staffTrackerServerChoiceBox, staffTrackerDateRangeLowerChoiceBox, staffTrackerDateRangeHigherChoiceBox);
    }

    //Do this so I can pass the event to a diff class
    public void serverSelected() {
        staffTrackerWindow.serverSelected(staffTrackerServerChoiceBox, staffTrackerDateRangeLowerChoiceBox, staffTrackerDateRangeHigherChoiceBox);
    }

    //Do this so I can pass the event to a diff class
    public void submitButtonClicked() {
        staffTrackerWindow.submitButtonClicked(staffTrackerServerChoiceBox, staffTrackerDateRangeLowerChoiceBox, staffTrackerDateRangeHigherChoiceBox, staffTrackerTableView);
    }

    public void dateHigherSelected() {
    }

    public void dateLowerSelected() {

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

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://104.236.10.120/staff_tracker", "staff_tracker", "hDOJQed032");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
