package com.example.springboot.models.requests;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BookRequest {
    
    @JsonProperty("isbn")
    private String Isbn;
    
    @JsonProperty("title")
    private String title;
    
    @JsonProperty("genre")
    private String genre;
    
    @JsonProperty("authorId")
    private List<Long> authorId;
    
    @JsonProperty("publisherId")
    private Long publisherId;
    
    @JsonProperty("publicationYear")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String publicationYear;
    
    private int copiesAvailable;
    private double price;

    public LocalDate getPublicationYear() {
        // Parse into LocalDate yyyy-mm-dd
        return LocalDate.parse(this.publicationYear);
    }

}
