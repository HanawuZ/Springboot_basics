package com.example.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springboot.models.User;
import com.example.springboot.repositories.UserRepository;

@Service
public class UserServices {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServices(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public User signup(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("Admin");
        return userRepository.save(user);
    }
}
