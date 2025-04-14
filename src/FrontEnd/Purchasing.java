package FrontEnd;


//Imports
import BackEnd.DatabaseConnection;
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
import BackEnd.Purchase;
import BackEnd.Validation;
import BackEnd.Warnings;
import java.sql.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.scene.control.TableColumn;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import javafx.scene.layout.Pane;

//Begin Subclass Purchasing
public class Purchasing extends ScreenController {
    
    public Supplier<Pane> getView(){
        return this::buildPurchasingView;
    }
    
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
    private TableView<Purchase> tableView;
    
    private BorderPane buildPurchasingView(){
    //public Purchasing(){
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
        hBox3 = new HBox();
        tableView = new TableView<>();
        
        //Combo Box options
        ObservableList<String> options = FXCollections.observableArrayList(
            "Widget",
            "Fidget",
            "Gadget",
            "Module",
            "Tool");
        
        //Set vBox for TabPan1
        vBox.setAlignment(Pos.CENTER);
        TextField quanityText = new TextField();
        Label lblQuanity = new Label("Quanity Purchased");
        TextField customerText = new TextField();
        Label lblCustomer = new Label("Customer");
        DatePicker PurchaseDate = new DatePicker();
        Label lblDate = new Label("Purchase Date");
        ComboBox partPicker = new ComboBox(options);
        Label lblPart = new Label("Part Nomencalture");
        vBox.getChildren().addAll(lblQuanity, quanityText, lblCustomer,
                customerText, lblDate, PurchaseDate, lblPart, partPicker);
        
        // Set HBox for TabPane1
        Button submitPurchase = new Button("Submit Order");
        Button clearButton = new Button("Clear All Fields");
        hBox1.getChildren().addAll(submitPurchase, clearButton);
        
        //Set BorderPane2 for TabPane1
        borderPane2.setCenter(vBox);
        borderPane2.setBottom(hBox1);
        
        // Set VBox for TabPane2 (Insert Customer)
        vBox2.setAlignment(Pos.CENTER);
        TextField customerNameText = new TextField();
        Label lblCustomerName = new Label("Customer Name or Company");
        TextField phoneNumberText = new TextField();
        Label lblPhone = new Label("Phone Number");
        TextField streetText = new TextField();
        Label lblStreet = new Label("Street Address");
        TextField cityText = new TextField();
        Label lblCity = new Label("City");
        TextField zipText = new TextField();
        Label lblZip = new Label("Zip");
        TextField stateText = new TextField();
        Label lblState = new Label("State");
        TextField countryText = new TextField();
        Label lblCountry = new Label("Country");
        vBox2.getChildren().addAll(lblCustomerName, customerNameText, 
                lblPhone, phoneNumberText, lblStreet, streetText, lblCity,
                cityText, lblZip, zipText, lblState, stateText, lblCountry,
                countryText);
        
        // Set HBox for Insert Customer TabPane
        Button submitCustomerButton = new Button("Submit");
        Button clearCustomerButton = new Button("Clear");
        hBox2.getChildren().addAll(submitCustomerButton, clearCustomerButton);
       
        //Set BorderPane for Customer Tab 3
        borderPane3.setCenter(vBox2);
        borderPane3.setBottom(hBox2);
        
        //set columns in tableview
        TableColumn<Purchase, Integer> orderIdColumn = 
                new TableColumn<>("Order ID");
        TableColumn<Purchase, String> customerNameColumn = 
                new TableColumn<>("Customer Name");
        TableColumn<Purchase, String> partNameColumn = new 
        TableColumn<>("Part Name");
        TableColumn<Purchase, Integer> quantityColumn = new 
        TableColumn<>("Quantity");
        TableColumn<Purchase, LocalDate> dateColumn = new 
        TableColumn<>("Purchase Date");
        
        //Set column sizes for use in Tab3
        orderIdColumn.setPrefWidth(100);
        customerNameColumn.setPrefWidth(150);
        partNameColumn.setPrefWidth(150);
        quantityColumn.setPrefWidth(100);
        dateColumn.setPrefWidth(150);

        // Bind Columns to the Purchase Model Class
        orderIdColumn.setCellValueFactory(p -> 
                p.getValue().orderIdProperty().asObject());
        customerNameColumn.setCellValueFactory(p -> 
                p.getValue().customerNameProperty());
        partNameColumn.setCellValueFactory(p -> 
                p.getValue().partNameProperty());
        quantityColumn.setCellValueFactory(p -> 
                p.getValue().quantityProperty().asObject());
        dateColumn.setCellValueFactory(p -> 
                p.getValue().purchaseDateProperty());

        // Add Columns to TableView
        tableView.getColumns().addAll(orderIdColumn, customerNameColumn, 
                partNameColumn, quantityColumn, dateColumn);
        
        //Tableview Hbox
        Button getOpenOrders = new Button("Open Orders");
        hBox3.getChildren().addAll(getOpenOrders);


      getOpenOrders.setOnAction(e -> {
            ObservableList<Purchase> purchases = FXCollections.observableArrayList();
            String sql = "SELECT p.purchase_id, cus.customer_name, lp.part_name, " 
                    + "p.quantity, p.purchase_date " 
                    + "FROM dbo.Purchasing AS p " 
                    + "JOIN dbo.Customer AS cus ON p.customer_id = cus.customer_id " 
                    + "JOIN dbo.LUPartName AS lp ON p.part_number = lp.part_number;";

            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int purchaseId = rs.getInt("purchase_id");
                    String customerName = rs.getString("customer_name");
                    String partName = rs.getString("part_name");
                    int quantity = rs.getInt("quantity");
                    LocalDate purchaseDate = rs.getDate("purchase_date").toLocalDate();

                    Purchase purchase = new Purchase(purchaseId, customerName, partName, quantity, purchaseDate);
                    purchases.add(purchase);
                    tableView.setItems(purchases);
                }
            } catch (SQLException event) {
                event.printStackTrace();
            }
        });
      
     // tableView.setItems(purchases);

        // Set BorderPane for Tab 3
        borderPaneTable.setCenter(tableView);
        borderPaneTable.setBottom(hBox3);
        
        //Set tabs for tabPane
        Tab tab = new Tab();
        Tab tab2 = new Tab();
        Tab tab3 = new Tab();
        Tab tab4 = new Tab();
        
        //Set Tab text
        tab.setText("Purchase");
        tab2.setText("Insert Customer");
        tab3.setText("Open Purchase Orders");
        
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
        
        borderPane.setCenter(tabPane);
        
        submitCustomerButton.setOnAction(e -> {
            String name = customerNameText.getText();
            String phoneNumber = phoneNumberText.getText();
            String streetAddress = streetText.getText();
            String city = cityText.getText();
            String state = stateText.getText();
            String zipCode = zipText.getText();
            String country = countryText.getText();
            //Error handling for all inputs
            if (name == null || name.trim().isEmpty()) {
                Warnings.emptyCustomerName();
                return;
            }

            if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
                Warnings.emptyPhoneNumber();
                return;
            }

            if (streetAddress == null || streetAddress.trim().isEmpty()) {
                Warnings.emptyStreetAddress();
                return;
            }

            if (city == null || city.trim().isEmpty()) {
                Warnings.emptyCity();
                return;
            }

            if (state == null || state.trim().isEmpty()) {
                Warnings.emptyState();
                return;
            }

            if (country == null || country.trim().isEmpty()) {
                Warnings.emptyCountry();
                return;
            }
            
            if (zipCode == null || zipCode.trim().isEmpty()) {
                Warnings.emptyCountry();
                return;
            }
            //Validate if the phone number is numbers and the correc length
            if (!Validation.isValidPhoneNumber(phoneNumber)) {
                Warnings.incorrectPhoneNumber(); 
                return;
            }

            if (!Validation.isValidZipCode(zipCode)) {
                Warnings.incorrectZipCode(); 
                return;
            }            
            
            
            try(Connection conn = DatabaseConnection.getConnection())
                {
                    CallableStatement stmt = conn.prepareCall("{call "
                            + "InsertCustomer(?, ?, ?, ?, ?, ?, ?)}");
                    stmt.setString(1, name);  // Set input username
                    stmt.setString(2, phoneNumber);  // Set input password
                    stmt.setString(3, streetAddress);
                    stmt.setString(4, city);
                    stmt.setString(5, state);
                    stmt.setString(6, country);
                    stmt.setString(7, zipCode);

                    // Execute the stored procedure
                    stmt.execute();


                } catch (SQLException event) {
                    event.printStackTrace();
                }
        });
        
        submitPurchase.setOnAction(e -> {
            //Add error handling in case the value isn't an int
            int quantity;
            String customer = customerText.getText();
            LocalDate date = PurchaseDate.getValue();
            String part;
            int user = 1;
            
            //First test if int quantity has a value. If not throw an error.
            String quantityInput = quanityText.getText();
            if (quantityInput == null || quantityInput.trim().isEmpty()) {
                Warnings.emptyQuantity(); 
                return;
            } //Try to assign the 
            try {
                quantity = Integer.parseInt(quantityInput);
                if (quantity <= 0) {
                    Warnings.invalidQuantity(); 
                    return;
                }
            } catch (NumberFormatException event) {
                Warnings.invalidQuantity(); 
                return;
            }

            // Customer if empty throw an error
            if (customer == null || customer.trim().isEmpty()) {
                Warnings.emptyCustomer(); 
                return;
            }

            // Purchase Date
            if (date == null) {
                Warnings.emptyPurchaseDate(); 
                return;
            }

            // Part selection error handling. IF empty
            if (partPicker.getValue() == null) {
                Warnings.emptyPart(); 
                return;
            }
            //If I convert this to a string off the bat. IT will always throw
            // an error
            part = partPicker.getValue().toString();    
            
            try(Connection conn = DatabaseConnection.getConnection())
                {
                    CallableStatement stmt = conn.prepareCall("{call InsertPurchase("
                            + "?, ?, ?, ?, ?)}");
                    stmt.setInt(1, user);
                    stmt.setInt(2, quantity);  // Set input username
                    stmt.setDate(3, java.sql.Date.valueOf(date));  // Set input password
                    stmt.setString(4, part);
                    stmt.setString(5, customer);


                    // Execute the stored procedure
                    stmt.execute();


                } catch (SQLException event) {
                    event.printStackTrace();
                }
                
        });
        
        return borderPane;
}
    public void showOpenOrders(){
        ObservableList<Purchase> purchases = FXCollections.observableArrayList();
            String sql = "SELECT p.purchase_id, cus.customer_name, lp.part_name, " 
                    + "p.quantity, p.purchase_date " 
                    + "FROM dbo.Purchasing AS p " 
                    + "JOIN dbo.Customer AS cus ON p.customer_id = cus.customer_id " 
                    + "JOIN dbo.LUPartName AS lp ON p.part_number = lp.part_number;";

            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int purchaseId = rs.getInt("purchase_id");
                    String customerName = rs.getString("customer_name");
                    String partName = rs.getString("part_name");
                    int quantity = rs.getInt("quantity");
                    LocalDate purchaseDate = rs.getDate("purchase_date").toLocalDate();

                    Purchase purchase = new Purchase(purchaseId, customerName, 
                            partName, quantity, purchaseDate);
                    purchases.add(purchase);
                    tableView.setItems(purchases);
                }
            } catch (SQLException event) {
                event.printStackTrace();
            }
    }
    
} //End Subclass Purchasing