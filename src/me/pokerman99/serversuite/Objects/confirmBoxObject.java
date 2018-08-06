package me.pokerman99.serversuite.Objects;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class confirmBoxObject {

    static boolean option;

    public static boolean dispay(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(325);
        window.setMinHeight(75);

        Label label =  new Label();
        label.setText(message);

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            option = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            option = false;
            window.close();
        });


        HBox layout = new HBox(12);
        layout.getChildren().addAll(label, noButton, yesButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return option;
    }

}
