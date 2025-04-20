package BackEnd;

//Imports

import javafx.scene.control.Alert;

//This class will return warning screens for ALL of Paim
//Begin Subclass Warnings
public class Warnings {
    public static void notAnInt(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Not an Integer");
            alert.setContentText("Please try again.");
            alert.showAndWait();        
        }         

    public static void notString(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Not a String");
            alert.setContentText("Please try again.");
            alert.showAndWait();        
        }
            public static void emptySet(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("HashMap is empty");
            alert.setContentText("Please input data first");
            alert.showAndWait();        
        } 
        public static void emptyUsername(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Username is empty");
            alert.setContentText("Please input username first");
            alert.showAndWait();        
        } 
        public static void emptyPassword(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Password is empty");
            alert.setContentText("Please input password first");
            alert.showAndWait();        
        } 
        public static void emptyRole(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Role is empty");
            alert.setContentText("Please input role first");
            alert.showAndWait();        
        }
        public static void emptyFirstName(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("First name is empty");
            alert.setContentText("Please input first name first");
            alert.showAndWait();        
        }
        public static void emptyLastName(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Last name is empty");
            alert.setContentText("Please input last name first");
            alert.showAndWait();        
        }
        public static void emptyEmail(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Email is empty");
            alert.setContentText("Please input email first");
            alert.showAndWait();        
        }
        public static void incorrectEmail(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Email address is incorrect");
            alert.setContentText("Please enter a valid email address "
                    + "(e.g., user@example.com");
            alert.showAndWait();        
        }
        public static void incorrectPassword(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Password is incorrect");
            alert.setContentText("Password must be at least 8 characters long "
                    + "and include at least one uppercase letter "
                    + "and one number.");
            alert.showAndWait();        
        }
        public static void emptyCustomerName(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Customer name is empty");
            alert.setContentText("Please input a customer name first");
            alert.showAndWait();        
        }
        public static void emptyPhoneNumber(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Phone number is empty");
            alert.setContentText("Please input a phone number first");
            alert.showAndWait();        
        }
        public static void emptyStreetAddress() {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Street address is empty");
            alert.setContentText("Please input a street address.");
            alert.showAndWait();
        }

        public static void emptyCity() {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("City is empty");
            alert.setContentText("Please input a city.");
            alert.showAndWait();
        }

        public static void emptyState() {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("State is empty");
            alert.setContentText("Please input a state.");
            alert.showAndWait();
        }

        public static void emptyCountry() {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Country is empty");
            alert.setContentText("Please input a country.");
            alert.showAndWait();
        }

        public static void emptyZipCode() {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Zip code is empty");
            alert.setContentText("Please input a zip code.");
            alert.showAndWait();
        }
        public static void incorrectPhoneNumber() {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Phone Number");
            alert.setContentText("Phone number must be 10 digits and contain only numbers.");
            alert.showAndWait();
        }

        public static void incorrectZipCode() {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Zip Code");
            alert.setContentText("Zip code must be 5 digits and contain "
                    + "only numbers.");
            alert.showAndWait();
        }
        public static void emptyKey(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Name field is empty");
            alert.setContentText("Please input data first");
            alert.showAndWait();        
        }
        public static void locationError(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Incorrect location");
            alert.setContentText("All locations must be between 01 and 03");
            alert.showAndWait();        
        } 
        public static void loginError(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Loggin failed");
            alert.setContentText("Username and/or password incorrect");
            alert.showAndWait();        
        } 
        
        public static void emptyQuantity() {
            showAlert("Quantity is empty", "Please enter a quantity.");
        }
        
        public static void emptyItemID() {
            showAlert("ItemID is empty", "Please enter an Item ID.");
        }
        
        public static void invalidQuantity() {
            showAlert("Invalid quantity", "Please enter a valid positive "
                    + "number.");
        }

        public static void emptyCustomer() {
            showAlert("Customer is empty", "Please enter a customer name.");
        }

        public static void emptyPurchaseDate() {
            showAlert("Purchase date is empty", "Please select a "
                    + "purchase date.");
        }

        public static void emptyPart() {
            showAlert("Part is empty", "Please select a part.");
        }
        
        public static void emptyUser() {
            showAlert("User not selected", "Please select a user "
                    + "from the list.");
        }

        public static void emptyWorkOrder() {
            showAlert("Work order not selected", "Please select a "
                    + "work order.");
        }

        public static void invalidWorkOrder() {
            showAlert("Invalid work order ID", "Work order must be a number "
                    + "greater than 0.");
        }
        //Prints the alerts
        private static void showAlert(String header, String content) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
        }
        
        public static void showAlert(String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
        public static void showAddCustomer() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Customer successfully added");
            alert.showAndWait();
        }
        public static void showCompleteStorage() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Item shipped successful.");
            alert.showAndWait();
        }
        public static void showAssignedItem() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Item assigned succesful.");
            alert.showAndWait();
        }
        public static void showAssignedWO() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Work order assigned succesful.");
            alert.showAndWait();
        }
        public static void showBeginWO() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Work order begin succesful.");
            alert.showAndWait();
        }
        public static void showCompleteWO() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Work order completed succesful.");
            alert.showAndWait();
        }
        public static void showCompletePurchase() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Purchase recieved succesfully.");
            alert.showAndWait();
        }
        public static void showCompleteUser() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("User account succesfully created.");
            alert.showAndWait();
        }
       
} //End Subclass Warnings