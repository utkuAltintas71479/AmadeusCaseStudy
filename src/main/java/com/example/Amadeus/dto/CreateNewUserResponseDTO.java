package com.example.Amadeus.dto;

public class CreateNewUserResponseDTO {
    private Integer userId;
    private String userName;

    // Constructors
    public CreateNewUserResponseDTO() {
    }

    public CreateNewUserResponseDTO(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
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
