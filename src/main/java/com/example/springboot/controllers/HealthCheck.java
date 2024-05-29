package com.example.springboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.lib.http.BaseResponse;

import org.springframework.http.ResponseEntity;

@RestController
public class HealthCheck {

    @GetMapping("/")
    public ResponseEntity<BaseResponse> greeting() {
        return BaseResponse.OK("Ping ppng", null, null);
    }
}