package FrontEnd;


//Imports
import BackEnd.DatabaseConnection;
import BackEnd.Purchase;
import BackEnd.WorkorderTable;
import BackEnd.InventoryHandler;
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

//Begin Subclass WorkorderScreen
public class WorkorderScreen extends ScreenController{
    
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
    private TableView<WorkorderTable> tableView;
    private TableView<WorkorderTable> tableViewAssigned;
    private TableView<WorkorderTable> tableViewComplete;
    private ComboBox<String> myComboBox;
    private ComboBox<String> myWoComboBox;
    private ComboBox<String> myAssignedWoCombo;
    private ComboBox<String> myCompleteWoCombo;
    
    public WorkorderScreen(){
        // 
        borderPane = new BorderPane();
        borderPane2 = new BorderPane();
        borderPane3 = new BorderPane();
        borderPane4 = new BorderPane();
        borderPaneTable = new BorderPane();
        tabPane = new TabPane();
        vBox = new VBox();
        vBox2 = new VBox();
        vBoxTab3 = new VBox();
        hBox1 = new HBox();
        hBox2 = new HBox();
        hBox3 = new HBox();
        tableView = new TableView<>();
        tableViewAssigned = new TableView<>();
        tableViewComplete = new TableView<>();
        myComboBox = new ComboBox<>();
        myWoComboBox = new ComboBox<>();
        myAssignedWoCombo = new ComboBox<>();
        myCompleteWoCombo = new ComboBox<>();
        
       
//Begin Tab 1-------------------------------------------------------------------
        //Buttons
        Button showUnAssigned = new Button("Show Unassigned Workorders");
        
        
        //Items to populate vBox for assign 
        //Call populateCombo box to view technicans
        myComboBox.setItems(populateComboBox(myComboBox)); 
        myAssignedWoCombo.setItems(populateorderID(myAssignedWoCombo, 2));
        myWoComboBox.setItems(populateorderID(myWoComboBox, 1));
        myCompleteWoCombo.setItems(populateorderID(myWoComboBox, 3));
        //myCompleteWoCombo.setItems());
        Button assignWorkorder = new Button("Assign Work Order");
        Label title = new Label("Select employee and Order:");
        Label txtMyWoBox = new Label("Workorder ID");
        Label txtComboEmployee = new Label("Employee");
        
        
        //HBox for use in Tab1 tableview
        hBox1.getChildren().addAll(showUnAssigned);
        
        //set columns in tableview
        TableColumn<WorkorderTable, Integer> orderIdColumn = 
                new TableColumn<>("Order ID");
        TableColumn<WorkorderTable, String> partNameColumn = new 
        TableColumn<>("Part Name");
        TableColumn<WorkorderTable, Integer> quantityColumn = new 
        TableColumn<>("Quantity");

        
        //Set column sizes for use in Tab3
        orderIdColumn.setPrefWidth(100);
        partNameColumn.setPrefWidth(150);
        quantityColumn.setPrefWidth(100);
        
        // Bind Columns to the Purchase Model Class
        orderIdColumn.setCellValueFactory(p -> 
                p.getValue().orderIdProperty().asObject());
        partNameColumn.setCellValueFactory(p -> 
                p.getValue().partNameProperty());
        quantityColumn.setCellValueFactory(p -> 
                p.getValue().quantityProperty().asObject());
        
        //Set VBox that will handle assigning workorders
        vBox.setMargin(vBox, new Insets(15, 15, 15, 15));
        vBox.setAlignment(Pos.CENTER);
        vBox.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(title, txtComboEmployee, myComboBox, 
                txtMyWoBox, myWoComboBox, assignWorkorder);
        
        // Add Columns to TableView
        tableView.getColumns().addAll(orderIdColumn, partNameColumn, 
                quantityColumn);
        
        //set BorderPane2 for use in Tab1 Unassigned Workorders
        borderPane2.setCenter(tableView);
        borderPane2.setBottom(hBox1);
        borderPane2.setLeft(vBox);
        //End Tab 1
//End Tab 1--------------------------------------------------------------------        
//Begin Tab 2------------------------------------------------------------------
        //set columns in tableview
        TableColumn<WorkorderTable, Integer> orderIdColumnAssigned = 
                new TableColumn<>("Order ID");
        TableColumn<WorkorderTable, String> partNameColumnAssigned = new 
        TableColumn<>("Part Name");
        TableColumn<WorkorderTable, Integer> quantityColumnAssigned = new 
        TableColumn<>("Quantity");
        TableColumn<WorkorderTable, String> assignedToColumn = new 
        TableColumn<>("Assigned To");
        
        //Set column sizes for use in Tab3
        orderIdColumnAssigned.setPrefWidth(100);
        partNameColumnAssigned.setPrefWidth(150);
        quantityColumnAssigned.setPrefWidth(100);
        assignedToColumn.setPrefWidth(100);
        
        // Bind Columns to the Purchase Model Class
        orderIdColumnAssigned.setCellValueFactory(p -> 
                p.getValue().orderIdProperty().asObject());
        partNameColumnAssigned.setCellValueFactory(p -> 
                p.getValue().partNameProperty());
        quantityColumnAssigned.setCellValueFactory(p -> 
                p.getValue().quantityProperty().asObject());
        assignedToColumn.setCellValueFactory(p ->
                p.getValue().techNameProperty());
        
        // Add Columns to TableView
        tableViewAssigned.getColumns().addAll(orderIdColumnAssigned, 
                partNameColumnAssigned, quantityColumnAssigned, 
                assignedToColumn);
        
        //Borderpane3 buttons
        Button showAssigned = new Button("Show Assigned");
        
        //HBox for use in tab2
        hBox2.getChildren().addAll(showAssigned);
        
        //Items to populate vBox for assign 
        //Call populateCombo box to view technicans
        
        Button beginWorkButton = new Button("Begin Work");
        Label titleWork = new Label("Select workorder to work: ");
        Label txtWorkMyWoBox = new Label("Workorder ID");
        
        //Set VBox that will handle assigning workorders
        vBox2.setMargin(vBox, new Insets(15, 15, 15, 15));
        vBox2.setAlignment(Pos.CENTER);
        vBox2.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        vBox2.setSpacing(10);
        vBox2.getChildren().addAll(titleWork, txtWorkMyWoBox, 
            myAssignedWoCombo, beginWorkButton);

        //Set borderPane3 for tab 2
        borderPane3.setCenter(tableViewAssigned);
        borderPane3.setBottom(hBox2);
        borderPane3.setLeft(vBox2);
//end Tab2--------------------------------------------------------------------

//Begin Tab3--------------------------------------------------------------------
       //set columns in tableview
        TableColumn<WorkorderTable, Integer> orderIdColumnComplete = 
                new TableColumn<>("Order ID");
        TableColumn<WorkorderTable, String> partNameColumnComplete = new 
        TableColumn<>("Part Name");
        TableColumn<WorkorderTable, Integer> quantityColumnComplete = new 
        TableColumn<>("Quantity");
        TableColumn<WorkorderTable, String> assignedToColumnComplete = new 
        TableColumn<>("Assigned To");
       // TableColumn<WorkorderTable, String> status = new 
        //TableColumn<>("Status");
        
        //Set column sizes for use in Tab3
        orderIdColumnComplete.setPrefWidth(100);
        partNameColumnComplete.setPrefWidth(150);
        quantityColumnComplete.setPrefWidth(100);
        assignedToColumnComplete.setPrefWidth(100);
        //status.setPrefWidth(100);
        
        // Bind Columns to the Purchase Model Class
        orderIdColumnComplete.setCellValueFactory(p -> 
                p.getValue().orderIdProperty().asObject());
        partNameColumnComplete.setCellValueFactory(p -> 
                p.getValue().partNameProperty());
        quantityColumnComplete.setCellValueFactory(p -> 
                p.getValue().quantityProperty().asObject());
        assignedToColumnComplete.setCellValueFactory(p ->
                p.getValue().techNameProperty());
        //status.setCellValueFactory(p -> p.getValue().statusProperty());
        
        // Add Columns to TableView
        tableViewComplete.getColumns().addAll(orderIdColumnComplete, 
                partNameColumnComplete, quantityColumnComplete, 
                assignedToColumnComplete);
        
        //Borderpane3 buttons
        Button showInProgress = new Button("Show in progress");
        
        //HBox for use in tab2
        hBox3.getChildren().addAll(showInProgress);
        
        //Items to populate vBox for assign 
        //Call populateCombo box to view technicans
        Button completeWorkorderButton = new Button("Complete Workorder");
        Label titleComplete = new Label("Select Order to complete: ");
        Label txtCompleteMyWoBox = new Label("Workorder ID");
        Label textDate = new Label("Date");
        DatePicker completeDate = new DatePicker();
        
        //Set VBox that will handle assigning workorders
        vBoxTab3.setMargin(vBox, new Insets(15, 15, 15, 15));
        vBoxTab3.setAlignment(Pos.CENTER);
        vBoxTab3.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        vBoxTab3.setSpacing(10);
        vBoxTab3.getChildren().addAll(titleComplete, txtCompleteMyWoBox, 
                myCompleteWoCombo, textDate, completeDate,
                completeWorkorderButton);

        //Set borderPane3 for tab 2
        borderPane4.setCenter(tableViewComplete);
        borderPane4.setBottom(hBox3);
        borderPane4.setLeft(vBoxTab3);        
        
//End Tab3---------------------------------------------------------------------        
        //Set TabView
                //Set tabs for tabPane
        Tab tab = new Tab();
        Tab tab2 = new Tab();
        Tab tab3 = new Tab();
        Tab tab4 = new Tab();
        
        //Set Tab text
        tab.setText("Unassigned Workorders");
        tab2.setText("Assigned Workorders");
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
            
        //Set main borderPane
        borderPane.setCenter(tabPane);


//Button handlers--------------------------------------------------------------     
        //Handle all the buttons with setOnAction
        showUnAssigned.setOnAction(event -> {
            tableView.refresh();
            ObservableList<WorkorderTable> woTable = FXCollections.observableArrayList();
            String sql = "SELECT w.workorder_id, w.quantity, lp.part_name " 
                    + "FROM dbo.Workorder AS w " 
                    + "JOIN dbo.LUPartName AS lp on w.part_number = lp.part_number " 
                    + "WHERE w.status_id = 1 ";

            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int orderId = rs.getInt("workorder_id");
                    //String assignedTo = rs.getString("assigned_to");
                    String partName = rs.getString("part_name");
                    int quantity = rs.getInt("quantity");
                    
                    WorkorderTable workorderTable = new WorkorderTable
                    (orderId, partName, quantity);
                    woTable.add(workorderTable);
                    tableView.setItems(woTable);
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            } 
        });
        
