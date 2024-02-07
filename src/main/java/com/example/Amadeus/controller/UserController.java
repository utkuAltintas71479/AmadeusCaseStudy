package com.example.Amadeus.controller;

import com.example.Amadeus.dto.request.CreateNewUserRequestDTO;
import com.example.Amadeus.dto.response.CreateNewUserResponseDTO;
import com.example.Amadeus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users/create")
    public ResponseEntity<CreateNewUserResponseDTO> createUser(@Validated @RequestBody CreateNewUserRequestDTO createNewUserRequestDTO) {
        return new ResponseEntity<>(userService.createUser(createNewUserRequestDTO), HttpStatus.CREATED);
    }


}
