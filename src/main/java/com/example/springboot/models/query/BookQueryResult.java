package com.example.springboot.models.query;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
public class BookQueryResult {

    @Column(name = "id")
    private String id;

    @Column(name = "copies_available")
    private Integer copiesAvailable;

    @Column(name = "genre")
    private String genre;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "publication_year")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publicationYear;

    @Column(name = "price")
    private Double price;

    @Column(name = "publisher_id")
    private String publisherId;

    @Column(name = "publisher_name")
    private String publisherName;

    @Column(name = "author_id")
    private String authorId;

    @Column(name = "author_firstname")
    private String authorFirstname;

    @Column(name = "author_lastname")
    private String authorLastname;

    public BookQueryResult() {}

    public BookQueryResult(String id, Integer copiesAvailable, String genre, String isbn, String title,
         Double price ,Date publicationYear, String publisherId, String publisherName, String authorId,
            String authorFirstname, String authorLastname) {
        this.id = id;
        this.copiesAvailable = copiesAvailable;
        this.genre = genre;
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.price = price;
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.authorId = authorId;
        this.authorFirstname = authorFirstname;
        this.authorLastname = authorLastname;
    }

}
