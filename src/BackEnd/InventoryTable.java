package BackEnd;


//Imports
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
//Begin Subclass InventoryTable
public class InventoryTable {
    private IntegerProperty itemID;
    private StringProperty partName;   
    private IntegerProperty quantity;
    private StringProperty assignedTo;
    private ObjectProperty<LocalDate> purchaseDate;
    private StringProperty status;
    private StringProperty location;
    
    //Itemid, partnumber, quantity,status_id, purchaseID
    public InventoryTable(int itemID, String partName, int quantity){
        this.itemID = new SimpleIntegerProperty(itemID);
        this.partName = new SimpleStringProperty(partName);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

public InventoryTable(int itemID, String partName, int quantity, 
        String location){
        this.itemID = new SimpleIntegerProperty(itemID);
        this.partName = new SimpleStringProperty(partName);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.location = new SimpleStringProperty(location);
    }
    
    //Getter methods for returning 
    public IntegerProperty itemIdProperty() { return itemID; }
    public StringProperty techNameProperty() { return assignedTo; }
    public StringProperty partNameProperty() { return partName; }
    public IntegerProperty quantityProperty() { return quantity; }
    public ObjectProperty<LocalDate> purchaseDateProperty() { 
        return purchaseDate; }
    public StringProperty statusProperty(){ return status; } 
    public StringProperty locationProperty(){ return location; }
} //End Subclass InventoryTable