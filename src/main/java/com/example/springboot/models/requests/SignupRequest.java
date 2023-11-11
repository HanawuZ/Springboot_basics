package com.example.springboot.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SignupRequest {
    
    @JsonProperty("userId")
    private String userId;
    
    @JsonProperty("firstname")
    private String firstName;
    
    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("role")
    private String role;

}
