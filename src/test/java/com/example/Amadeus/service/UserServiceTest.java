package com.example.Amadeus.service;

import com.example.Amadeus.dto.CreateNewUserRequestDTO;
import com.example.Amadeus.dto.CreateNewUserResponseDTO;
import com.example.Amadeus.entity.User;
import com.example.Amadeus.repository.UserRepository;
import com.example.Amadeus.exception.UserNameAlreadyInUseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private CreateNewUserRequestDTO newUserRequest;

    @BeforeEach
    void setUp() {
        newUserRequest = new CreateNewUserRequestDTO();
        newUserRequest.setUserName("TEST_USERNAME");
        newUserRequest.setPassword("TEST_PASSWORD");
    }

    @Test
    void createUser_success() {
        when(userRepository.findByUserName(newUserRequest.getUserName())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(newUserRequest.getPassword())).thenReturn("ENCODED_TEST_PASSWORD");
        CreateNewUserResponseDTO response = userService.createUser(newUserRequest);
        assertNotNull(response);
        assertEquals(newUserRequest.getUserName(), response.getUserName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUser_failure_userNameAlreadyExists() {
        when(userRepository.findByUserName(newUserRequest.getUserName())).thenReturn(Optional.of(new User()));
        assertThrows(UserNameAlreadyInUseException.class, () -> userService.createUser(newUserRequest));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void isUsernameAlreadyInUse_true() {
        when(userRepository.findByUserName("TEST_USERNAME")).thenReturn(Optional.of(new User()));
        boolean result = userService.isUsernameAlreadyInUse("TEST_USERNAME");
        assertTrue(result);
    }

    @Test
    void isUsernameAlreadyInUse_false() {
        when(userRepository.findByUserName("TEST_USERNAME")).thenReturn(Optional.empty());
        boolean result = userService.isUsernameAlreadyInUse("TEST_USERNAME");
        assertFalse(result);
    }
}
