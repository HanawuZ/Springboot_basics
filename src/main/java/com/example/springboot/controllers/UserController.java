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
import com.example.springboot.models.requests.SigninRequest;
import com.example.springboot.models.requests.SignupRequest;
import com.example.springboot.models.response.SigninResponse;

import org.springframework.security.authentication.AuthenticationManager;
import com.example.springboot.repositories.UserRepository;
import com.example.springboot.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import com.example.springboot.auth.JwtUtil;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    // private JwtUtil jwtUtil;

    public UserController(UserService userService, AuthenticationManager authenticationManager,
            UserRepository userRepository) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        // this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) throws JsonProcessingException {
        System.out.println(signupRequest.getEmail());
        System.out.println(signupRequest.getLastName());

        userService.signup(signupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("user successfully signed up");
    }

    // @GetMapping("/authenticated")
    // public ResponseEntity<String> authenticated() throws JsonProcessingException
    // {
    // String responseMessage = "This API is accessed by only authenticated users";

    // HashMap<String, String> dataMap = new HashMap<>();
    // dataMap.put("message", responseMessage);

    // return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    // }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) throws JsonProcessingException {

        try {
            // Authentication authentication = authenticationManager
            //         .authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(),
            //                 signinRequest.getPassword()));

            // if (authentication == null) {
            //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error signin failed!");
            // }

            // Get user from username
            User user = userRepository.findUserByUsername(signinRequest.getUsername());

            if (user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No user found!!");
            }

            // String token = jwtUtil.createToken(user);

            SigninResponse signinResponse = new SigninResponse(user.getUsername(), "");

            return ResponseEntity.ok(signinResponse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error signin failed!!");

        }
    }

    /*
     * @ResponseBody
     * 
     * @RequestMapping(value = "/login",method = RequestMethod.POST)
     * public ResponseEntity login(@RequestBody LoginReq loginReq) {
     * 
     * try {
     * Authentication authentication =
     * authenticationManager.authenticate(new
     * UsernamePasswordAuthenticationToken(loginReq.getEmail(),
     * loginReq.getPassword()));
     * String email = authentication.getName();
     * User user = new User(email,"");
     * String token = jwtUtil.createToken(user);
     * LoginRes loginRes = new LoginRes(email,token);
     * 
     * return ResponseEntity.ok(loginRes);
     * 
     * }catch (BadCredentialsException e){
     * ErrorRes errorResponse = new
     * ErrorRes(HttpStatus.BAD_REQUEST,"Invalid username or password");
     * return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
     * }catch (Exception e){
     * ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST,
     * e.getMessage());
     * return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
     * }
     * }
     */

}
