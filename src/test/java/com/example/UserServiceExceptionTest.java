package main.java.com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceExceptionTest {

    @Test
    void testExceptionWithMessage() {
        String message = "Test error message";
        UserServiceException exception = new UserServiceException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testExceptionWithMessageAndCause() {
        String message = "Test error message";
        Throwable cause = new RuntimeException("Root cause");
        UserServiceException exception = new UserServiceException(message, cause);
        
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}