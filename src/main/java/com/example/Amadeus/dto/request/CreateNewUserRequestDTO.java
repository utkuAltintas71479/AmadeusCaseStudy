package com.example.Amadeus.dto.request;

import com.example.Amadeus.util.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateNewUserRequestDTO {
    @NotBlank(message = Constants.USERNAME_REQUIRED)
    private String userName;

    @NotBlank(message = Constants.PASSWORD_REQUIRED)
    @Size(min = Constants.PASSWORD_LENGTH_LIMIT, message = Constants.PASSWORD_LENGTH_LIMIT_FAILED)
    private String password;

    public CreateNewUserRequestDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
