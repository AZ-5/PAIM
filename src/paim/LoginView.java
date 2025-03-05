package paim;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import javafx.scene.layout.Region;



public class LoginView extends ScreenController {

    private BorderPane borderPane;

    public LoginView() {
        // Initialize the view components
        borderPane = new BorderPane();
        
        // User Region to create blank space on left
        Region leftSpacer = new Region();
        leftSpacer.setPrefWidth(100); 
        

        // User Region to set space on right
        Region rightSpacer = new Region();
        rightSpacer.setPrefWidth(100); 

        // Create a VBox to stack the text and buttons vertically
        VBox vbox = new VBox(10);  
        vbox.setAlignment(Pos.CENTER);  

        // Create text field for username
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        // Create password field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        // Create login button
        Button loginButton = new Button("Log In");

        // Set width for the text fields and password field
        usernameField.setPrefWidth(200);  
        passwordField.setPrefWidth(200);  

        // Add components to the VBox
        vbox.getChildren().addAll(usernameField, passwordField, loginButton);

        // Add the VBox to the center of the BorderPane
        borderPane.setCenter(vbox);
        // Set regions and buttons in BorderPane
        borderPane.setLeft(leftSpacer);
        borderPane.setRight(rightSpacer);
        
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();  
            String password = passwordField.getText();  

            try(Connection conn = DatabaseConnection.getConnection())
                {
                    CallableStatement stmt = conn.prepareCall("{call AuthenticateUser(?, ?, ?)}");
                    stmt.setString(1, username);  // Set input username
                    stmt.setString(2, password);  // Set input password
                    stmt.registerOutParameter(3, Types.INTEGER);  // Register output parameter

                    // Execute the stored procedure
                    stmt.execute();

                    // Retrieve the return value from the output parameter
                    int result = stmt.getInt(3);

                    System.out.println("Return value: " + result);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        });
    }

    // Getter for the BorderPane
    public BorderPane getView() {
        return borderPane;
    }
}