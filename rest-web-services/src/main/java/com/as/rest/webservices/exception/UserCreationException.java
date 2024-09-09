package com.as.rest.webservices.exception;

public class UserCreationException extends RuntimeException{

    public UserCreationException(String message, Exception e) {
        super(message, e);
    }

    public UserCreationException(String message) {
        super(message);
    }
}
