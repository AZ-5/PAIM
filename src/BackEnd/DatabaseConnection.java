package BackEnd;

//Imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://JONDESKTOP:1433;databaseName=PAIM;encrypt=false";
    private static final String USER = "java_test";
    private static final String PASSWORD = "1234";

    // Private constructor to prevent instantiation
    private DatabaseConnection() {}

    // Method to get a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}