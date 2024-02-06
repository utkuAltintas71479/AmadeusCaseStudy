package com.example.Amadeus.exception;

public class NoFlightException extends RuntimeException{

    public NoFlightException(String error) {
        super(String.format(error));
    }

}
