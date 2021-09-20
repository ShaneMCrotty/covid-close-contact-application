// Student Name: Shane Crotty
// Student Number: R00066752

package View;

import Controller.Controller;
import Model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TabA extends Tab{

    public TabA(Controller control) throws IOException, ClassNotFoundException {
        setText("Individual/s Management System");

        // Header, Title, Caption
        HBox headerPane = new HBox();
        Label header = new Label("\nIndividual/s Management System");
        headerPane.setAlignment(Pos.CENTER);
        headerPane.getChildren().add(header);
        headerPane.setStyle("-fx-font-weight: bold");

        // User Input Boxes, Personal Details
        VBox detailsColumn = new VBox(5);
        detailsColumn.setPadding(new Insets(10));
        detailsColumn.setAlignment(Pos.BASELINE_LEFT);

        Label fnameLabel = new Label("Enter First Name:");
        TextField fnameTextfield = new TextField();

        Label middlenameLabel = new Label("Enter Middle Name:");
        TextField middlenameTextfield = new TextField();

        Label lnameLabel = new Label("Enter Last Name:");
        TextField lnameTextfield = new TextField();

        Label idLabel = new Label("Enter ID:");
        TextField idTextfield = new TextField();

        Label phoneLabel = new Label("Enter Phone Number:");
        TextField phoneTextfield = new TextField();

        Label emailLabel = new Label("Enter Email Address:");
        TextField emailTextfield = new TextField();

        // Add All The Labels and Input Boxes to a VBox
        detailsColumn.getChildren().addAll(fnameLabel, fnameTextfield, middlenameLabel, middlenameTextfield, lnameLabel, lnameTextfield, idLabel, idTextfield, phoneLabel, phoneTextfield, emailLabel, emailTextfield);

        // Buttons to Add, Remove and List the Details Entered in the Fields
        HBox buttonRowAddRemoveList = new HBox(5);
        buttonRowAddRemoveList.setPadding(new Insets(10));
        buttonRowAddRemoveList.setAlignment(Pos.BASELINE_LEFT);

        // The Add Button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {

                    control.addPerson(idTextfield.getText(), fnameTextfield.getText(), middlenameTextfield.getText(), lnameTextfield.getText(), phoneTextfield.getText(), emailTextfield.getText());
                    fnameTextfield.clear();
                    middlenameTextfield.clear();
                    lnameTextfield.clear();
                    idTextfield.clear();
                    phoneTextfield.clear();
                    emailTextfield.clear();
                });

        // The Display All in System Button
        Button displayButton = new Button("Display All");

        // Add All The Buttons to a HBox
        buttonRowAddRemoveList.getChildren().addAll(addButton, displayButton);

        // Table to Display the Results of the List Button
        TableView<Person> table = new TableView();
        table.setMaxHeight(150);
        TableColumn<Person, String> fnameColumn = new TableColumn("First Name");
        fnameColumn.setMinWidth(93.75);
        TableColumn<Person, String> mnameColumn = new TableColumn("Middle Name");
        mnameColumn.setMinWidth(93.75);
        TableColumn <Person, String>lnameColumn = new TableColumn("Last Name");
        lnameColumn.setMinWidth(93.75);
        TableColumn <Person, String> idColumn = new TableColumn("ID");
        idColumn.setMinWidth(75.75);
        TableColumn <Person, String> phoneColumn = new TableColumn("Phone No");
        phoneColumn.setMinWidth(111.75);
        TableColumn <Person, String> emailColumn = new TableColumn("Email");
        emailColumn.setMinWidth(111.75);

        // Set the Value for each Cell (Our User Details)
        fnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        mnameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));


        // Add the Columns to the Table
        table.getColumns().addAll(fnameColumn, mnameColumn, lnameColumn, idColumn, phoneColumn, emailColumn);

        // Display all Persons in the Database
        displayButton.setOnAction(actionEvent -> {
            ObservableList<Person> allPersonObjects = FXCollections.observableArrayList(control.createObservableListAll());
            table.setItems(allPersonObjects);
        });

        // Search Button for Person/s via ID
        HBox labelSearch = new HBox(5);
        labelSearch.setPadding(new Insets(10));
        labelSearch.setAlignment(Pos.BASELINE_LEFT);
        Label searchLabel = new Label("Search for Person via ID Number:");
        labelSearch.getChildren().addAll(searchLabel);
        HBox buttonRowSearch = new HBox(5);
        buttonRowSearch.setPadding(new Insets(10));
        buttonRowSearch.setAlignment(Pos.BASELINE_LEFT);

        TextField searchField = new TextField();
        Button searchButton = new Button("Search");

        searchButton.setOnAction(actionEvent -> {
            ObservableList<Person> searchedPerson = FXCollections.observableArrayList(control.getPersonById(searchField.getText()));
            table.setItems(searchedPerson);
                });

        buttonRowSearch.getChildren().addAll(searchField,searchButton);

        // Remove Button to remove Person/s via ID
        HBox idLabelRow = new HBox(5);
        idLabelRow.setPadding(new Insets(10));
        idLabelRow.setAlignment(Pos.BASELINE_LEFT);
        Label idLabel2 = new Label("Remove a Person via ID Number:");
        idLabelRow.getChildren().addAll(idLabel2);

        HBox buttonRowSearch2 = new HBox(5);
        buttonRowSearch2.setPadding(new Insets(10));
        buttonRowSearch2.setAlignment(Pos.BASELINE_LEFT);

        TextField idSearchField = new TextField();
        Button removeButton = new Button("Remove");

        removeButton.setOnAction(e ->
            control.removePerson(idSearchField.getText()));

        buttonRowSearch2.getChildren().addAll(idSearchField,removeButton);


        // Row at Footer, with Button to Exit/Close the Program
        HBox footerRow = new HBox();
        footerRow.setPadding(new Insets(10));
        footerRow.setAlignment(Pos.BASELINE_RIGHT);

        //The Exit Button and Handling
        Button exitButton = new Button("Exit");
        ExitWindow exitWindow = new ExitWindow();
        exitButton.setOnAction(e-> exitWindow.display(control));
        footerRow.getChildren().addAll(exitButton);

        // The Vertical Box Layout to Act as the "Root" of "TabA", Containing All Other Stacks/Panes/Boxes etc.
        VBox tabRoot = new VBox();
        tabRoot.getChildren().addAll(headerPane, detailsColumn, buttonRowAddRemoveList, table, labelSearch, buttonRowSearch, idLabelRow, buttonRowSearch2, footerRow);

        setContent(tabRoot);

    }
}
