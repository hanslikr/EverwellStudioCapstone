/*
 * Sources and guides utilized:
 * https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
 * https://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-connect-drivermanager.html
 * https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
 */

package com.everwell.database;

// java classes needed to make/manage database connection.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // URL to identify MySQL server, port, and database.
    private static final String URL = "jdbc:mysql://localhost:3307/everwell_wellness";

    // MySQL username for accessing the database.
    private static final String USER = "root";

    // Password is stored as an environment variable instead of being written
    // directly into the source code.
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    // creates the connection and available to other classes.
    // SQLException is thrown if the connection is not successful.
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}