package me.pokerman99.serversuite.Windows.staffTrackerWindow;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import me.pokerman99.serversuite.Main;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class staffTrackerWindow {

    //Load the window from file objects are not loaded
    public void start() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("staffTracker.fxml"));

        Rectangle2D screen = Screen.getPrimary().getVisualBounds();

        Main.stafftrackerWindow = new Stage();


        Main.stafftrackerWindow.setTitle("ServerSuite - Home");
        Main.stafftrackerWindow.setScene(new Scene(root));
        Main.stafftrackerWindow.setResizable(false);
        Main.stafftrackerWindow.show();
    }


    //Fired once the window loads AND all the objects are loaded too
    public static void staffTrackerTab(Tab staffTrackerTab, TextArea staffTrackerTextArea, Button submitButton, ChoiceBox<String> staffTrackerServerChoiceBox, ChoiceBox<String> staffTrackerDateRangeLowerChoiceBox, ChoiceBox<String> staffTrackerDateRangeHigherChoiceBox) {

        //Set defaults for choiceboxes
        {
            staffTrackerServerChoiceBox.setValue("SELECT");
            staffTrackerServerChoiceBox.getItems().addAll("PD", "PV", "PL", "PC");

            staffTrackerDateRangeLowerChoiceBox.setValue("SELECT");

            staffTrackerDateRangeHigherChoiceBox.getItems().add("NOW");
            staffTrackerDateRangeHigherChoiceBox.setValue("NOW");
        }

    }

    //Fired once someone selects a server
    public static void serverSelected(ChoiceBox<String> staffTrackerServerChoiceBox, ChoiceBox<String> staffTrackerDateRangeLowerChoiceBox, ChoiceBox<String> staffTrackerDateRangeHigherChoiceBox) {
        String server = staffTrackerServerChoiceBox.getValue();

        System.out.print(staffTrackerServerChoiceBox.getValue());

        //Clear all items in choice box so no change of wrong data
        {
            if (staffTrackerServerChoiceBox.getValue().equals("SELECT")) return;
            staffTrackerDateRangeLowerChoiceBox.getItems().clear();
            staffTrackerDateRangeHigherChoiceBox.getItems().clear();
        }

        //Execute sql statement to grab new values and fill the choices
        {
            try {
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(getDates(server.toLowerCase()));

                List<String> dates = new ArrayList<>();

                while (resultSet.next()) {
                    dates.add(resultSet.getString(1));

                }
                Collections.reverse(dates);

                staffTrackerDateRangeLowerChoiceBox.getItems().addAll(dates);

                staffTrackerDateRangeHigherChoiceBox.getItems().add("NOW");
                staffTrackerDateRangeHigherChoiceBox.getItems().addAll(dates);

                staffTrackerDateRangeHigherChoiceBox.setValue("NOW");
                staffTrackerDateRangeLowerChoiceBox.setValue(staffTrackerDateRangeLowerChoiceBox.getItems().get(0));


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void submitButtonClicked(ChoiceBox<String> staffTrackerServerChoiceBox, ChoiceBox<String> staffTrackerDateRangeLowerChoiceBox, ChoiceBox<String> staffTrackerDateRangeHigherChoiceBox, TextArea staffTrackerTextArea) {
        String selectedServer = staffTrackerServerChoiceBox.getValue().toLowerCase();
        String selectedTimeLower = staffTrackerDateRangeLowerChoiceBox.getValue();
        String selectedTimeHigher = staffTrackerDateRangeHigherChoiceBox.getValue();


        if (selectedTimeHigher.equals("NOW")) {
            selectedTimeHigher = staffTrackerDateRangeLowerChoiceBox.getItems().get(0);
        }

        System.out.println(selectedTimeLower);
        System.out.println(selectedTimeHigher);

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getInfo(selectedServer, selectedTimeLower, selectedTimeHigher));

            //Remove all old text to new stuff doesn't over write it.
            staffTrackerTextArea.clear();

            while (resultSet.next()) {
                staffTrackerTextArea.appendText(resultSet.getString(3) + " - " + resultSet.getString(4) + " - " + resultSet.getString(5) + "\n\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String getInfo(String server, String dateLower, String dateHigher) {
        String SQL = "SELECT * FROM " + server + " WHERE date BETWEEN '" + dateLower + "' and '" + dateHigher + "';";
        return SQL;
    }

    public static String getDates(String server) {
        String SQL = "SELECT date FROM " + server + " WHERE uuid='3fdc6561-8e09-4a09-8f6b-fff5cb8710c8';";
        return SQL;
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


    public static void passAllObjects(AnchorPane anchorPane, TabPane tabPane, Tab tab1, Tab staffTrackerTab, Tab tab3, Tab tab4, CheckBox staffTrackerChoiceBox) {


    }

}
