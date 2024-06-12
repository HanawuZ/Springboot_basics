package com.example.springboot.models.response.Book;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class BookResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("copiesAvailable")
    private Integer copiesAvailable;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("title")
    private String title;

    @JsonProperty("publicationYear")
    private Date publicationYear;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("authors")
    private List<Author> authors;

    @JsonProperty("publisher")
    private Publisher publisher;

}
