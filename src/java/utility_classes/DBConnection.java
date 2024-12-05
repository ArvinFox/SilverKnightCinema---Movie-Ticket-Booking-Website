package utility_classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/silverknightcinema_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345";
    
    private DBConnection() {}
    
    public static Connection getConnection() {
        Connection connection = null;
        
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");  // Load MySQL JDBC Driver
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Database connection established successfully.");
                
            } catch (ClassNotFoundException e) {
                System.err.println("MySQL JDBC Driver not found.");
                e.printStackTrace();
                
            } catch (SQLException e) {
                System.err.println("Failed to establish database connection.");
                e.printStackTrace();
            }
        }
        
        return connection;
    }
    
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to close database connection.");
            e.printStackTrace();
        }
    }
}
