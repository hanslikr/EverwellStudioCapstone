
/*
 * Sources and guides utilized:
 * Oracle JDBC Tutorial:
 * https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
 *
 * MySQL Connector/J Developer Guide:
 * https://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-connect-drivermanager.html
 *
 * Apache Maven Getting Started Guide:
 * https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
 */

package com.everwell.database;

// Java classes needed to make and manage the database connection.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // URL to identify the MySQL server, port, and database.
    private static final String URL = "jdbc:mysql://localhost:3307/everwell_wellness";

    // MySQL username used to access the database.
    private static final String USER = "root";

    // Password is stored as an environment variable instead of
    // being written directly into the source code.
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    // Creates the connection and makes it available to other classes.
    // SQLException is thrown if the connection cannot be established.
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
