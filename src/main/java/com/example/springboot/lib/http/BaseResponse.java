package com.example.springboot.lib.http;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.ResponseEntity;
@Data
public class BaseResponse {
    
    @JsonProperty("statusCode")
    @JsonIgnore
    private int statusCode;
    
    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Object data;

    public BaseResponse() {}

    public BaseResponse(int statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public static ResponseEntity<BaseResponse> Build(BaseResponse response) {
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
