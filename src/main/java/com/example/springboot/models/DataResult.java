package com.example.springboot.models;

import lombok.Data;

@Data
public class DataResult<T> {
    private final T data;
    private final String error;
    private final int code;

    public DataResult(int code, T data, String error) {
        this.data = data;
        this.error = error;
        this.code = code;
    }
}
