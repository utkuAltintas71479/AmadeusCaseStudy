package com.example.Amadeus.exception;

public class UserNameAlreadyInUseException extends RuntimeException{
    public UserNameAlreadyInUseException(String username) {
        super(String.format("Username '%s' is already in use", username));
    }
}
