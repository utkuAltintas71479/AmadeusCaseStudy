package com.example.Amadeus.exception;

public class NoAirportException extends RuntimeException{
    public NoAirportException(String message) {
        super(message);
    }
}
