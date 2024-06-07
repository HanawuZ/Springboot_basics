package com.example.springboot.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SigninResponse {
    
    @JsonProperty("accessToken")
    private String accessToken;

    @JsonProperty("username")
    private String username;
}
