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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.TableColumn;
import java.time.LocalDate;
import java.util.function.Supplier;
import static javafx.application.Application.STYLESHEET_CASPIAN;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//Begin Subclass Purchasing
public class Purchasing extends ScreenController {
    
    public Supplier<Pane> getView(){
        return this::buildPurchasingView;
    }
    
    private BorderPane borderPane;
    private BorderPane borderPane2;
    private BorderPane borderPane3;
    private BorderPane borderPaneTable;
    private BorderPane borderPaneClosed;
    private TabPane tabPane;
    private VBox vBox;
    private VBox vBox2;
    private HBox hBox1;
    private HBox hBox2;
    private HBox hBox3;
    private HBox hBox4;
    private HBox customerHBox;
    private HBox purchaseHBox;
    private TableView<Purchase> tableView;
    private TableView<Purchase> closedTableView;
    private GridPane grid;
    private VBox titleVBox;
    private VBox purchaseVBox;
    private ComboBox<String> myCustomerCombo;
    
    
    private BorderPane buildPurchasingView(){
    //public Purchasing(){
        //Set Panes
        borderPane = new BorderPane();
        borderPane2 = new BorderPane();
        borderPane3 = new BorderPane();
        borderPaneTable = new BorderPane();
        borderPaneClosed = new BorderPane();
        tabPane = new TabPane();
        vBox = new VBox();
        vBox2 = new VBox();
        hBox1 = new HBox();
        hBox2 = new HBox();
        hBox3 = new HBox();
        hBox4 = new HBox();
        customerHBox = new HBox();
        purchaseHBox = new HBox();
        tableView = new TableView<>();
        closedTableView = new TableView<>();
        titleVBox = new VBox();
        purchaseVBox = new VBox();
        myCustomerCombo = new ComboBox<>();
        
//Title VBox ------------------------------------------------------------------
       //Screen
       Text woTitle = new Text("Purchasing Management");
       woTitle.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD,
                FontPosture.REGULAR, 25));
       //Title VBOX
       titleVBox.setAlignment(Pos.CENTER);
       titleVBox.getChildren().addAll(woTitle);
