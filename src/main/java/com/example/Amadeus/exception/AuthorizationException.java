package com.example.Amadeus.exception;

public class AuthorizationException extends RuntimeException{

    public AuthorizationException(String error) {
        super(String.format(error));
    }

}
