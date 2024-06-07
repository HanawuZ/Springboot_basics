package com.example.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.example.springboot.lib.http.BaseResponse;
import com.example.springboot.models.User;
import com.example.springboot.models.requests.SigninRequest;
import com.example.springboot.models.requests.SignupRequest;
import com.example.springboot.models.response.SigninResponse;
import com.example.springboot.services.JwtService;
import com.example.springboot.services.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * InnerUserController
 */
interface IUserController {
    ResponseEntity<BaseResponse> createUser(@RequestBody SignupRequest userSignupRequest);

    ResponseEntity<BaseResponse> userSignin(@RequestBody SigninRequest signinRequest);
}

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController implements IUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<BaseResponse> createUser(@RequestBody SignupRequest userSignupRequest) {
        try {
            if (userSignupRequest == null) {
                return BaseResponse.BadRequest(null, "User request cannot be empty", null);
            }

            if (userSignupRequest.getPassword() == null || userSignupRequest.getPassword().isEmpty()) {
                return BaseResponse.BadRequest(null, "Password cannot be empty", null);
            }

            if (userSignupRequest.getUsername() == null || userSignupRequest.getUsername().isEmpty()) {
                return BaseResponse.BadRequest(null, "Username cannot be empty", null);
            }

            if (userSignupRequest.getEmail() == null || userSignupRequest.getEmail().isEmpty()) {
                return BaseResponse.BadRequest(null, "Email cannot be empty", null);
            }

            boolean isComplete = userService.createUser(userSignupRequest);
            if (!isComplete) {
                return BaseResponse.InternalServerError(null, "User creation failed", null);
            }
            return BaseResponse.Created("User created successfully", null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.InternalServerError(null, e.getMessage(), null);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<BaseResponse> userSignin(@RequestBody SigninRequest signinRequest) {
        try {
            if (signinRequest == null) {
                return BaseResponse.BadRequest(null, "User request cannot be empty", null);
            }

            if (signinRequest.getPassword() == null || signinRequest.getPassword().isEmpty()) {
                return BaseResponse.BadRequest(null, "Password cannot be empty", null);
            }

            if (signinRequest.getUsername() == null || signinRequest.getUsername().isEmpty()) {
                return BaseResponse.BadRequest(null, "Username cannot be empty", null);
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                SigninResponse response = new SigninResponse();
                response.setUsername(signinRequest.getUsername());
                response.setAccessToken(jwtService.generateToken(signinRequest.getUsername()));
                return BaseResponse.OK(null, null,response);
            }

            return BaseResponse.BadRequest(null, "Username or password is incorrect", null);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.InternalServerError(null, e.getMessage() + ", Username or password is incorrect", null);
        }
    }
}
