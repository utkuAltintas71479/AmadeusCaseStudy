package com.example.Amadeus.service;

import com.example.Amadeus.dto.CreateNewUserRequestDTO;
import com.example.Amadeus.dto.CreateNewUserResponseDTO;
import com.example.Amadeus.dto.LoginUserRequestDTO;
import com.example.Amadeus.dto.LoginUserResponseDTO;
import com.example.Amadeus.entity.User;
import com.example.Amadeus.exception.AuthorizationException;
import com.example.Amadeus.exception.UserNameAlreadyInUseException;
import com.example.Amadeus.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CreateNewUserResponseDTO createUser(CreateNewUserRequestDTO createNewUserRequestDTO) {
        if (isUsernameAlreadyInUse(createNewUserRequestDTO.getUserName())) {
            throw new UserNameAlreadyInUseException(createNewUserRequestDTO.getUserName());
        }
        User user = new User();
        user.setUserName(createNewUserRequestDTO.getUserName());
        user.setPassword(passwordEncoder.encode(createNewUserRequestDTO.getPassword()));
        userRepository.save(user);
        return new CreateNewUserResponseDTO(user.getUserId(), user.getUserName(),"User created successfully");
    }

    public LoginUserResponseDTO loginUser(LoginUserRequestDTO loginUserRequestDTO) {
        User user = userRepository.findByUserName(loginUserRequestDTO.getUserName())
                .orElseThrow(() -> new AuthorizationException("Invalid username"));
        if (!passwordEncoder.matches(loginUserRequestDTO.getPassword(), user.getPassword())) {
            throw new AuthorizationException("Invalid password");
        }
        return new LoginUserResponseDTO(user.getUserName(),"Login successful");
    }


    public boolean isUsernameAlreadyInUse(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }
}
