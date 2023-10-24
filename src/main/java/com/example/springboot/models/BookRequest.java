package com.example.springboot.models;

public class BookRequest {
    private String title;
    private Long authorId;

    // Constructors, getters, setters
    public BookRequest(String title, Long authorId) {
        this.title = title;
        this.authorId = authorId;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for authorId
    public Long getAuthorId() {
        return authorId;
    }

    // Setter for authorId
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
    
}
