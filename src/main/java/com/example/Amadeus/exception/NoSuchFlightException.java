package com.example.Amadeus.exception;

public class NoSuchFlightException extends RuntimeException {
    public NoSuchFlightException(String error) {
        super(String.format(error));
    }

}
