package main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthentication {
    // Validate user credentials and fetch user details
    public static User authenticateUser(String username, String password) {

        // Assume you have a DatabaseConnection class with a getConnection method
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Prepare the SQL statement for login validation
            String sql = "SELECT * FROM UserAccounts WHERE username=? AND password=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                // Execute the SQL statement
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // User found, fetch details
                        String fetchedUsername = resultSet.getString("username");

                        // Create and return a User object
                        return new User(fetchedUsername);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Return null if user not found or an error occurred
        return null;
    }
}
