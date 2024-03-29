package com.example.Amadeus.service;

import com.example.Amadeus.dto.request.CreateNewUserRequestDTO;
import com.example.Amadeus.dto.response.CreateNewUserResponseDTO;
import com.example.Amadeus.entity.User;
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
        return new CreateNewUserResponseDTO(user.getUserId(), user.getUserName());
    }




    public boolean isUsernameAlreadyInUse(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }
}
