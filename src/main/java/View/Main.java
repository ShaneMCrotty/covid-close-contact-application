// Student Name: Shane Crotty
// Student Number: R00066752

package View;

import Controller.Controller;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage window;
    private Controller control;

    public static void main(String[] args) { launch(args);}

    @Override
    public void start(Stage primaryStage){
        try{
            //The Main Window of our Application
            window = primaryStage;

            //The Controller Object for the Project
            control = new Controller();

            window.setTitle("Covid Close Contact Database");

            Group root = new Group();

            TabPane tabPane = new TabPane();
            BorderPane mainPane = new BorderPane();

            //Adding the tabs to the Tab Pane, the Same Controller is Passed Amongst Them
            tabPane.getTabs().add(new TabA(control));
            tabPane.getTabs().add(new TabB(control));
            tabPane.getTabs().add(new TabC(control));

            // Displaying and Formatting the Scenes/Stage and Windows
            mainPane.setCenter(tabPane);
            Scene scene = new Scene(root, 582,862);
            root.getChildren().add(mainPane);
            window.setScene(scene);
            window.show();


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }




}



