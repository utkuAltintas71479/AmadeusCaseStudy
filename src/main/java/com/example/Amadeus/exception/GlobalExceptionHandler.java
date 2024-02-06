package com.example.Amadeus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException methodArgumentNotValidException) {
        ErrorResponse response = new ErrorResponse(methodArgumentNotValidException.getFieldError().getDefaultMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNameAlreadyInUseException.class)
    public ResponseEntity<ErrorResponse> handleUserNameAlreadyInUseError(UserNameAlreadyInUseException userNameAlreadyInUseException) {
        ErrorResponse response = new ErrorResponse(userNameAlreadyInUseException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationError(AuthorizationException authorizationException) {
        ErrorResponse response = new ErrorResponse(authorizationException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoSuchAirportException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchAirportError(NoSuchAirportException noSuchAirportException) {
        ErrorResponse response = new ErrorResponse(noSuchAirportException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoAirportException.class)
    public ResponseEntity<ErrorResponse> handleNoAirportException(NoAirportException noAirportException) {
        ErrorResponse response = new ErrorResponse(noAirportException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AirportMismatchException.class)
    public ResponseEntity<ErrorResponse> handleAirportMismatchException(AirportMismatchException airportMismatchException) {
        ErrorResponse response = new ErrorResponse(airportMismatchException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateMismatchException.class)
    public ResponseEntity<ErrorResponse> handleDateMismatchException(DateMismatchException dateMismatchException) {
        ErrorResponse response = new ErrorResponse(dateMismatchException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchFlightException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchFlightException(NoSuchFlightException noSuchFlightException) {
        ErrorResponse response = new ErrorResponse(noSuchFlightException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoFlightException.class)
    public ResponseEntity<ErrorResponse> handleNoFlightException(NoFlightException noFlightException) {
        ErrorResponse response = new ErrorResponse(noFlightException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
