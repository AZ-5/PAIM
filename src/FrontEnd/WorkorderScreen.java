package FrontEnd;


//Imports
import BackEnd.DatabaseConnection;
import BackEnd.Purchase;
import BackEnd.WorkorderTable;
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

//Begin Subclass WorkorderScreen
public class WorkorderScreen extends ScreenController{
    
    private BorderPane borderPane;
    private BorderPane borderPane2;
    private BorderPane borderPane3;
    private BorderPane borderPaneTable;
    private TabPane tabPane;
    private VBox vBox;
    private VBox vBox2;
    private HBox hBox1;
    private HBox hBox2;
    private HBox hBox3;
    private TableView<WorkorderTable> tableView;
    private ComboBox<String> myComboBox;
    
    public WorkorderScreen(){
        // 
        borderPane = new BorderPane();
        borderPane2 = new BorderPane();
        borderPane3 = new BorderPane();
        borderPaneTable = new BorderPane();
        tabPane = new TabPane();
        vBox = new VBox();
        vBox2 = new VBox();
        hBox1 = new HBox();
        hBox2 = new HBox();
        hBox3 = new HBox();
        tableView = new TableView<>();
        myComboBox = new ComboBox<>();
        
// (myComboBox);
        
        //Buttons
        Button showUnAssigned = new Button("Show Unassigned Workorders");
        
        //Populate combobox with all users with the role of Tech
        myComboBox.setItems(populateComboBox(myComboBox));
        
        //HBox for use in Tab1 tableview
        hBox1.getChildren().addAll(showUnAssigned, myComboBox);
        
        //set columns in tableview
        TableColumn<WorkorderTable, Integer> orderIdColumn = 
                new TableColumn<>("Order ID");
        TableColumn<WorkorderTable, String> partNameColumn = new 
        TableColumn<>("Part Name");
        TableColumn<WorkorderTable, Integer> quantityColumn = new 
        TableColumn<>("Quantity");

        
        //Set column sizes for use in Tab3
        orderIdColumn.setPrefWidth(100);
        //assignedToColumn.setPrefWidth(150);
        partNameColumn.setPrefWidth(150);
        quantityColumn.setPrefWidth(100);
        //dateColumn.setPrefWidth(150);
        
        // Bind Columns to the Purchase Model Class
        orderIdColumn.setCellValueFactory(p -> 
                p.getValue().orderIdProperty().asObject());
        partNameColumn.setCellValueFactory(p -> 
                p.getValue().partNameProperty());
        quantityColumn.setCellValueFactory(p -> 
                p.getValue().quantityProperty().asObject());
        
        // Add Columns to TableView
        tableView.getColumns().addAll(orderIdColumn, partNameColumn, 
                quantityColumn);
        //set BorderPane2 for use in Tab1 Unassigned Workorders
        borderPane2.setCenter(tableView);
        borderPane2.setBottom(hBox1);
        
        //Set TabView
                //Set tabs for tabPane
        Tab tab = new Tab();
        Tab tab2 = new Tab();
        Tab tab3 = new Tab();
        Tab tab4 = new Tab();
        
        //Set Tab text
        tab.setText("Unassigned Workorders");
        tab2.setText("Assigned Workorders");
        tab3.setText("Edit Workorders");
        
        //Remove the ability to close tabs
        tab.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        
        //Set the conent of the tabs
        tab.setContent(borderPane2);
        tab2.setContent(borderPane3);
        tab3.setContent(borderPaneTable);
        
        //Add all tabs to tabPane
        tabPane.getTabs().addAll(tab, tab2, tab3);
        

        
        //Set main borderPane
        borderPane.setCenter(tabPane);
        
        
        
        //Handle all the buttons with setOnAction
        showUnAssigned.setOnAction(event -> {
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
                    //LocalDate purchaseDate = rs.getDate("complete_date").toLocalDate();
                    
                    WorkorderTable workorderTable = new WorkorderTable
                    (orderId, partName, quantity);
                    woTable.add(workorderTable);
                    tableView.setItems(woTable);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } 
        });
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
            System.out.println(fullName);
                items.add(fullName); 
            }
        }catch (SQLException e) {
                    e.printStackTrace();
                }
        return items;
    }
    
    public void updateWorkorder(TableView<WorkorderTable> tableView, String newRole){
        WorkorderTable selectedUser = tableView.getSelectionModel().getSelectedItem();
        
        //Prevents a workorder status from be changed to assigned without a 
        // a user from being assigned.
        if (selectedUser == null) {
            System.out.println("No user selected.");
            return;
        }
        // I should only need the int to keep working on this 
        // Hopefully orderid is enough to update the proper row.
        IntegerProperty userId = selectedUser.orderIdProperty();
        int id = userId.get();
    };
    
    public BorderPane getView(){
       return borderPane;
   }  
} //End Subclass WorkorderScreen