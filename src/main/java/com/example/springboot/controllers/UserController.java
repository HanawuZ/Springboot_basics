package com.example.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.models.User;
import com.example.springboot.services.UserServices;
import com.example.springboot.dto.requests.SignupRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserServices userServices;

    public UserController(UserServices userServices){
        this.userServices = userServices;
    }

    @GetMapping
    public String hello(){
        return "Hello World! this is test";
    }

    @PostMapping 
    ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
        User newUser = new User();
        newUser.setUsername(signupRequest.getUsername());
        newUser.setFirstName(signupRequest.getFirstName());
        newUser.setLastName(signupRequest.getLastName());
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPassword(signupRequest.getPassword());

        User savedUser = userServices.signup(newUser);
        return ResponseEntity.ok(savedUser);
    }
}
