package com.example.Amadeus.exception;

public class NoSuchAirportException extends RuntimeException{

    public NoSuchAirportException(String error) {
        super(String.format(error));
    }

}

