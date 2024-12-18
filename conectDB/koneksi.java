package conectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
    private static Connection connection;
 
    public static void config() {
        try {

            String url = "jdbc:mysql://localhost:3306/morse";
            String username = "root";
            String password = "sheiapr204";

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            System.err.println("Database connection failed.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            System.err.println("Connection is not initialized. Call initialize() first.");
        }
        return connection;
    }

    // Method to close the connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close the connection.");
                e.printStackTrace();
            }
        }
    }
}
