package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {

    private final String password;

    public UserService(String password) {
        this.password = password;
    }

    public void findUser(String username) throws UserServiceException {

        String query = "SELECT * FROM users WHERE name = ?";

        try (Connection conn =
                     DriverManager.getConnection("jdbc:mysql://localhost/db",
                             "root", password);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.executeQuery();
        } catch (SQLException e) {
            throw new UserServiceException("Failed to find user: " + username, e);
        }
    }

    public void deleteUser(String username) throws UserServiceException {

        String query = "DELETE FROM users WHERE name = ?";

        try (Connection conn =
                     DriverManager.getConnection("jdbc:mysql://localhost/db",
                             "root", password);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new UserServiceException("Failed to delete user: " + username, e);
        }
    }
}