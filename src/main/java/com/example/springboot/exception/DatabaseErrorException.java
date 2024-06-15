package com.example.springboot.exception;

public class DatabaseErrorException extends RuntimeException{
    public DatabaseErrorException(String message) {
        super(message);
    }
}