//Tab 1 Begin-------------------------------------------------------------------
        
        //BorderPane3 Top
        Text purchaseInstruction = new Text("Please enter the customer's "
                + "purchase in the fields below.");
        purchaseInstruction.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD,
                FontPosture.REGULAR, 15));
        purchaseHBox.setPadding(new Insets(10));
        purchaseHBox.setAlignment(Pos.CENTER);
        purchaseHBox.getChildren().addAll(purchaseInstruction);

        // Set HBox for TabPane1
        Button submitPurchase = new Button("Submit Order");
        Button clearButton = new Button("Clear All Fields");
        hBox1.setAlignment(Pos.CENTER_RIGHT);
        hBox1.setSpacing(10);
        hBox1.getChildren().addAll(submitPurchase, clearButton);
        //Combo Box options
        ObservableList<String> options = FXCollections.observableArrayList(
            "Widget",
            "Fidget",
            "Gadget",
            "Module",
            "Tool");
        // Create a GridPane
        purchaseVBox.setPadding(new Insets(10, 0, 0, 0));
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); 
        gridPane.setVgap(10); 
        gridPane.setAlignment(Pos.CENTER); 
        purchaseVBox.getChildren().add(gridPane);
        
        
        //Set vBox for TabPan1
        vBox.setAlignment(Pos.CENTER);
        TextField quantityText = new TextField();
        Label lblQuantity = new Label("Quanity Purchased");
        TextField customerText = new TextField();
        Label lblCustomer = new Label("Customer");
        DatePicker PurchaseDate = new DatePicker();
        Label lblDate = new Label("Purchase Date");
        ComboBox partPicker = new ComboBox(options);
        Label lblPart = new Label("Part Nomencalture");
        myCustomerCombo.setItems(populateCustomer(myCustomerCombo));
        
        // Adding elements to the GridPane
        gridPane.add(lblQuantity, 0, 0); 
        gridPane.add(quantityText, 1, 0); 
        gridPane.add(lblCustomer, 0, 1); 
        gridPane.add(myCustomerCombo, 1, 1); 
        gridPane.add(lblDate, 0, 2); 
        gridPane.add(PurchaseDate, 1, 2); 
        gridPane.add(lblPart, 0, 3); 
        gridPane.add(partPicker, 1, 3); 
        gridPane.add(hBox1, 1, 4);
        //gridPane.add(myCustomerCombo, 2, 5);

        //Set BorderPane2 for TabPane1
        borderPane2.setCenter(purchaseVBox);
        borderPane2.setTop(purchaseHBox);
        //borderPane2.setBottom(hBox1);
        
        // Set GridPane for Customer 
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
        ComboBox<String> stateComboBox = new ComboBox<>();
        stateComboBox.getItems().addAll(
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", 
                "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", 
                "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa","Kansas", 
                "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", 
                "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", 
                "Nebraska", "Nevada","New Hampshire", "New Jersey", 
                "New Mexico", "New York", "North Carolina", "North Dakota", 
                "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
                "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", 
                "Vermont", "Virginia", "Washington", "West Virginia", 
                "Wisconsin", "Wyoming"
        );
        Label lblState = new Label("State");
        TextField countryText = new TextField();
        Label lblCountry = new Label("Country");
        
        // Set HBox for Insert Customer TabPane
        Button submitCustomerButton = new Button("Submit");
        Button clearCustomerButton = new Button("Clear");
        hBox2.setAlignment(Pos.CENTER_RIGHT);
        hBox2.setSpacing(10);
        hBox2.getChildren().addAll(submitCustomerButton, clearCustomerButton);        
        
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        // Row 0 - Customer Name
        grid.add(lblCustomerName, 0, 0);
        grid.add(customerNameText, 1, 0, 3, 1); // Span across 3 columns

        // Row 1 - Phone Number
        grid.add(lblPhone, 0, 1);
        grid.add(phoneNumberText, 1, 1);

        // Row 2 - Street Address
        grid.add(lblStreet, 0, 2);
        grid.add(streetText, 1, 2, 3, 1); // Span across 3 columns

        // Row 3 - City and Zip
        grid.add(lblCity, 0, 3);
        grid.add(cityText, 1, 3);
        grid.add(lblZip, 2, 3);
        grid.add(zipText, 3, 3);

        // Row 4 - State and Country
        grid.add(lblState, 0, 4);
        grid.add(stateComboBox, 1, 4);
        grid.add(lblCountry, 2, 4);
        grid.add(countryText, 3, 4);
        
        //Row 5 - Buttons
        grid.add(hBox2, 0, 5, 4, 1);
        
        // Optional: Set column widths (if desired)
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(120);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setMinWidth(60);

        ColumnConstraints col4 = new ColumnConstraints();
        col4.setHgrow(Priority.ALWAYS);

        grid.getColumnConstraints().addAll(col1, col2, col3, col4);
        
        
        //BorderPane3 Top
        Text customerInstruction = new Text("Please enter the customer's "
                + "information in the fields below.");
        customerInstruction.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD,
                FontPosture.REGULAR, 15));
        customerHBox.setPadding(new Insets(10));
        customerHBox.setAlignment(Pos.CENTER);
        customerHBox.getChildren().addAll(customerInstruction);
       
        //Set BorderPane for Customer Tab 3
        borderPane3.setCenter(grid);
        borderPane3.setTop(customerHBox);
        
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
        TableColumn<Purchase, String> statusColumn = new 
        TableColumn<>("Status");
        
        //Set column sizes for use in Tab3
        orderIdColumn.setPrefWidth(100);
        customerNameColumn.setPrefWidth(150);
        partNameColumn.setPrefWidth(150);
        quantityColumn.setPrefWidth(100);
        dateColumn.setPrefWidth(150);
        statusColumn.setPrefWidth(150);

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
        statusColumn.setCellValueFactory(p ->
                p.getValue().statusProperty());

        // Add Columns to TableView
        tableView.getColumns().addAll(orderIdColumn, customerNameColumn, 
                partNameColumn, quantityColumn, dateColumn, statusColumn);
        
        //Tableview Hbox
        Button getOpenOrders = new Button("Open Orders");
        hBox3.getChildren().addAll(getOpenOrders);
        hBox3.setPadding(new Insets(5, 5, 5, 5));
        
