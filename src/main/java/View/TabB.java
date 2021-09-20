// Student Name: Shane Crotty
// Student Number: R00066752

package View;

import Controller.Controller;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Date;
import java.time.LocalDate;

public class TabB extends Tab {

    public TabB(Controller control) {
        try{
            setText("Close Contact Management System");

            // Header, Title, Caption
            HBox headerPane = new HBox();
            Label header = new Label("\nClose Contact Management System");
            headerPane.setAlignment(Pos.CENTER);
            headerPane.getChildren().add(header);
            headerPane.setStyle("-fx-font-weight: bold");

            // ID and Date Input
            VBox dropDowns = new VBox(5);
            dropDowns.setPadding(new Insets(10));
            dropDowns.setAlignment(Pos.BASELINE_LEFT);

            Label firstContactLabel = new Label("Please Enter the ID of the First Contact:");
            TextField firstContactTextField = new TextField();

            Label secondContactLabel = new Label("Please Enter the ID of the Second Contact:");
            TextField secondContactTextField = new TextField();

            Label dateLabel = new Label("Date of Close Contact:");
            DatePicker date = new DatePicker();
            date.setMinWidth(362);

            dropDowns.getChildren().addAll(firstContactLabel, firstContactTextField, secondContactLabel, secondContactTextField, dateLabel, date);

            // Button to Submit Contents of Textfields and Create a Close Contact
            HBox recordButtonBox = new HBox(5);
            recordButtonBox.setPadding(new Insets(10));
            recordButtonBox.setAlignment(Pos.BASELINE_LEFT);

            Button recordCloseContactButton = new Button("Record Close Contact");
            recordCloseContactButton.setOnAction(e -> {
                LocalDate localD = date.getValue();
                Date formatDate = Date.valueOf(localD);
                control.addContact(firstContactTextField.getText(), secondContactTextField.getText(), formatDate);
                firstContactTextField.clear();
                secondContactTextField.clear();

            });
            recordButtonBox.getChildren().add(recordCloseContactButton);

            // Table to Display Contacts from a Given Date
            TableView<Contact> table2 = new TableView();
            table2.setMaxHeight(150);
            TableColumn<Contact, String> id1Column = new TableColumn("Person 1");
            id1Column.setMinWidth(183.75);
            TableColumn<Contact, String> id2Column = new TableColumn("Person 2");
            id2Column.setMinWidth(183.75);
            TableColumn <Contact, String>dateColumn = new TableColumn("Date of Contact");
            dateColumn.setMinWidth(183.75);

            // Set the Value for each Cell
            id1Column.setCellValueFactory(new PropertyValueFactory<>("id1"));
            id2Column.setCellValueFactory(new PropertyValueFactory<>("id2"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfContact"));

            // Add the Columns to the Table
            table2.getColumns().addAll(id1Column, id2Column, dateColumn);

            // Search for Close Contacts via ID Button and Labels
            VBox searchCloseContactsBox = new VBox(5);
            searchCloseContactsBox.setPadding(new Insets(12));
            searchCloseContactsBox.setAlignment(Pos.BASELINE_LEFT);

            Label searchContactLabel = new Label("Search for Close Contacts By ID:");
            TextField searchContactTextField = new TextField();

            // Date Picker to Select from which Date, Close Contact may have Occurred
            Label dateContactLabel = new Label("From the Following Date:");
            DatePicker contactDate = new DatePicker();
            Button searchContactButton = new Button("Search");

            searchContactButton.setOnAction(actionEvent -> {LocalDate localD2 = contactDate.getValue();
                Date formatDate = Date.valueOf(localD2);
                ObservableList<Contact> matchedContacts = FXCollections.observableArrayList(control.getContactByIdDate(searchContactTextField.getText(), formatDate));
                table2.setItems(matchedContacts);
            });
            searchCloseContactsBox.getChildren().addAll(searchContactLabel,searchContactTextField,dateContactLabel, contactDate, searchContactButton);

            // Image to be Displayed
            Image closeContactImage = new Image("closeContactImage.jpg");
            ImageView iv = new ImageView();
            iv.setImage(closeContactImage);

            iv.setFitWidth(582);
            iv.setFitHeight(181);

            // Row at Footer, with Button to Exit/Close the Program
            HBox footerRow = new HBox();
            footerRow.setPadding(new Insets(10));
            footerRow.setAlignment(Pos.BASELINE_RIGHT);

            //The Exit Button and Handling
            Button exitButton = new Button("Exit");
            ExitWindow exitWindow = new ExitWindow();
            exitButton.setOnAction(e-> exitWindow.display(control));
            footerRow.getChildren().addAll(exitButton);

            // The Vertical Box Layout to Act as the "Root" of "TabB", Containing All Other Stacks/Panes/Boxes etc.
            VBox tabRoot = new VBox();
            tabRoot.getChildren().addAll(headerPane,dropDowns, recordButtonBox,table2, searchCloseContactsBox, iv, footerRow);

            setContent(tabRoot);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}