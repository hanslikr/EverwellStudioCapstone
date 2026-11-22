// Class to register customers using their name, email, date of birth, and password.
// The password will be hashed before it is stored in the database.

package com.everwell.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerRegistration {

    // customer info from registration form here:
    public boolean registerCustomer(
            String firstName,
            String lastName,
            String email,
            LocalDate dateOfBirth,
            String password) throws SQLException, ClassNotFoundException {

        // insert a new customer into the database:
        String sql = "INSERT INTO customers "
                + "(first_name, last_name, email, date_of_birth, password_hash) "
                + "VALUES (?, ?, ?, ?, ?)";

        // connect to db and and prepare the SQL statement.
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            // Add the customer information to the SQL statement.
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setDate(4, java.sql.Date.valueOf(dateOfBirth));
            statement.setString(5, password);

            // execute SQL statement and check if customer got added as expected
            return statement.executeUpdate() == 1;

        }
    }
}