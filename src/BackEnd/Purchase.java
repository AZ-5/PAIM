package BackEnd;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


//Imports

//Begin Subclass Purchase
public class Purchase {
    private IntegerProperty orderId;
    private StringProperty customerName;
    private StringProperty partName;
    private IntegerProperty quantity;
    private ObjectProperty<LocalDate> purchaseDate;
    private StringProperty status;
    
    public Purchase(int orderId, String customerName, String partName, 
       int quantity, LocalDate purchaseDate, String status){
        this.orderId = new SimpleIntegerProperty(orderId);
        this.customerName = new SimpleStringProperty(customerName);
        this.partName = new SimpleStringProperty(partName);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.purchaseDate = new SimpleObjectProperty<>(purchaseDate);
        this.status = new SimpleStringProperty(status);
    }
    
    public IntegerProperty orderIdProperty() { return orderId; }
    public StringProperty customerNameProperty() { return customerName; }
    public StringProperty partNameProperty() { return partName; }
    public IntegerProperty quantityProperty() { return quantity; }
    public ObjectProperty<LocalDate> purchaseDateProperty() { 
        return purchaseDate; }
    public StringProperty statusProperty() { return status; }
            
} //End Subclass Purchase