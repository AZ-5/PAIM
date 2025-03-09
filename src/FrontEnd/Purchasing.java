package FrontEnd;


//Imports
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
import java.time.LocalDate;
import javafx.scene.control.TableColumn;

//Begin Subclass Purchasing
public class Purchasing extends ScreenController {

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
    
    
    public Purchasing(){
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
        
        //Combo Box options
        ObservableList<String> options = FXCollections.observableArrayList(
            "Widget",
            "Fidget",
            "Toy");
        
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
        Label lblZip = new Label("City");
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

        // Add Some Dummy Data
        ObservableList<Purchase> purchases = FXCollections.observableArrayList(
                new Purchase(1, "John Doe", "Widget", 5, LocalDate.now()),
                new Purchase(2, "Alice Smith", "Fidget", 10, LocalDate.now()),
                new Purchase(3, "Robert Green", "Toy", 15, LocalDate.now())
        );
        tableView.setItems(purchases);

        // Set BorderPane for Tab 3
        borderPaneTable.setCenter(tableView);
        
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
        
        
}

    public BorderPane getView(){
       return borderPane;
   }  
} //End Subclass Purchasing