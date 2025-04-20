package FrontEnd;


//Imports
import FrontEnd.ScreenController;
import java.util.function.Supplier;
import static javafx.application.Application.STYLESHEET_CASPIAN;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//Begin Subclass DebugWindowsScreen
public class DebugWindowsScreen extends ScreenController {
    
    public Supplier<Pane> getView(){
        return this::buildDebugView;
    }
    
    private VBox titleVBox;
    
    //private BorderPane borderPane;
    private BorderPane buildDebugView(){

//Title VBox ------------------------------------------------------------------
       //Screen
       titleVBox = new VBox();
       Text woTitle = new Text("Work Order Management");
       woTitle.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD,
                FontPosture.REGULAR, 25));
       //Title VBOX
       titleVBox.setAlignment(Pos.CENTER);
       titleVBox.getChildren().addAll(woTitle);        
        
       BorderPane borderPane1 = new BorderPane();
        
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
                workorderButton, createAccountButton, loginButton);
        
        // Add vbox to borderpane
        borderPane1.setCenter(vbox);
        borderPane1.setLeft(leftSpacer);
        borderPane1.setRight(rightSpacer);
        borderPane1.setTop(titleVBox);
        
        loginButton.setOnAction(event -> switchTo("login"));
        purchasingButton.setOnAction(event -> switchTo("purchasing"));
        adminButton.setOnAction(event -> switchTo("admin"));
        createAccountButton.setOnAction(event -> switchTo("create"));
        workorderButton.setOnAction(event -> switchTo("workorder"));
        inventoryButton.setOnAction(event -> switchTo("inventory"));
        
        return borderPane1;
    }

} //End Subclass DebugWindowsScreen