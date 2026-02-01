package main.java.com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService("testPassword123");
    }

    @Test
    void testUserServiceConstructor() {
        assertNotNull(userService);
    }

    @Test
    void testFindUserWithNullUsername() {
        UserServiceException exception = assertThrows(UserServiceException.class, () -> {
            userService.findUser(null);
        });
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }

    @Test
    void testDeleteUserWithNullUsername() {
        UserServiceException exception = assertThrows(UserServiceException.class, () -> {
            userService.deleteUser(null);
        });
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }

    @Test
    void testFindUserWithEmptyUsername() {
        UserServiceException exception = assertThrows(UserServiceException.class, () -> {
            userService.findUser("");
        });
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }

    @Test
    void testDeleteUserWithEmptyUsername() {
        UserServiceException exception = assertThrows(UserServiceException.class, () -> {
            userService.deleteUser("");
        });
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }

    @Test
    void testFindUserThrowsExceptionForDatabaseError() {
        // This will throw UserServiceException because no actual database connection exists
        UserServiceException exception = assertThrows(UserServiceException.class, () -> {
            userService.findUser("testUser");
        });
        assertTrue(exception.getMessage().contains("Failed to find user"));
    }

    @Test
    void testDeleteUserThrowsExceptionForDatabaseError() {
        // This will throw UserServiceException because no actual database connection exists
        UserServiceException exception = assertThrows(UserServiceException.class, () -> {
            userService.deleteUser("testUser");
        });
        assertTrue(exception.getMessage().contains("Failed to delete user"));
    }
}