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
} //End Subclass Warnings