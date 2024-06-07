package com.example.springboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.lib.http.BaseResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
public class HealthCheck {

    @GetMapping("/")
    public ResponseEntity<BaseResponse> greeting() {
        return BaseResponse.OK("Ping pong", null, null);
    }

    @GetMapping("/check/auth/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BaseResponse>  userAuthCheck() {
        try {
            return BaseResponse.OK("Role user authenticated", null, null);
        } catch (Exception e) {
            return  BaseResponse.Unauthorized(null, e.getMessage(), null);
        }
    }

    @GetMapping("/check/auth/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<BaseResponse>  adminAuthCheck() {
        try {
            return BaseResponse.OK("Role admin authenticated", null, null);
        } catch (Exception e) {
            return  BaseResponse.Unauthorized(null, e.getMessage(), null);
        }
    }
}