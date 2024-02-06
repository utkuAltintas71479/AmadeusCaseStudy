package com.example.Amadeus.dto;

public class LoginUserResponseDTO {
    private String message;
    private String userName;


    public LoginUserResponseDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginUserResponseDTO(String userName ) {
        this.userName = userName;
        this.message = "Login successful";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
