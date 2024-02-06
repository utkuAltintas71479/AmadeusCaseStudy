package com.example.Amadeus.exception;

public class AirportMismatchException extends RuntimeException {
    public AirportMismatchException(String error) {
        super(String.format(error));
    }

}
