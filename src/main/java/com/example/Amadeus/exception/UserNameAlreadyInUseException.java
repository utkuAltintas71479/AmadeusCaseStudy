package com.example.Amadeus.exception;

public class UserNameAlreadyInUseException extends RuntimeException{

    public UserNameAlreadyInUseException(String userName) {
        super(String.format("Username already in use: %s", userName));
    }

}
