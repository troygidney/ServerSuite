package me.pokerman99.serversuite.Windows.staffTrackerWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import me.pokerman99.serversuite.Main;
import me.pokerman99.serversuite.Objects.AlertBox.closeBoxWindow;
import org.joda.time.DateTime;

import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;


public class staffTrackerWindow {

    public static int amountOfStaff;
    public static List<String> namesOfStaff;

    //Load the window from file objects are not loaded
    public void start() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("staffTracker.fxml"));

        {
            //Making the stage - important
            Main.stafftrackerWindow = new Stage();

            Main.stafftrackerWindow.setOnCloseRequest(event -> { //Setting the X button event action
                event.consume(); //Consume the actual event of closing so we can handle it
                new closeBoxWindow().start(); //Start the close bot options
            });

            Main.stafftrackerWindow.setTitle("ServerSuite - Home"); //Set the window title to this
            Main.stafftrackerWindow.setScene(new Scene(root)); //Setting the window scene
            Main.stafftrackerWindow.setMaximized(true); //Set the screen to max

        }
        //Display the window
        Main.stafftrackerWindow.show();
    }
    //staffTrackerNameSlot1


    //Fired once the window loads AND all the objects are loaded too
    public static void loadDefaults(Tab staffTrackerTab, TableView<addItemsToColumn> staffTrackerTableView, Button submitButton, ChoiceBox<String> staffTrackerServerChoiceBox, ChoiceBox<String> staffTrackerDateRangeLowerChoiceBox, ChoiceBox<String> staffTrackerDateRangeHigherChoiceBox) {

        //Set defaults for choiceboxes
        {
            staffTrackerServerChoiceBox.setValue("SELECT");
            staffTrackerServerChoiceBox.getItems().addAll("PD", "PV", "PL", "PC", "PB");

            staffTrackerDateRangeLowerChoiceBox.setValue("SELECT");

            staffTrackerDateRangeHigherChoiceBox.getItems().add("NOW");
            staffTrackerDateRangeHigherChoiceBox.setValue("NOW");

            staffTrackerTableView.setTableMenuButtonVisible(true);
        }

    }

    //Fired once someone selects a server
    public static void serverSelected(ChoiceBox<String> staffTrackerServerChoiceBox, ChoiceBox<String> staffTrackerDateRangeLowerChoiceBox, ChoiceBox<String> staffTrackerDateRangeHigherChoiceBox) {
        String server = staffTrackerServerChoiceBox.getValue().toLowerCase();

        //Clear all items in choice box so no chance of wrong data
        {
            if (staffTrackerServerChoiceBox.getValue().equals("SELECT")) return;
            staffTrackerDateRangeLowerChoiceBox.getItems().clear();
            staffTrackerDateRangeHigherChoiceBox.getItems().clear();
        }


        //Execute sql statement to grab new values and fill the choices with the avaliable dates
        {

                /*Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(getDates(server.toLowerCase()));

                List<String> dates = new ArrayList<>();

                //Fill in all the dates
                while (resultSet.next()) {
                    dates.add(resultSet.getString(1));

                }
                Collections.reverse(dates);
*/
                {
                    //Adding dates to choiceboxes
                    //staffTrackerDateRangeLowerChoiceBox.getItems().addAll(dates);

                    staffTrackerDateRangeHigherChoiceBox.getItems().add("NOW");
                    staffTrackerDateRangeHigherChoiceBox.getItems().add("1 Week");
                    staffTrackerDateRangeHigherChoiceBox.getItems().add("2 Weeks");
                    staffTrackerDateRangeHigherChoiceBox.getItems().add("3 Weeks");
                    staffTrackerDateRangeHigherChoiceBox.getItems().add("1 Month");
                    staffTrackerDateRangeHigherChoiceBox.getItems().add("2 Months");
                    staffTrackerDateRangeHigherChoiceBox.getItems().add("3 Months");
                    staffTrackerDateRangeHigherChoiceBox.getItems().add("6 Months");

                    //staffTrackerDateRangeHigherChoiceBox.getItems().addAll(dates);

                    staffTrackerDateRangeHigherChoiceBox.setValue("NOW");

                    staffTrackerDateRangeLowerChoiceBox.getItems().add("NOW");


                    //staffTrackerDateRangeHigherChoiceBox.getItems().addAll(dates);

                    staffTrackerDateRangeLowerChoiceBox.setValue("NOW");
//                    staffTrackerDateRangeLowerChoiceBox.setValue(staffTrackerDateRangeLowerChoiceBox.getItems().get(0));

                }

                //Close the connection
               // resultSet.close();



        }


        {
            try {
                //Made a new connection because this section cannot see the OG connection
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(getStaffNames(server));

                namesOfStaff = new ArrayList<>();

                while (resultSet.next()) {
                    if (!namesOfStaff.contains(resultSet.getString(1))) {
                        namesOfStaff.add(resultSet.getString(1));
                    }
                }

                //TODO Find out what to use this for if nothing remove
                amountOfStaff = namesOfStaff.size();

                //Close the connection
                resultSet.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void submitButtonClicked(ChoiceBox<String> staffTrackerServerChoiceBox, ChoiceBox<String> staffTrackerDateRangeLowerChoiceBox, ChoiceBox<String> staffTrackerDateRangeHigherChoiceBox, TableView<addItemsToColumn> staffTrackerTableView) {
        String selectedServer = staffTrackerServerChoiceBox.getValue().toLowerCase();
        String selectedTimeLower = staffTrackerDateRangeLowerChoiceBox.getValue();
        String selectedTimeHigher = staffTrackerDateRangeHigherChoiceBox.getValue();

        //Setting the fuctions if not valid or need to be changed
        {
            if (selectedServer.equals("select")) return;
        }


        try {
            //remove old stuff so no change of over lapping
            staffTrackerTableView.getColumns().clear();
            staffTrackerTableView.getItems().clear();

            {
                Connection connection = getConnection();
                Statement statement = connection.createStatement();

                //Store the name and the playtime data
                Map<String, String> map = new HashMap<>();

                //Getting the name and playtime data
                ResultSet resultSet = statement.executeQuery("SELECT date FROM " + selectedServer + " ORDER BY id DESC LIMIT 0,1;");
                Date now = null;
                Date calDate = new Date(Calendar.getInstance().getTimeInMillis());
                Calendar cal = null;

                while(resultSet.next()) {
                    now = resultSet.getDate("date");
                    cal = Calendar.getInstance();
                    cal.setTime(now);
                }

                resultSet.close();


                switch (selectedTimeHigher) {
                    case "1 Week": {
                        cal.add(Calendar.DAY_OF_WEEK, -7);
                        break;
                    }
                    case "2 Weeks": {
                        cal.add(Calendar.DAY_OF_WEEK, -14);
                        break;
                    }
                    case "3 Weeks": {
                        cal.add(Calendar.DAY_OF_WEEK, -21);
                        break;
                    }
                    case "1 Month": {
                        cal.add(Calendar.MONTH, -1);
                        break;
                    }
                    case "2 Months": {
                        cal.add(Calendar.MONTH, -2);
                        break;
                    }
                    case "3 Months": {
                        cal.add(Calendar.MONTH, -3);
                        break;
                    }
                    case "6 Months": {
                        cal.add(Calendar.MONTH, -6);
                        break;
                    }
                    case "NOW":
                }

                calDate.setTime(cal.getTimeInMillis());


                {
                    for (String staffName : namesOfStaff) {


                        //System.out.println(calDate);
                        //System.out.println(now);

                        ResultSet resultSetBefore = statement.executeQuery("SELECT DISTINCT name, date, (playtime)time FROM "+ selectedServer +" WHERE DATE LIKE '"+ calDate.toString() +"%' AND name='"+ staffName +"';");


                        int playtimeBefore = 0;
                        //String name;
                        DateTime nowDate;

                        while (resultSetBefore.next()) {
                            //playtimeBefore = resultSetBefore.getInt(3);
                            playtimeBefore = resultSetBefore.getInt("time");
                        }

                        resultSetBefore.close();
                        ResultSet resultSetNow = statement.executeQuery("SELECT DISTINCT name, date, playtime FROM "+ selectedServer +" WHERE date LIKE '"+ now +"%'AND name='"+ staffName +"';");

                        int playtimeNow = 0;
                        while (resultSetNow.next()) {
                            playtimeNow = resultSetNow.getInt(3);
                        }

                        resultSetNow.close();
                        System.out.println(playtimeBefore - playtimeNow);
                        if ((playtimeBefore - playtimeNow) == 0) {
                            continue;
                        }



                        map.put(staffName, String.valueOf(playtimeNow - playtimeBefore));
                    }
                }

                //Close the connection to the database as we no longer need it
                connection.close();

                //Make the columns and add data to them. This was a bitch
                TableColumn<addItemsToColumn, String> namesColumn = new TableColumn<>("Names");
                namesColumn.setMinWidth(110);
                namesColumn.setCellValueFactory(new PropertyValueFactory<>("names"));

                TableColumn<addItemsToColumn, String> playtimeColumn = new TableColumn<>("PlayTime");
                playtimeColumn.setMinWidth(110);
                playtimeColumn.setCellValueFactory(new PropertyValueFactory<>("playtime"));

                //Set the items. Idk how this works lmao
                staffTrackerTableView.setItems(addRows(map));

                //Adding the columns to the tableview
                staffTrackerTableView.getColumns().addAll(namesColumn, playtimeColumn);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Utility functions below so there wasn't a lot of clutter but they only apply to the staff tracker (atm)


    public static ObservableList<addItemsToColumn> addRows(Map<String, String> map) {
        ObservableList<addItemsToColumn> observableList = FXCollections.observableArrayList();
        map.forEach((s, s2) -> {
            observableList.add(new addItemsToColumn(s, s2));
        });
        return observableList;
    }

    public static String getStaffNames(String server) {
        String SQL = "SELECT name FROM " + server + ";";
        return SQL;
    }


    public static String getDates(String server) {
        String SQL = "SELECT date FROM " + server + " WHERE uuid='5b03f120-12ac-440a-8966-b215408b7c02';";
        return SQL;
    }

    public static String getPlayTimeLowerTime(String server, String name, String dateLower) {
        String SQL = "SELECT playtime FROM " + server + " WHERE name='" + name + "' and date='" + dateLower + "';";
        return SQL;
    }

    public static String getPlayTimeHigherTime(String server, String name, String dateHigher) {
        String SQL = "SELECT playtime FROM " + server + " WHERE name='" + name + "' and date='" + dateHigher + "';";
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

}
