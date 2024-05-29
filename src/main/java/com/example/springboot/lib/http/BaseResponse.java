package com.example.springboot.lib.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.ResponseEntity;

public class BaseResponse {
    
    @JsonProperty("message")
    private String message;

    @JsonProperty("error")
    private String error;

    @JsonProperty("data")
    private Object data;

    public BaseResponse(String message, String error, Object data) {
        this.message = message;
        this.error = error;
        this.data = data;
    }

    public static ResponseEntity<BaseResponse> OK(String message, String error, Object data) {
        return ResponseEntity.status(StatusCode.STATUS_OK).body(new BaseResponse(message, error, data));
    }

    public static ResponseEntity<BaseResponse> BadRequest(String message, String error, Object data) {
        return ResponseEntity.status(StatusCode.STATUS_BAD_REQUEST).body(new BaseResponse(message, error, data));
    }

    public static ResponseEntity<BaseResponse> Created(String message, String error, Object data) {
        return ResponseEntity.status(StatusCode.STATUS_CREATED).body(new BaseResponse(message, error, data));
    }   

    public static ResponseEntity<BaseResponse> Forbidden(String message, String error, Object data) {
        return ResponseEntity.status(StatusCode.STATUS_FORBIDDEN).body(new BaseResponse(message, error, data));
    }

    public static ResponseEntity<BaseResponse> Unauthorized(String message, String error, Object data) {
        return ResponseEntity.status(StatusCode.STATUS_UNAUTHORIZED).body(new BaseResponse(message, error, data));
    }   

    public static ResponseEntity<BaseResponse> NotFound(String message, String error, Object data) {
        return ResponseEntity.status(StatusCode.STATUS_NOT_FOUND).body(new BaseResponse(message, error, data));
    }   

    public static ResponseEntity<BaseResponse> InternalServerError(String message, String error, Object data) {     
        return ResponseEntity.status(StatusCode.STATUS_INTERNAL_SERVER_ERROR).body(new BaseResponse(message, error, data));
    }
}
