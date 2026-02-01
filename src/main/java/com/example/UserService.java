package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserService {

    private final String password;

    public UserService(String password) {
        this.password = password;
    }

    public void findUser(String username) throws Exception {

        String query = "SELECT * FROM users WHERE name = ?";

        try (Connection conn =
                     DriverManager.getConnection("jdbc:mysql://localhost/db",
                             "root", password);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.executeQuery();
        }
    }

    public void deleteUser(String username) throws Exception {

        String query = "DELETE FROM users WHERE name = ?";

        try (Connection conn =
                     DriverManager.getConnection("jdbc:mysql://localhost/db",
                             "root", password);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.executeUpdate();
        }
    }
}
