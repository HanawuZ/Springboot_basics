package com.example.springboot.models.response.Book;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
@Data
public class Publisher {
    
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;
}
