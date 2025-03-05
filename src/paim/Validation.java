package paim;

//Imports

//Begin Subclass Validation
public class Validation {
    
    // Check if a string is not empty or null
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    // Validate email format
    // Letters, numbers, + - _ . before @ sign. Followed by 
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // Validate password at least 8 characters, one number, one uppercase letter)
    public static boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

} //End Subclass Validation