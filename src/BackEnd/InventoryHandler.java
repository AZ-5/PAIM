package BackEnd;


//Imports
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
        
//Begin Subclass InventoryHandler
public class InventoryHandler {
    
    public void createInventory(int OrderID){
        int orderID = OrderID;
        
        //Prevents a workorder status from be changed to assigned without a 
        // a user from being assigned.
        if (orderID == 0){
           System.out.println("No order selected.");
            return;
        }

        try(Connection conn = DatabaseConnection.getConnection()){ 
            
            CallableStatement pstmt = conn.prepareCall("{call InsertInventory("
                            + "? )}");
            pstmt.setInt(1, orderID);  // Set input username
            // Execute the stored procedure
            pstmt.execute();
        }catch (SQLException e) {
                    e.printStackTrace();
                }
    }
    
    public void showUnStored(){
        
    }
    
    //Reusable fucntion. Receives combo box and status id number
    //Returns the order ids that match the status id
    public ObservableList<String> populateorderID(ComboBox<String> comboBox,
            int StatusId){
        
        int statusId = StatusId;
        ObservableList<String> items = FXCollections.observableArrayList();
        String query = "SELECT i.item_id " 
                    + "FROM dbo.Inventory AS i " 
                    + "WHERE i.status_id = ? ";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            
            stmt.setInt(1, statusId);    
            ResultSet rs = stmt.executeQuery(); 
            //ResultSet rs = stmt.executeQuery(query); 
            
            //Adds full_name to observableList as long as there are more
            //Items from the query to add. 
            while (rs.next()) {
                String itemID = rs.getString("item_id"); // Fetch data
                items.add(itemID); 
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
                + "WHERE u.role = 'Inventory' ";
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
    
    //Recieves user name. Calls the database and returns the user's ID
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
    
    //Receive itemID, userID, and location
    //Call Insert Location Inventory and pass the above variables.
    //This will update the location and status ID for inventory, purchasing,
    // and workorder table
    public void assignLocation(int ItemID, int userID, String Location,
            int StatusID){
        int itemID = ItemID;
        String location = Location;
        int statusID = StatusID;
        
        //Add better data validation
        if(itemID == 0 || location.equals(null)){
            System.out.println("No orderID or date");
            return;
        }
        try(Connection conn = DatabaseConnection.getConnection()){ 
            
            CallableStatement pstmt = conn.prepareCall("{call InsertLocationInventory("
                            + "?, ?, ?, ?)}");
            pstmt.setInt(1, ItemID);
            pstmt.setString(2, location);// Set input username
            pstmt.setInt(3, userID);
            pstmt.setInt(4, statusID);
            // Execute the stored procedure
            pstmt.execute();
        }catch (SQLException e) {
                    e.printStackTrace();
                }       
    }  
    


} //End Subclass InventoryHandler