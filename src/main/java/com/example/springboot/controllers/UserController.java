package com.example.springboot.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.models.User;
import com.example.springboot.models.requests.SignupRequest;
import com.example.springboot.repositories.UserRepository;
import com.example.springboot.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class UserController {

    @Autowired
    public UserService userService;
    

    public UserController(UserService userService) {
        this.userService = userService;
    }   

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) throws JsonProcessingException {
        System.out.println(signupRequest.getEmail());        
        System.out.println(signupRequest.getLastName());

        userService.signup(signupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("user successfully signed up");
    }

    @GetMapping("/authenticated")
    public ResponseEntity<String> authenticated() throws JsonProcessingException {
        String responseMessage = "This API is accessed by only authenticated users";

        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("message", responseMessage);

        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }


}
