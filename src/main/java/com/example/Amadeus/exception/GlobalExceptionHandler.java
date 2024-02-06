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

}
