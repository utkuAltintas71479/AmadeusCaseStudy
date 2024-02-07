package com.example.Amadeus.entity;

import com.example.Amadeus.util.Constants;
import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false, length = Constants.USERNAME_MAX_LENGTH)
    private String userName;

    @Column(nullable = false, length = Constants.PASSWORD_MAX_LENGTH)
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}