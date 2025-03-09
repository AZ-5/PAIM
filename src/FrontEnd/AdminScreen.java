package FrontEnd;

import BackEnd.Purchase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


//Imports

//Begin Subclass AdminScreen
public class AdminScreen extends ScreenController {
    
    private BorderPane borderPane;
    private BorderPane borderPane2;
    private BorderPane borderPane3;
    private BorderPane borderPaneTable;
    private TabPane tabPane;
    private VBox vBox;
    private VBox vBox2;
    private HBox hBox1;
    private HBox hBox2;
    private TableView<Purchase> tableView;
    
    public AdminScreen(){
        //Set Panes
        borderPane = new BorderPane();
        borderPane2 = new BorderPane();
        borderPane3 = new BorderPane();
        borderPaneTable = new BorderPane();
        tabPane = new TabPane();
        vBox = new VBox();
        vBox2 = new VBox();
        hBox1 = new HBox();
        hBox2 = new HBox();
        tableView = new TableView<>();
        
        // Create labels and TextFields for use in AddUser Tab1
        //userid, username, firstname, lastname, role, email, password
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);  // Horizontal gap between columns
        gridPane.setVgap(10);  // Vertical gap between rows
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        
        // Username Grid
        Label lblUserID = new Label("User ID");
        TextField userIDText = new TextField();
        gridPane.add(lblUserID, 0, 0);
        gridPane.add(userIDText, 0, 1);

        // Username Grid
        Label lblUserName = new Label("Username");
        TextField userNameText = new TextField();
        gridPane.add(lblUserName, 1, 0);
        gridPane.add(userNameText, 1, 1);

        // Role Grid
        Label lblRole = new Label("Role");
        TextField roleText = new TextField();
        gridPane.add(lblRole, 2, 0);
        gridPane.add(roleText, 2, 1);

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
       
        //Set BorderPane for tab1 AddUser
        borderPane2.setCenter(gridPane);
        
        //Set tabs for tabPane
        Tab tab = new Tab();
        Tab tab2 = new Tab();
        Tab tab3 = new Tab();
        
        //Set Tab text
        tab.setText("Add User");
        tab2.setText("Remove User");
        tab3.setText("Look up User");
        
        //Remove the ability to close tabs
        tab.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false); 
        
        //Set the conent of the tabs
        tab.setContent(borderPane2);
        
        //Set Hbox for use in Tab1 (Add user)
        tabPane.getTabs().addAll(tab, tab2, tab3);
        
        borderPane.setCenter(tabPane);
    }

public BorderPane getView(){
       return borderPane;
   }  
} //End Subclass AdminScreen