package FrontEnd;


//Imports
import BackEnd.DatabaseConnection;
import BackEnd.Purchase;
import BackEnd.WorkorderTable;
import BackEnd.InventoryHandler;
import BackEnd.InventoryTable;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import java.sql.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.scene.control.TableColumn;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;

//Begin Subclass InventoryScreen
public class InventoryScreen extends ScreenController {

    private BorderPane borderPane;
    private BorderPane borderPane2;
    private BorderPane borderPane3;
    private BorderPane borderPane4;
    private BorderPane borderPaneTable;
    private TabPane tabPane;
    private VBox vBox;
    private VBox vBox2;
    private VBox vBoxTab3;
    private HBox hBox1;
    private HBox hBox2;
    private HBox hBox3;
    private TableView<InventoryTable> tableAwaitingStorage;
    private TableView<InventoryTable> tableStored;
    private TableView<InventoryTable> tableViewComplete;
    private ComboBox<String> myComboAwaitingID;
    private ComboBox<String> myComboStored;
    private ComboBox<String> myAssignedWoCombo;
    private ComboBox<String> myComboEmployee;
    
    public InventoryScreen(){
        borderPane = new BorderPane();
        borderPane2 = new BorderPane();
        borderPane3 = new BorderPane();
        borderPane4 = new BorderPane();
        tabPane = new TabPane();
        tableAwaitingStorage = new TableView<>();
        tableStored = new TableView<>();
        hBox1 = new HBox();
        hBox2 = new HBox();
        vBox = new VBox();
        vBox2 = new VBox();
        myComboAwaitingID = new ComboBox<>();
        myComboEmployee = new ComboBox<>();
        
//Begin Tab 1-----------------------------------------------------------------
        //set columns in tableview
        TableColumn<InventoryTable, Integer> itemIdColumnAwaiting = 
                new TableColumn<>("Item ID");
        TableColumn<InventoryTable, String> partNameColumnAwaiting = new 
        TableColumn<>("Part Name");
        TableColumn<InventoryTable, Integer> quantityColumnAwaiting = new 
        TableColumn<>("Quantity");

        
        //Set column sizes for use in Tab3
        itemIdColumnAwaiting.setPrefWidth(100);
        partNameColumnAwaiting.setPrefWidth(150);
        quantityColumnAwaiting.setPrefWidth(100);
        
        // Bind Columns to the Purchase Model Class
        itemIdColumnAwaiting.setCellValueFactory(p -> 
                p.getValue().itemIdProperty().asObject());
        partNameColumnAwaiting.setCellValueFactory(p -> 
                p.getValue().partNameProperty());
        quantityColumnAwaiting.setCellValueFactory(p -> 
                p.getValue().quantityProperty().asObject());

        
        // Add Columns to TableView
        tableAwaitingStorage.getColumns().addAll(itemIdColumnAwaiting, 
                partNameColumnAwaiting, quantityColumnAwaiting);        

        //HBox items
        Button showAwaiting = new Button("Show awaiting storage");
        
        //Assign HBOX
        hBox1.getChildren().addAll(showAwaiting);
        
        //Vbox Buttons
        InventoryHandler comboHandler = new InventoryHandler();
        myComboAwaitingID.setItems(
                comboHandler.populateorderID(myComboAwaitingID, 4));
        Label lblEmployee = new Label("Employee ID");
        myComboEmployee.setItems(
                comboHandler.populateComboBox(myComboEmployee));
        Label lblTitle = new Label("Store item: ");
        Label lblItemID = new Label("Item ID ");
        Label lblAisle = new Label("Aisle Number: ");
        TextField txtAisle = new TextField();
        Label lblBay = new Label("Bay Number: ");
        TextField txtBay = new TextField();
        Label lblShelfLevel = new Label("Shelf Level: ");
        TextField txtShelfLevel = new TextField();
        Button assignItem = new Button("Store Item");        
        
        //Set VBox that will handle assigning workorders
        vBox.setMargin(vBox, new Insets(15, 15, 15, 15));
        vBox.setAlignment(Pos.CENTER);
        vBox.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(lblTitle, lblItemID, myComboAwaitingID, 
                lblEmployee, myComboEmployee, lblAisle, txtAisle, lblBay, 
                txtBay, lblShelfLevel, txtShelfLevel, assignItem);        
        
        //Set BordPane for Tab1
        borderPane2.setCenter(tableAwaitingStorage);
        borderPane2.setBottom(hBox1);
        borderPane2.setLeft(vBox);

//End Tab 1-------------------------------------------------------------------
//Begin Tab 2------------------------------------------------------------------
        //set columns in tableview
        TableColumn<InventoryTable, Integer> itemIdColumnStored = 
                new TableColumn<>("Item ID");
        TableColumn<InventoryTable, String> partNameColumnStored = new 
        TableColumn<>("Part Name");
        TableColumn<InventoryTable, Integer> quantityColumnStored = new 
        TableColumn<>("Quantity");
        TableColumn<InventoryTable, String> locationColumnStored = new 
        TableColumn<>("Quantity");
        
        //Set column sizes for use in Tab3
        itemIdColumnStored.setPrefWidth(100);
        partNameColumnStored.setPrefWidth(150);
        quantityColumnStored.setPrefWidth(100);
        locationColumnStored.setPrefWidth(150);
        
        // Bind Columns to the Purchase Model Class
        itemIdColumnStored.setCellValueFactory(p -> 
                p.getValue().itemIdProperty().asObject());
        partNameColumnStored.setCellValueFactory(p -> 
                p.getValue().partNameProperty());
        quantityColumnStored.setCellValueFactory(p -> 
                p.getValue().quantityProperty().asObject());
        locationColumnStored.setCellValueFactory(p -> 
                p.getValue().locationProperty());
        
        // Add Columns to TableView
        tableStored.getColumns().addAll(itemIdColumnStored, 
                partNameColumnStored, quantityColumnStored, 
                locationColumnStored); 
        //Hbox button
        Button showStoredButton = new Button("Show stored items");
        
        //Hbox
        hBox2.getChildren().addAll(showStoredButton);
        
        //Borderpane
        borderPane3.setCenter(tableStored);

//END Tab 2---------------------------------------------------------------------
//Begin Tabs -------------------------------------------------------------------      
        //Set TabView
        //Set tabs for tabPane
        Tab tab = new Tab();
        Tab tab2 = new Tab();
        Tab tab3 = new Tab();
        Tab tab4 = new Tab();
        
        //Set Tab text
        tab.setText("Inventory");
        tab2.setText("Assigned for shipping");
        tab3.setText("In progress");
        
        //Remove the ability to close tabs
        tab.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        
        //Set the conent of the tabs
        tab.setContent(borderPane2);
        tab2.setContent(borderPane3);
        tab3.setContent(borderPane4);
        
        //Add all tabs to tabPane
        tabPane.getTabs().addAll(tab, tab2, tab3);
//End Tabs----------------------------------------------------------------------            
        //Set main borderPane
        borderPane.setCenter(tabPane);
    
        showAwaiting.setOnAction(event -> {
            ObservableList<InventoryTable> inventoryList = FXCollections.observableArrayList();
            String sql = "SELECT i.item_id, i.quantity, lp.part_name "
                    + "FROM dbo.Inventory as i " 
                    + "JOIN dbo.LUPartName AS lp on i.part_number = lp.part_number "
                    + "WHERE i.status_id = 4 ";

            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int itemID = rs.getInt("item_id");
                    //String assignedTo = rs.getString("full_name");
                    String partName = rs.getString("part_name");
                    int quantity = rs.getInt("quantity");
                    //LocalDate purchaseDate = rs.getDate("complete_date").toLocalDate();
                    
                    InventoryTable item = new InventoryTable(itemID,
                    partName, quantity);
                    
                    inventoryList.add(item);

                    
                }
                tableAwaitingStorage.setItems(inventoryList);
            } catch (SQLException e) {
                e.printStackTrace();
            }             
        });
        
        assignItem.setOnAction(event ->{
            InventoryHandler employeeHandler = new InventoryHandler();
            String userName = myComboEmployee.getValue().toString();
            int itemID = Integer.parseInt(myComboAwaitingID.getValue());
            int userID = employeeHandler.userID(userName);
            String aisle = txtAisle.getText();
            String bay = txtBay.getText();
            String shelfLevel = txtShelfLevel.getText();
            
            if(!aisle.equals("01") && !aisle.equals("02") && !aisle.equals("03"))
            {
                System.out.println("Aisle value must be 01, 02, or 03");
                return;
            }
            if(!bay.equals("01") && !bay.equals("02") && !bay.equals("03"))
            {
                System.out.println("Bay value must be 01, 02, or 03");
                return;
            }
            if(!shelfLevel.equals("01") && !shelfLevel.equals("02") && 
                    !shelfLevel.equals("03"))
            {
                System.out.println("Shelf value must be 01, 02, or 03");
                return;
            }
            String location = aisle + bay + shelfLevel;
            
            System.out.println(location);
            employeeHandler.assignLocation(itemID, userID, location);
        });
        showStoredButton.setOnAction(event -> {
            ObservableList<InventoryTable> inventoryList = FXCollections.observableArrayList();
            String sql = "SELECT i.item_id, i.quantity, lp.part_name "
                    + "FROM dbo.Inventory as i " 
                    + "JOIN dbo.LUPartName AS lp on i.part_number = lp.part_number "
                    + "WHERE i.status_id = 5 ";

            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int itemID = rs.getInt("item_id");
                    String partName = rs.getString("part_name");
                    int quantity = rs.getInt("quantity");
                    String location = rs.getString("location");
                    
                    InventoryTable item = new InventoryTable(itemID,
                    partName, quantity, location);
                    
                    inventoryList.add(item);

                    
                }
                tableAwaitingStorage.setItems(inventoryList);
            } catch (SQLException e) {
                e.printStackTrace();
            } 
        });
    }
    
    
    public BorderPane getView(){
        return borderPane;
       }  
    
   
} //End Subclass InventoryScreen