// Student Name: Shane Crotty
// Student Number: R00066752

package View;

import Controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TabC extends Tab {

    public TabC(Controller control) {
        setText("Memory Test");

        // Header, Title, Caption
        HBox headerPane = new HBox();
        Label header = new Label("\nMemory Test");
        headerPane.setAlignment(Pos.CENTER);
        headerPane.getChildren().add(header);
        headerPane.setStyle("-fx-font-weight: bold");

        BorderPane borderPane = new BorderPane();
        Label warning = new Label("\nWARNING: This button calls a loop, and will continue to run until the application runs out of memory\n\n\n");
        Button loopButton = new Button("Create Loop of Dummy Objects");
        borderPane.setTop(warning);
        borderPane.setCenter(loopButton);

        loopButton.setOnAction(e -> control.dummyLoop());

        VBox tabRoot = new VBox();
        tabRoot.getChildren().addAll(headerPane, borderPane);

        setContent(tabRoot);

    }
}
