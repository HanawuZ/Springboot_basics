package com.example.springboot.services;

import org.apache.catalina.connector.Response;
import org.hibernate.annotations.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springboot.models.User;
import com.example.springboot.repositories.UserRepository;

import com.example.springboot.models.requests.SignupRequest;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<String> signup(SignupRequest signupRequest) {
        User user = new User();
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole(signupRequest.getRole());
        userRepository.save(user);

        return ResponseEntity.ok("User created successfully");
    }

    
}
