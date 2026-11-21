
package com.everwell.database;

// Import the classes needed to work with the database connection.
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {

    public static void main(String[] args) {

        // Try to connect to the Everwell database.
        // The connection automatically closes when the test is finished.
        try (Connection connection = DatabaseConnection.getConnection()) {

            // If the connection works, display a confirmation message.
            if (connection != null && !connection.isClosed()) {
                System.out.println("Successfully connected to Everwell Wellness database!");
            }

        } catch (SQLException e) {

            // If something goes wrong, display the error.
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
    }
}
