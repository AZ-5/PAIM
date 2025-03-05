package paim;

//Imports

import javafx.scene.control.Alert;


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
        public static void emptyKey(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Name field is empty");
            alert.setContentText("Please input data first");
            alert.showAndWait();        
        }
} //End Subclass Warnings