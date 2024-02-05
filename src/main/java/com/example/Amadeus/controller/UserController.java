package com.example.Amadeus.controller;

import com.example.Amadeus.dto.CreateNewUserResponseDTO;
import com.example.Amadeus.dto.LoginUserRequestDTO;
import com.example.Amadeus.dto.LoginUserResponseDTO;
import com.example.Amadeus.entity.User;
import com.example.Amadeus.exception.UserNameAlreadyInUseException;
import com.example.Amadeus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.Amadeus.dto.CreateNewUserRequestDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createNewUser")
    public ResponseEntity<CreateNewUserResponseDTO> createUser(@Validated @RequestBody CreateNewUserRequestDTO createNewUserRequestDTO){
        return new ResponseEntity<>(userService.createUser(createNewUserRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDTO> loginUser(@Validated @RequestBody LoginUserRequestDTO loginUserRequestDTO){
        return new ResponseEntity<>(userService.loginUser(loginUserRequestDTO), HttpStatus.OK);
    }
}
