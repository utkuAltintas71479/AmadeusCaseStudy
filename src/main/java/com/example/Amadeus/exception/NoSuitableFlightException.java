package com.example.Amadeus.exception;

public class NoSuitableFlightException extends RuntimeException{
    public NoSuitableFlightException(String message) {
        super(message);
    }
}
