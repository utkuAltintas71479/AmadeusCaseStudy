package com.example.Amadeus.controller;

import com.example.Amadeus.dto.request.CreateNewUserRequestDTO;
import com.example.Amadeus.dto.response.CreateNewUserResponseDTO;
import com.example.Amadeus.dto.response.SearchedFlightResponseDTO;
import com.example.Amadeus.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Sign up for an account", description = "Creates a new user account on the platform using the provided information.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateNewUserResponseDTO.class))),
    })
    public ResponseEntity<CreateNewUserResponseDTO> createUser(@Validated @RequestBody CreateNewUserRequestDTO createNewUserRequestDTO) {
        return new ResponseEntity<>(userService.createUser(createNewUserRequestDTO), HttpStatus.CREATED);
    }


}
