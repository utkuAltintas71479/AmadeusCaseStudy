package com.example.Amadeus.controller;
import com.example.Amadeus.exception.UserNameAlreadyInUseException;
import org.apache.catalina.User;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import com.example.Amadeus.dto.CreateNewUserRequestDTO;
import com.example.Amadeus.dto.CreateNewUserResponseDTO;
import com.example.Amadeus.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createUser_whenPostMethod() throws Exception {
        CreateNewUserRequestDTO requestDTO = new CreateNewUserRequestDTO();
        requestDTO.setUserName("TEST_USER_NAME");
        requestDTO.setPassword("TEST_PASSWORD");
        CreateNewUserResponseDTO responseDTO = new CreateNewUserResponseDTO();
        responseDTO.setUserId(1);
        responseDTO.setUserName("TEST_USER_NAME");
        when(userService.createUser(any(CreateNewUserRequestDTO.class))).thenReturn(responseDTO);
        mockMvc.perform(post("/createNewUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(responseDTO.getUserId()))
                .andExpect(jsonPath("$.userName").value(responseDTO.getUserName()));
        verify(userService).createUser(any(CreateNewUserRequestDTO.class));
    }

    @Test
    public void createUser_whenPasswordIsTooShort_shouldReturnBadRequest() throws Exception {
        CreateNewUserRequestDTO requestDTO = new CreateNewUserRequestDTO();
        requestDTO.setUserName("TEST_USER_NAME");
        requestDTO.setPassword("SHORT");
        mockMvc.perform(post("/createNewUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    public void createUser_whenUsernameIsAlreadyInUse_shouldReturnBadRequest() throws Exception {
        CreateNewUserRequestDTO requestDTO = new CreateNewUserRequestDTO();
        requestDTO.setUserName("TEST_USER_NAME");
        requestDTO.setPassword("TEST_PASSWORD");
        doThrow(new UserNameAlreadyInUseException("Username is already in use"))
                .when(userService).createUser(any(CreateNewUserRequestDTO.class));
        mockMvc.perform(post("/createNewUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UserNameAlreadyInUseException))
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    public void createUser_whenPasswordIsEmpty_shouldReturnBadRequest() throws Exception {
        CreateNewUserRequestDTO requestDTO = new CreateNewUserRequestDTO();
        requestDTO.setUserName("TEST_USER_NAME");
        requestDTO.setPassword("");
        mockMvc.perform(post("/createNewUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(jsonPath("$.error").exists());
    }
}