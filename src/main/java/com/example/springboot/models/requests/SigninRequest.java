package com.example.springboot.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SigninRequest {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("password")
    private String password;
}
