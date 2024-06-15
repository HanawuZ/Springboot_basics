package com.example.springboot.exception;

public class GeneralErrorExcpetion extends RuntimeException {
    public GeneralErrorExcpetion(String message) {
        super(message);
    }
}
