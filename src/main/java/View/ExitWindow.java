package View;

import Controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ExitWindow {

    public ExitWindow() {
    }
    public static void display(Controller control) {
        Stage exitWindow = new Stage();
        exitWindow.initModality(Modality.APPLICATION_MODAL);
        exitWindow.setTitle("Save Before Closing");
        exitWindow.setMinWidth(350.0D);
        exitWindow.setMinHeight(125.0D);
        Label label = new Label("Would you like to save before exiting?");
        Button exitSaveButton = new Button("Save");
        exitSaveButton.setOnAction((e) -> {

            List saveList = control.createObservableListAll();
            try {
                control.serialisedSave(saveList);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            System.exit(0);

        });
        Button noSaveButton = new Button("Don't Save");
        noSaveButton.setOnAction((e) -> {
            System.exit(0);
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction((e) -> {
            exitWindow.close();
        });
        HBox layout = new HBox(10.0D);
        layout.setPadding(new Insets(10.0D));
        layout.getChildren().addAll(new Node[]{label});
        layout.setAlignment(Pos.CENTER);
        HBox buttonLayout = new HBox(10.0D);
        buttonLayout.setPadding(new Insets(10.0D));
        buttonLayout.setAlignment(Pos.BASELINE_CENTER);
        buttonLayout.getChildren().addAll(new Node[]{exitSaveButton, noSaveButton, cancelButton});
        VBox root = new VBox();
        root.getChildren().addAll(new Node[]{layout, buttonLayout});
        Scene scene = new Scene(root);
        exitWindow.setScene(scene);
        exitWindow.showAndWait();
    }
}



