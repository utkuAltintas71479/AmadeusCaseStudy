package com.example.Amadeus.controller;

import com.example.Amadeus.entity.User;
import com.example.Amadeus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.Amadeus.dto.CreateNewUserRequestDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createNewUser")
    public ResponseEntity<?> createUser(@RequestBody CreateNewUserRequestDTO createNewUserRequestDTO) {
        try {
            return new ResponseEntity<>(userService.createUser(createNewUserRequestDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
