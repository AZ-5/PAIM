package BackEnd;


//Imports
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//Begin Subclass WorkorderTable
public class WorkorderTable {
    private IntegerProperty orderId;
    private StringProperty partName;   
    private IntegerProperty quantity;
    private StringProperty assignedTo;
    private ObjectProperty<LocalDate> purchaseDate;
    
    public WorkorderTable(int orderId,  String partName, 
       int quantity){
        this.orderId = new SimpleIntegerProperty(orderId);
        //this.assignedTo = new SimpleStringProperty(assignedTo);
        this.partName = new SimpleStringProperty(partName);
        this.quantity = new SimpleIntegerProperty(quantity);
        //this.purchaseDate = new SimpleObjectProperty<>(purchaseDate);
    }
    
    public IntegerProperty orderIdProperty() { return orderId; }
    public StringProperty techNameProperty() { return assignedTo; }
    public StringProperty partNameProperty() { return partName; }
    public IntegerProperty quantityProperty() { return quantity; }
    public ObjectProperty<LocalDate> purchaseDateProperty() { 
        return purchaseDate; }
} //End Subclass WorkorderTable