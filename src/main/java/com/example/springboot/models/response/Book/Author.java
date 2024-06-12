package com.example.springboot.models.response.Book;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
@Data
public class Author {
    @JsonProperty("id")
    private String id;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;
}
