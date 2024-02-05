package com.example.Amadeus.dto;

public class LoginUserResponseDTO {
    private String userName;

    public LoginUserResponseDTO() {
    }

    public LoginUserResponseDTO( String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
