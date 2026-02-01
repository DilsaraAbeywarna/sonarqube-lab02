package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceExceptionTest {

    @Test
    void testExceptionWithMessage() {
        String message = "Test error message";
        main.java.com.example.UserServiceException exception = new main.java.com.example.UserServiceException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testExceptionWithMessageAndCause() {
        String message = "Test error message";
        Throwable cause = new RuntimeException("Root cause");
        main.java.com.example.UserServiceException exception = new main.java.com.example.UserServiceException(message, cause);
        
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}