package com.example.Amadeus.dto;

public class CreateNewUserResponseDTO {
    private Integer userId;
    private String userName;

    private String message;

    // Constructors
    public CreateNewUserResponseDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CreateNewUserResponseDTO(Integer userId, String userName, String message) {
        this.userId = userId;
        this.userName = userName;
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
