package com.example.springboot.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SigninRequest {

    @JsonProperty("usernameOrEmail")
    private String usernameOrEmail;

    @JsonProperty("password")
    private String password;
}
