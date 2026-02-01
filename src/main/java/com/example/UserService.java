package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    private final String password;

    public UserService(String password) {
        this.password = password;
    }

    public void findUser(String username) throws UserServiceException {
        if (username == null || username.isEmpty()) {
            throw new UserServiceException("Username cannot be null or empty");
        }

        String query = "SELECT id, name, email, created_at FROM users WHERE name = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Process user data if needed
                }
            }
        } catch (SQLException e) {
            throw new UserServiceException("Failed to find user: " + username, e);
        }
    }

    public void deleteUser(String username) throws UserServiceException {
        if (username == null || username.isEmpty()) {
            throw new UserServiceException("Username cannot be null or empty");
        }

        String query = "DELETE FROM users WHERE name = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new UserServiceException("Failed to delete user: " + username, e);
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/db", "root", password);
    }
}