package FrontEnd;
//Imports
import BackEnd.DatabaseConnection;
import BackEnd.Validation;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


//NOTES TO SELF. Need to validate all entries.
// Then needs confirmation screen

//Begin Subclass CreateAccount
public class CreateAccount extends ScreenController{
    
    private BorderPane borderPane;
    private HBox hBox1;
    
    public CreateAccount(){
        borderPane = new BorderPane();
        
        // Create labels and TextFields for use in AddUser Tab1
        //userid, username, firstname, lastname, role, email, password
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);  // Horizontal gap between columns
        gridPane.setVgap(10);  // Vertical gap between rows
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        
        // Username Grid
        Label lblUserName = new Label("Username");
        TextField userNameText = new TextField();
        gridPane.add(lblUserName, 0, 0);
        gridPane.add(userNameText, 0, 1);

        // Password Grid
        Label lblPassword = new Label("Password");
        TextField passwordText = new TextField();
        Label lblPasswordWarning = new Label("Password: 8 characters/one uppercase"
                + "/one number");
        gridPane.add(lblPassword, 1, 0);
        gridPane.add(passwordText, 1, 1);
        gridPane.add(lblPasswordWarning, 2, 1);
        

        // Role Grid
        Label lblRole = new Label("Role");
        TextField roleText = new TextField();
        gridPane.add(lblRole, 0, 4);
        gridPane.add(roleText, 0, 5);

        // First Name Grid
        Label lblFirstName = new Label("First Name");
        TextField firstNameText = new TextField();
        gridPane.add(lblFirstName, 0, 2);
        gridPane.add(firstNameText, 0, 3);

        // Last Name Grid
        Label lblLastName = new Label("Last Name");
        TextField lastNameText = new TextField();
        gridPane.add(lblLastName, 1, 2);
        gridPane.add(lastNameText, 1, 3);

        // Email Grid
        Label lblEmail = new Label("Email");
        TextField emailText = new TextField();
        gridPane.add(lblEmail, 2, 2);
        gridPane.add(emailText, 2, 3);
        
        //Buttons 
        Button createAccountButton = new Button("Create Account");
        Button clearButton = new Button("Clear");
        
        hBox1 = new HBox();
        hBox1.setSpacing(10);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(createAccountButton, clearButton);
        
        borderPane.setCenter(gridPane);
        borderPane.setBottom(hBox1);
        
        
        // NEED TO ADD ERROR HANDLING FOR CREATE ACCOUNT
        createAccountButton.setOnAction(event -> {
            //Need to check if USername exists
            String userName = userNameText.getText();
            String password = passwordText.getText();
            String role = roleText.getText();
            String firstName = firstNameText.getText();
            String lastName = lastNameText.getText();
            String email = emailText.getText();
            
            try(Connection conn = DatabaseConnection.getConnection())
                {
                    CallableStatement stmt = conn.prepareCall("{call InsertUser("
                            + "?, ?, ?, ?, ?, ?)}");
                    stmt.setString(1, userName);  // Set input username
                    stmt.setString(2, email);  // Set input password
                    stmt.setString(3, password);
                    stmt.setString(4, firstName);
                    stmt.setString(5, lastName);
                    stmt.setString(6, role);

                    // Execute the stored procedure
                    stmt.execute();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
        });
        
        clearButton.setOnAction(event -> {
            
        });
    }
    

        // Getter for the BorderPane
    public BorderPane getView() {
        return borderPane;
    }
} //End Subclass CreateAccount