//Tab4 Closed Purchase Orders--------------------------------------------------
        //set columns in tableview
        TableColumn<Purchase, Integer> orderIdColumnC = 
                new TableColumn<>("Order ID");
        TableColumn<Purchase, String> customerNameColumnC = 
                new TableColumn<>("Customer Name");
        TableColumn<Purchase, String> partNameColumnC = new 
        TableColumn<>("Part Name");
        TableColumn<Purchase, Integer> quantityColumnC = new 
        TableColumn<>("Quantity");
        TableColumn<Purchase, LocalDate> dateColumnC = new 
        TableColumn<>("Purchase Date");
        TableColumn<Purchase, String> statusColumnC = new 
        TableColumn<>("Status");
        
        //Set column sizes for use in Tab3
        orderIdColumnC.setPrefWidth(100);
        customerNameColumnC.setPrefWidth(150);
        partNameColumnC.setPrefWidth(150);
        quantityColumnC.setPrefWidth(100);
        dateColumnC.setPrefWidth(150);
        statusColumnC.setPrefWidth(150);

        // Bind Columns to the Purchase Model Class
        orderIdColumnC.setCellValueFactory(p -> 
                p.getValue().orderIdProperty().asObject());
        customerNameColumnC.setCellValueFactory(p -> 
                p.getValue().customerNameProperty());
        partNameColumnC.setCellValueFactory(p -> 
                p.getValue().partNameProperty());
        quantityColumnC.setCellValueFactory(p -> 
                p.getValue().quantityProperty().asObject());
        dateColumnC.setCellValueFactory(p -> 
                p.getValue().purchaseDateProperty());
        statusColumnC.setCellValueFactory(p ->
                p.getValue().statusProperty());

        // Add Columns to TableView
        closedTableView.getColumns().addAll(orderIdColumnC, customerNameColumnC, 
                partNameColumnC, quantityColumnC, dateColumnC, statusColumnC);
        
        //Tableview Hbox
        Button getClosedOrders = new Button("Show Orders");
        hBox4.getChildren().addAll(getClosedOrders);  
        hBox4.setPadding(new Insets(5, 5, 5, 5));


      getOpenOrders.setOnAction(e -> {
            ObservableList<Purchase> purchases = FXCollections.observableArrayList();
            String sql = "SELECT p.purchase_id, cus.customer_name, lp.part_name, " 
                    + "p.quantity, p.purchase_date, ls.status_desc " 
                    + "FROM dbo.Purchasing AS p " 
                    + "JOIN dbo.Customer AS cus ON p.customer_id = cus.customer_id " 
                    + "JOIN dbo.LUPartName AS lp ON p.part_number = lp.part_number "
                    + "JOIN dbo.Product_LU_Status as ls ON p.status_id = ls.status_id "
                    + "WHERE ls.status_desc != 'Received by Customer'; ";

            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int purchaseId = rs.getInt("purchase_id");
                    String customerName = rs.getString("customer_name");
                    String partName = rs.getString("part_name");
                    int quantity = rs.getInt("quantity");
                    LocalDate purchaseDate = rs.getDate("purchase_date").toLocalDate();
                    String status = rs.getString("status_desc");

                    Purchase purchase = new Purchase(purchaseId, customerName, 
                            partName, quantity, purchaseDate, status);
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
        
        //Set BorderPane for Tab 4
        borderPaneClosed.setCenter(closedTableView);
        borderPaneClosed.setBottom(hBox4);
        
        //Set tabs for tabPane
        Tab tab = new Tab();
        Tab tab2 = new Tab();
        Tab tab3 = new Tab();
        Tab tab4 = new Tab();
        
        //Set Tab text
        tab.setText("Purchase");
        tab2.setText("Insert Customer");
        tab3.setText("Open Purchase Orders");
        tab4.setText("Closed Purchased Orders");
        
        //Remove the ability to close tabs
        tab.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        tab4.setClosable(false);

        
        //Set the conent of the tabs
        tab.setContent(borderPane2);
        tab2.setContent(borderPane3);
        tab3.setContent(borderPaneTable);
        tab4.setContent(borderPaneClosed);
        
        
        
        //Add all tabs to tabPane
        tabPane.getTabs().addAll(tab, tab2, tab3, tab4);
        
        borderPane.setCenter(tabPane);
        borderPane.setTop(titleVBox);
        
        submitCustomerButton.setOnAction(e -> {
            if(stateComboBox.getValue() == null){
                Warnings.emptyState();
                return;
            }
            String name = customerNameText.getText();
            String phoneNumber = phoneNumberText.getText();
            String streetAddress = streetText.getText();
            String city = cityText.getText();
            String state = stateComboBox.getValue().toString();
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
                            + "InsertCustomer(?, ?, ?, ?, ?, ?, ?, ?)}");
                    stmt.setString(1, name);  // Set input username
                    stmt.setString(2, phoneNumber);  // Set input password
                    stmt.setString(3, streetAddress);
                    stmt.setString(4, city);
                    stmt.setString(5, state);
                    stmt.setString(6, country);
                    stmt.setString(7, zipCode);
                    stmt.registerOutParameter(8, java.sql.Types.INTEGER);

                    // Execute the stored procedure
                    stmt.execute();
                    
                    int result = stmt.getInt(8);
                    
                    if(result == 1){
                        customerNameText.clear();
                        phoneNumberText.clear();
                        streetText.clear();
                        cityText.clear();
                        zipText.clear();
                        stateComboBox.getSelectionModel().clearSelection();
                        countryText.clear();
                        Warnings.showAddCustomer();
                        myCustomerCombo.setItems(populateCustomer
                                                            (myCustomerCombo));
                    }

                } catch (SQLException event) {
                    event.printStackTrace();
                }

        });
        
        clearCustomerButton.setOnAction(e -> {
            customerNameText.clear();
            phoneNumberText.clear();
            streetText.clear();
            cityText.clear();
            zipText.clear();
            stateComboBox.getSelectionModel().clearSelection();
            countryText.clear();
        });
        
        submitPurchase.setOnAction(e -> {
            //Add error handling in case the value isn't an int
            int quantity;
            if(myCustomerCombo == null){
                Warnings.emptyCustomer(); 
                return;
            }
            
            String customer = myCustomerCombo.getValue().toString();
            LocalDate date = PurchaseDate.getValue();
            String part;
            int user = 1;
            
            //First test if int quantity has a value. If not throw an error.
            String quantityInput = quantityText.getText();
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
                    //Passing part name instead of partnumber
                    //The part number will be determined in the stored procedure
                    stmt.setString(4, part);
                    stmt.setString(5, customer);


                    // Execute the stored procedure
                    stmt.execute();


                } catch (SQLException event) {
                    event.printStackTrace();
                }
            quantityText.clear();       
            customerText.clear();
            PurchaseDate.setValue(null);
            partPicker.getSelectionModel().clearSelection();
            myCustomerCombo.getSelectionModel().clearSelection();
            Warnings.showCompletePurchase();
        });
        
        clearButton.setOnAction(e -> {
            quantityText.clear();       
            customerText.clear();
            PurchaseDate.setValue(null);
            partPicker.getSelectionModel().clearSelection();
            myCustomerCombo.getSelectionModel().clearSelection();
        });
        
        getClosedOrders.setOnAction(e -> showClosedOrders());
        
        return borderPane;
        
        
}

    public void showOpenOrders(){
        ObservableList<Purchase> purchases = FXCollections.observableArrayList();
            String sql = "SELECT p.purchase_id, cus.customer_name, lp.part_name, " 
                    + "p.quantity, p.purchase_date, ls.status_desc " 
                    + "FROM dbo.Purchasing AS p " 
                    + "JOIN dbo.Customer AS cus ON p.customer_id = cus.customer_id " 
                    + "JOIN dbo.LUPartName AS lp ON p.part_number = lp.part_number"
                    + "JOIN dbo.Product_LU_Status as ls ON p.status_id = ls.status_id"
                    + "WHERE ls.status_desc != 'Received by Customer';";

            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int purchaseId = rs.getInt("purchase_id");
                    String customerName = rs.getString("customer_name");
                    String partName = rs.getString("part_name");
                    int quantity = rs.getInt("quantity");
                    LocalDate purchaseDate = rs.getDate("purchase_date").toLocalDate();
                    String status = rs.getString("status_desc");

                    Purchase purchase = new Purchase(purchaseId, customerName, 
                            partName, quantity, purchaseDate, status);
                    purchases.add(purchase);
                    tableView.setItems(purchases);
                }
            } catch (SQLException event) {
                event.printStackTrace();
            }
    }
    
    public void showClosedOrders(){
        ObservableList<Purchase> purchases = FXCollections.observableArrayList();
            String sql = "SELECT p.purchase_id, cus.customer_name, lp.part_name, " 
                    + "p.quantity, p.purchase_date, ls.status_desc " 
                    + "FROM dbo.Purchasing AS p " 
                    + "JOIN dbo.Customer AS cus ON p.customer_id = cus.customer_id " 
                    + "JOIN dbo.LUPartName AS lp ON p.part_number = lp.part_number "
                    + "JOIN dbo.Product_LU_Status as ls ON p.status_id = ls.status_id "
                    + "WHERE ls.status_desc = 'Received by Customer';";

            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int purchaseId = rs.getInt("purchase_id");
                    String customerName = rs.getString("customer_name");
                    String partName = rs.getString("part_name");
                    int quantity = rs.getInt("quantity");
                    LocalDate purchaseDate = rs.getDate("purchase_date").toLocalDate();
                    String status = rs.getString("status_desc");

                    Purchase purchase = new Purchase(purchaseId, customerName, 
                            partName, quantity, purchaseDate, status);
                    purchases.add(purchase);
                    closedTableView.setItems(purchases);
                }
            } catch (SQLException event) {
                event.printStackTrace();
            }
    }
    
    public ObservableList<String> populateCustomer(ComboBox<String> comboBox){
        
        ObservableList<String> items = FXCollections.observableArrayList();
        String query = "SELECT customer_name FROM dbo.Customer";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
                
            ResultSet rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                String customer = rs.getString("customer_name"); // Fetch data
                items.add(customer); 
            }
        }catch (SQLException e) {
                    e.printStackTrace();
                }
        return items;
    }    
    
    
    
} //End Subclass Purchasing