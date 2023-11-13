package com.example.springboot.models.response;
import lombok.Data;

@Data
public class SigninResponse {
    private String username;
    private String token;

    public SigninResponse(String email, String token) {
        this.username = email;
        this.token = token;
    }
}
