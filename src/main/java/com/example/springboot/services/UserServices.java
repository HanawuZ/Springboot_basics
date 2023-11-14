package com.example.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.models.User;
import com.example.springboot.repositories.UserRepository;

@Service
public class UserServices {
    
    @Autowired
    private UserRepository userRepository;

    public UserServices(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
}
