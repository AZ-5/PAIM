package FrontEnd;


//Imports
import FrontEnd.ScreenController;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

//Begin Subclass DebugWindowsScreen
public class DebugWindowsScreen extends ScreenController {
    
    private BorderPane borderPane;
    
    public DebugWindowsScreen(){
        
        borderPane = new BorderPane();
        
        // User Region to create blank space on left
        Region leftSpacer = new Region();
        leftSpacer.setPrefWidth(100); 
        
        // Use Region to creat blank space on right
        Region rightSpacer = new Region();
        rightSpacer.setPrefWidth(100);
        
        // Create vbox to stack buttons vertically
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        
        // Create Buttons for use to switch to screens
        Button purchasingButton = new Button("Purchasing");
        Button inventoryButton = new Button("Inventory");
        Button workorderButton = new Button("Work Order");
        Button adminButton = new Button("Admin");
        Button createAccountButton = new Button("Create Account");
        Button loginButton = new Button("Login");     
        
        // Add buttons to the Vbox
        vbox.getChildren().addAll(purchasingButton, inventoryButton, 
                workorderButton, adminButton, createAccountButton, loginButton);
        
        // Add vbox to borderpane
        borderPane.setCenter(vbox);
        borderPane.setLeft(leftSpacer);
        borderPane.setRight(rightSpacer);
        
        loginButton.setOnAction(event -> switchTo("login"));
        purchasingButton.setOnAction(event -> switchTo("purchasing"));
        adminButton.setOnAction(event -> switchTo("admin"));
        createAccountButton.setOnAction(even -> switchTo("create"));
        workorderButton.setOnAction(event -> switchTo("workorder"));
    }
    
    public BorderPane getView(){
        return borderPane;
    }

} //End Subclass DebugWindowsScreen