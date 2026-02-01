package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

class UserServiceTest {

    private main.java.com.example.UserService userService;
    private Connection testConnection;
    private static final String H2_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MySQL";

    @BeforeEach
    void setUp() throws Exception {
        // Create H2 in-memory database
        testConnection = DriverManager.getConnection(H2_URL, "sa", "");
        
        // Create test table
        try (Statement stmt = testConnection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "email VARCHAR(255), " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")");
            
            // Insert test data
            stmt.execute("INSERT INTO users (name, email) VALUES ('testuser', 'test@example.com')");
            stmt.execute("INSERT INTO users (name, email) VALUES ('admin', 'admin@example.com')");
        }
        
        // Initialize UserService with H2 connection
        userService = new main.java.com.example.UserService(H2_URL, "sa", "");
    }

    @AfterEach
    void tearDown() throws Exception {
        if (testConnection != null && !testConnection.isClosed()) {
            try (Statement stmt = testConnection.createStatement()) {
                stmt.execute("DROP TABLE IF EXISTS users");
            }
            testConnection.close();
        }
    }

    @Test
    void testUserServiceConstructor() {
        assertNotNull(userService);
    }

    @Test
    void testUserServiceDefaultConstructor() {
        main.java.com.example.UserService defaultService = new main.java.com.example.UserService("password");
        assertNotNull(defaultService);
    }

    @Test
    void testFindUserWithNullUsername() {
        main.java.com.example.UserServiceException exception = assertThrows(
            main.java.com.example.UserServiceException.class, 
            () -> userService.findUser(null)
        );
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }

    @Test
    void testDeleteUserWithNullUsername() {
        main.java.com.example.UserServiceException exception = assertThrows(
            main.java.com.example.UserServiceException.class, 
            () -> userService.deleteUser(null)
        );
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }

    @Test
    void testFindUserWithEmptyUsername() {
        main.java.com.example.UserServiceException exception = assertThrows(
            main.java.com.example.UserServiceException.class, 
            () -> userService.findUser("")
        );
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }

    @Test
    void testDeleteUserWithEmptyUsername() {
        main.java.com.example.UserServiceException exception = assertThrows(
            main.java.com.example.UserServiceException.class, 
            () -> userService.deleteUser("")
        );
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }

    @Test
    void testFindUserSuccess() {
        // This should succeed with H2 database
        assertDoesNotThrow(() -> userService.findUser("testuser"));
    }

    @Test
    void testFindUserNotFound() {
        // This should not throw exception even if user not found
        assertDoesNotThrow(() -> userService.findUser("nonexistent"));
    }

    @Test
    void testDeleteUserSuccess() {
        // This should succeed
        assertDoesNotThrow(() -> userService.deleteUser("testuser"));
    }

    @Test
    void testDeleteUserNotFound() {
        // Deleting non-existent user should not throw exception
        assertDoesNotThrow(() -> userService.deleteUser("nonexistent"));
    }
}