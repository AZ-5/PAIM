package FrontEnd;

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
import BackEnd.DatabaseConnection;
import BackEnd.Warnings;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Supplier;
import static javafx.application.Application.STYLESHEET_CASPIAN;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;



public class LoginView extends ScreenController {

    public Supplier<Pane> getView() {
        return this::buildLoginView;
        //return borderPane;
    }
   // private BorderPane borderPane;
    
    private BorderPane buildLoginView(){
   // public LoginView() {
        // Initialize the view components
        BorderPane borderPane = new BorderPane();
        
        // User Region to create blank space on left
        Region leftSpacer = new Region();
        leftSpacer.setPrefWidth(300); 
        

        // User Region to set space on right
        Region rightSpacer = new Region();
        rightSpacer.setPrefWidth(300); 

        // Create a VBox to stack the text and buttons vertically
        VBox vbox = new VBox(10);  
        vbox.setAlignment(Pos.CENTER);  

        // Create text field for username
        Label txtTitle = new Label("Login");
        txtTitle.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD,
                FontPosture.REGULAR, 25));
        Label txtUsername = new Label("Username");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username:");

        // Create password field
        Label txtPassword = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        // Create login button
        Button loginButton = new Button("Log In");
        Button createAccButton = new Button("Create Account");

        // Set width for the text fields and password field
        usernameField.setPrefWidth(100);  
        passwordField.setPrefWidth(100);  

        // Add components to the VBox
        vbox.getChildren().addAll(txtTitle, txtUsername,usernameField, 
                txtPassword, passwordField, loginButton, createAccButton);

        // Add the VBox to the center of the BorderPane
        borderPane.setCenter(vbox);
        // Set regions and buttons in BorderPane
        borderPane.setLeft(leftSpacer);
        borderPane.setRight(rightSpacer);
        
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();  
            //Test if username is blank
            if(username == null || username.trim().isEmpty()){
                Warnings.emptyUsername();
                return;
            }
            String password = passwordField.getText();  
            //Test if password is blank
            if(password == null || password.trim().isEmpty()){
                Warnings.emptyPassword();
                return;
            }
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
                    
                    if(result == 1){
                        getRole(username);
                    } else{
                        //Warning window
                        Warnings.loginError();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

        });
        
        createAccButton.setOnAction(event -> switchTo("create"));
        return borderPane;
    }

    /*
    // Getter for the BorderPane
    public Supplier<Pane> getView() {
        return this::buildLoginView;
        //return borderPane;
    }
    */
    
    //After successful login look for user role.
    //The role determine what screen you see.
    public void getRole(String username){
        String Username = username;
        String role = null;
        String sql = "SELECT role FROM dbo.P_Users WHERE username = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)){
                
                 pstmt.setString(1, Username);
                try(ResultSet rs = pstmt.executeQuery()){
                    if (rs.next()) {
                        role = rs.getString("role");
                    } 
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            //
            if(role.equals("Admin")){
                switchTo("debug");
            } else if(role.equals("Tech")){
                switchTo("workorder");
            } else if(role.equals("Inventory")){
                switchTo("inventory");
            } else if(role.equals("Purchasing")){
                switchTo("purchasing");
            }
            
    
    }
}