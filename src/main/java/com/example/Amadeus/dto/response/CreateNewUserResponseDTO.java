package com.example.Amadeus.dto.response;

import com.example.Amadeus.util.Constants;

public class CreateNewUserResponseDTO {
    private String message;
    private Integer userId;
    private String userName;


    // Constructors
    public CreateNewUserResponseDTO() {
    }

    public CreateNewUserResponseDTO(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.message = Constants.USER_CREATION_SUCCESS;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
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
