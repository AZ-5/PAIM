package FrontEnd;


//Imports
import BackEnd.DatabaseConnection;
import BackEnd.Warnings;
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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Supplier;
import javafx.scene.control.TableColumn;
import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;

//Begin Subclass InventoryScreen
public class InventoryScreen extends ScreenController {

    public Supplier<Pane> getView(){
        return this::buildInventoryFactory;
    }
    private BorderPane borderPane;
    private BorderPane borderPane2;
    private BorderPane borderPane3;
    private BorderPane borderPane4;
    private BorderPane borderPaneTable;
    private TabPane tabPane;
    private VBox vBox;
    private VBox vBoxTab2;
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
    
    private BorderPane buildInventoryFactory(){
    //public InventoryScreen(){
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
        vBoxTab2 = new VBox();
        myComboAwaitingID = new ComboBox<>();
        myComboEmployee = new ComboBox<>();
        myComboStored = new ComboBox<>();
        
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
        TableColumn<InventoryTable, String> aisleColumnStored = new 
        TableColumn<>("Aisle");
        TableColumn<InventoryTable, String> bayColumnStored = new 
        TableColumn<>("Bay");
        TableColumn<InventoryTable, String> shelfColumnStored = new 
        TableColumn<>("Shelf");
        
        //Set column sizes for use in Tab3
        itemIdColumnStored.setPrefWidth(100);
        partNameColumnStored.setPrefWidth(150);
        quantityColumnStored.setPrefWidth(100);
        aisleColumnStored.setPrefWidth(150);
        bayColumnStored.setPrefWidth(150);
        shelfColumnStored.setPrefWidth(150);
        
        // Bind Columns to the Purchase Model Class
        itemIdColumnStored.setCellValueFactory(p -> 
                p.getValue().itemIdProperty().asObject());
        partNameColumnStored.setCellValueFactory(p -> 
                p.getValue().partNameProperty());
        quantityColumnStored.setCellValueFactory(p -> 
                p.getValue().quantityProperty().asObject());
        aisleColumnStored.setCellValueFactory(p -> 
                p.getValue().aisleProperty());
        bayColumnStored.setCellValueFactory(p -> 
                p.getValue().bayProperty());
        shelfColumnStored.setCellValueFactory(p -> 
                p.getValue().shelfProperty());
        
        // Add Columns to TableView
        tableStored.getColumns().addAll(itemIdColumnStored, 
                partNameColumnStored, quantityColumnStored, 
                aisleColumnStored, bayColumnStored, shelfColumnStored); 
        //Hbox button
        Button showStoredButton = new Button("Show stored items");
        
        //Hbox
        hBox2.getChildren().addAll(showStoredButton);
        
        //VBox
        Label lblCompleteTitle = new Label("Move items for shipping: ");
        Label lblItemIDComplete = new Label("Item ID: ");
        myComboStored.setItems(
                comboHandler.populateorderID(myComboAwaitingID, 5));
        Button completeStorage = new Button("Ship item");
        
        //Set VBox that will handle assigning workorders
        vBoxTab2.setMargin(vBox, new Insets(15, 15, 15, 15));
        vBoxTab2.setAlignment(Pos.CENTER);
        vBoxTab2.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        vBoxTab2.setSpacing(10);
        vBoxTab2.getChildren().addAll(lblCompleteTitle, lblItemIDComplete, 
               myComboStored, completeStorage);
        
        //Borderpane
        borderPane3.setCenter(tableStored);
        borderPane3.setBottom(hBox2);
        borderPane3.setLeft(vBoxTab2);

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
        tabPane.getTabs().addAll(tab, tab2);
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
                    String partName = rs.getString("part_name");
                    int quantity = rs.getInt("quantity");
                    
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
            int status = 5;
            String aisle = txtAisle.getText();
            String bay = txtBay.getText();
            String shelfLevel = txtShelfLevel.getText();
            
            if(!aisle.equals("01") && !aisle.equals("02") && !aisle.equals("03"))
            {
                System.out.println("Aisle value must be 01, 02, or 03");
                Warnings.locationError();
                return;
            }
            if(!bay.equals("01") && !bay.equals("02") && !bay.equals("03"))
            {
                System.out.println("Bay value must be 01, 02, or 03");
                Warnings.locationError();
                return;
            }
            if(!shelfLevel.equals("01") && !shelfLevel.equals("02") && 
                    !shelfLevel.equals("03"))
            {
                System.out.println("Shelf value must be 01, 02, or 03");
                Warnings.locationError();
                return;
            }
            String location = aisle + bay + shelfLevel;
            
            employeeHandler.assignLocation(itemID, userID, location, status);
            myComboStored.setItems(
                comboHandler.populateorderID(myComboAwaitingID, 5));
        });
        showStoredButton.setOnAction(event -> showStored());
        /*
        {
            ObservableList<InventoryTable> inventoryList = FXCollections.observableArrayList();
            String sql = "SELECT i.item_id, i.quantity, lp.part_name, "
                    + "SUBSTRING(i.location, 1, 2) AS aisle, "
                    + "SUBSTRING(i.location, 3, 2) AS bay, "
                    + "SUBSTRING(i.location, 5, 2) AS shelf_level "
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
                    String aisle = rs.getString("aisle");
                    String bay = rs.getString("bay");
                    String shelf = rs.getString("shelf_level");
                    
                    InventoryTable item = new InventoryTable(itemID,
                    partName, quantity, aisle, bay, shelf);
                    
                    inventoryList.add(item);

                    
                }
                tableStored.setItems(inventoryList);
            } catch (SQLException e) {
                e.printStackTrace();
            } 

        });
       */ 
        //Pass itemID and then update the value as shipped
        completeStorage.setOnAction(event -> {
            int itemID = Integer.parseInt(myComboStored.getValue());
            int status = 6;
            String location = "Shipped";
            InventoryHandler shipmentHandler = new InventoryHandler();
            String sql = "SELECT i_user_id "
                    + "FROM dbo.Inventory "
                    + "WHERE item_id = ? ";
            
            try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // Assign value to the "?" parameter
                pstmt.setInt(1, itemID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int userID = rs.getInt("i_user_id");
                    shipmentHandler.assignLocation(itemID, userID, 
                            location, status);
                }
            }
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            showStored();
        });
        return borderPane;
    }
    
  
    public void showStored(){
        ObservableList<InventoryTable> inventoryList = FXCollections.observableArrayList();
            String sql = "SELECT i.item_id, i.quantity, lp.part_name, "
                    + "SUBSTRING(i.location, 1, 2) AS aisle, "
                    + "SUBSTRING(i.location, 3, 2) AS bay, "
                    + "SUBSTRING(i.location, 5, 2) AS shelf_level "
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
                    String aisle = rs.getString("aisle");
                    String bay = rs.getString("bay");
                    String shelf = rs.getString("shelf_level");
                    
                    InventoryTable item = new InventoryTable(itemID,
                    partName, quantity, aisle, bay, shelf);
                    
                    inventoryList.add(item);

                    
                }
                tableStored.setItems(inventoryList);
            } catch (SQLException e) {
                e.printStackTrace();
            } 
    }
    /*
    public BorderPane getView(){
        return borderPane;
       }  
    */
    
} //End Subclass InventoryScreen