        //On click will return all values that are assigned to a tech.
        //Being assigned to a tech requires the status to be 2
        showAssigned.setOnAction(event -> {
           ObservableList<WorkorderTable> woTable = FXCollections.observableArrayList();
            String sql = "SELECT w.workorder_id, w.quantity, lp.part_name,"
                    + " CONCAT(pu.first_name, ' ', pu.last_name) AS full_name " 
                    + "FROM dbo.Workorder AS w " 
                    + "JOIN dbo.LUPartName AS lp on w.part_number = lp.part_number "
                    + "JOIN dbo.P_Users AS pu on w.user_id = pu.user_id " 
                    + "WHERE w.status_id = 2 ";

            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int orderId = rs.getInt("workorder_id");
                    String assignedTo = rs.getString("full_name");
                    String partName = rs.getString("part_name");
                    int quantity = rs.getInt("quantity");
                    //LocalDate purchaseDate = rs.getDate("complete_date").toLocalDate();
                    
                    WorkorderTable workorderTable = new WorkorderTable
                    (orderId, partName, quantity, assignedTo);
                    woTable.add(workorderTable);
                    tableViewAssigned.setItems(woTable);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } 
        });
        
        //Show workorders that are assigned or are being worked
        showInProgress.setOnAction(event -> {
ObservableList<WorkorderTable> woTable = FXCollections.observableArrayList();
            String sql = "SELECT w.workorder_id, w.quantity, lp.part_name,"
                    + " CONCAT(pu.first_name, ' ', pu.last_name) AS full_name " 
                    + "FROM dbo.Workorder AS w " 
                    + "JOIN dbo.LUPartName AS lp on w.part_number = lp.part_number "
                    + "JOIN dbo.P_Users AS pu on w.user_id = pu.user_id " 
                    + "WHERE w.status_id = 3 ";
            
            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int orderId = rs.getInt("workorder_id");
                    String assignedTo = rs.getString("full_name");
                    String partName = rs.getString("part_name");
                    int quantity = rs.getInt("quantity");
                    //LocalDate purchaseDate = rs.getDate("complete_date").toLocalDate();
                    
                    WorkorderTable workorderTable = new WorkorderTable
                    (orderId, partName, quantity, assignedTo);
                    woTable.add(workorderTable);
                    tableViewComplete.setItems(woTable);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }             
        });
        
        assignWorkorder.setOnAction(event -> {
            String user = myComboBox.getValue().toString();
            int orderID = Integer.parseInt(myWoComboBox.getValue());
            int userId = userID(user);
            System.out.println(userId);
            System.out.println(orderID);

            assignWorkorder(orderID, userId);
            //Update assigned workorder combobox, so the combobox always 
            //stays up to date
            myAssignedWoCombo.setItems(populateorderID(myAssignedWoCombo, 2));
        });
        
        //Pass orderID and statusID to set OrderID as in progress
        beginWorkButton.setOnAction(event -> {
            int orderID = Integer.parseInt(myAssignedWoCombo.getValue());
            int statusID = 3;
            updateWorkorder(orderID, statusID);
            myCompleteWoCombo.setItems(populateorderID(myWoComboBox, 3));
        });
        
        completeWorkorderButton.setOnAction(event -> {
            InventoryHandler inventoryHandler = new InventoryHandler();
            int orderID = Integer.parseInt(myCompleteWoCombo.getValue());
            int statusID = 4;
            LocalDate date = completeDate.getValue();
            updateWorkorder(orderID, statusID);
            inventoryHandler.createInventory(orderID);
        });
    }
    public BorderPane getView(){
           return borderPane;
       }     

    //Reusable fucntion. Receives combo box and status id number
    //Returns the order ids that match the status id
    public ObservableList<String> populateorderID(ComboBox<String> comboBox,
            int StatusId){
        
        int statusId = StatusId;
        ObservableList<String> items = FXCollections.observableArrayList();
        String query = "SELECT w.workorder_id " 
                    + "FROM dbo.Workorder AS w " 
                    + "WHERE w.status_id = ? ";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            
            stmt.setInt(1, statusId);    
            ResultSet rs = stmt.executeQuery(); 
            //ResultSet rs = stmt.executeQuery(query); 
            
            //Adds full_name to observableList as long as there are more
            //Items from the query to add. 
            while (rs.next()) {
                String workorderID = rs.getString("workorder_id"); // Fetch data
                items.add(workorderID); 
            }
        }catch (SQLException e) {
                    e.printStackTrace();
                }
        return items;
    }
    
    // This method will search for all users who have the role of Tech
    // The values are stored in an observableList and returned to the 
    // ComboBox
    public ObservableList<String> populateComboBox(ComboBox<String> comboBox){
        ObservableList<String> items = FXCollections.observableArrayList();
        String query = "SELECT CONCAT(u.first_name, ' ', u.last_name) AS full_name "
                + "FROM dbo.P_Users as u "
                + "WHERE u.role = 'Tech' ";
        try(Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            //Adds full_name to observableList as long as there are more
            //Items from the query to add. 
            while (rs.next()) {
                String fullName = rs.getString("full_name"); // Fetch data
                items.add(fullName); 
            }
        }catch (SQLException e) {
                    e.printStackTrace();
                }
        return items;
    }


    //Assign workorder. On button assignOrder this function
    //will receive userID and OrderID. The stored procedure will
    //handle the rest
    public void assignWorkorder(int UserID, int OrderID){
        int userID = UserID;
        int orderID = OrderID;
        
        //Prevents a workorder status from be changed to assigned without a 
        // a user from being assigned.
        if (userID == 0 && orderID == 0){
           System.out.println("No user selected.");
            return;
        }

        try(Connection conn = DatabaseConnection.getConnection()){ 
            
            CallableStatement pstmt = conn.prepareCall("{call InsertTech("
                            + "?, ?)}");
            pstmt.setInt(1, userID);
            pstmt.setInt(2, orderID);  // Set input username
            // Execute the stored procedure
            pstmt.execute();
        }catch (SQLException e) {
                    e.printStackTrace();
                }
                
    };
   
    public void updateWorkorder(int OrderID, int StatusID){
        int statusID = StatusID;
        int orderID = OrderID;
        
        //Prevents a workorder status from be changed to assigned without a 
        // a user from being assigned.
        if (orderID == 0){
           System.out.println("No order selected.");
            return;
        }

        try(Connection conn = DatabaseConnection.getConnection()){ 
            
            CallableStatement pstmt = conn.prepareCall("{call UpdateStatus("
                            + "?, ?)}");
            pstmt.setInt(1, orderID);
            pstmt.setInt(2, statusID);  // Set input username
            // Execute the stored procedure
            pstmt.execute();
        }catch (SQLException e) {
                    e.printStackTrace();
                }
                
    };
    
    public int userID(String selectedUser){
        String[] nameHalf = selectedUser.split(" ");
        if (nameHalf.length >= 2) { // Ensure there are at least two parts
        String firstName = nameHalf[0]; 
        String lastName = nameHalf[1];  


        // Use in SQL query
        String query = "SELECT * FROM P_Users WHERE first_name = ? AND last_name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("user_id"); // Retrieve the user_id
                } else {
                    System.out.println("User not found.");
                    return -1; // Return -1 if no user is found
                }
            }

        } catch (SQLException e) {
                e.printStackTrace();
                 // Return -1 in case of SQL error
                }

        }
        return -1;
    } 
    
    public void completeDateWorkorder(int OrderID, LocalDate Date){
        int orderID = OrderID;
        LocalDate date = Date;
        
        //Add better data validation
        if(orderID == 0 || date == null){
            System.out.println("No orderID or date");
            return;
        }
        String query = "Update dbo.Workorder "
                + "SET dbo.complete_date = ? "
                + "WHERE workorder_id = ? ";
        try(Connection conn = DatabaseConnection.getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setDate(1, java.sql.Date.valueOf(date));  // Set input username
            pstmt.setInt(2, orderID);
            
            // Execute the stored procedure
            pstmt.execute();
        }catch (SQLException e) {
                    e.printStackTrace();
                }        
    }
    
}//End Subclass WorkorderScreen