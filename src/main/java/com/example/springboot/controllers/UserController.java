package com.example.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.example.springboot.lib.http.BaseResponse;
import com.example.springboot.lib.http.StatusCode;
import com.example.springboot.models.DataResult;
import com.example.springboot.models.User;
import com.example.springboot.models.requests.SigninRequest;
import com.example.springboot.models.requests.SignupRequest;
import com.example.springboot.models.response.SigninResponse;
import com.example.springboot.services.JwtService;
import com.example.springboot.services.UserService;
import com.fasterxml.jackson.databind.JsonSerializable.Base;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * InnerUserController
 */
interface IUserController {
    // ResponseEntity<BaseResponse> createUser(@RequestBody SignupRequest userSignupRequest);

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

    // @PostMapping
    // public ResponseEntity<BaseResponse> createUser(@RequestBody SignupRequest userSignupRequest) {
    //     try {
    //         if (userSignupRequest == null) {
    //             return HttpResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, null, "User request cannot be empty", null));
    //         }

    //         if (userSignupRequest.getPassword() == null || userSignupRequest.getPassword().isEmpty() || userSignupRequest.getPassword().equals("")) {
    //             return HttpResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, null, "Password cannot be empty", null));
    //         }

    //         if (userSignupRequest.getUsername() == null || userSignupRequest.getUsername().isEmpty() || userSignupRequest.getUsername().equals("")) {
    //             return HttpResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, null, "Username cannot be empty", null));
    //         }

    //         if (userSignupRequest.getEmail() == null || userSignupRequest.getEmail().isEmpty() || userSignupRequest.getEmail().equals("")) {
    //             return HttpResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, null, "User request cannot be empty", null));
    //         }

    //         BaseResponse response = userService.createUser(userSignupRequest);

    //         return HttpResponse.Build(response);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return HttpResponse.Build(new BaseResponse(StatusCode.INTERNAL_SERVER_ERROR, null, e.getMessage(), null));
    //     }
    // }

    @PostMapping("/signin")
    public ResponseEntity<BaseResponse> userSignin(@RequestBody SigninRequest signinRequest) {
        try {
            if (signinRequest == null) {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "Signin request cannot be empty", null));
            }

            if (signinRequest.getPassword() == null || signinRequest.getPassword().isEmpty()) {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "Password cannot be empty", null));
            }

            if (signinRequest.getUsernameOrEmail() == null || signinRequest.getUsernameOrEmail().isEmpty()) {
                return BaseResponse.Build(new BaseResponse(StatusCode.BAD_REQUEST, "Username/Email cannot be empty", null));
            }

            // Authenticate and get user data
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signinRequest.getUsernameOrEmail(), signinRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                DataResult<User> result = userService.getUserData(signinRequest.getUsernameOrEmail());
                SigninResponse response = new SigninResponse();
                response.setUsername(signinRequest.getUsernameOrEmail());
                response.setAccessToken(jwtService.generateToken(result.getData()));
                return BaseResponse.Build(new BaseResponse(StatusCode.CREATED, "Signin successful", response));
            }
            return BaseResponse.Build(new BaseResponse(StatusCode.UNAUTHORIZED, "Username or password is incorrect", null));

        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.Build(new BaseResponse(StatusCode.INTERNAL_SERVER_ERROR, e.getMessage(), null));
        }
    }
}
