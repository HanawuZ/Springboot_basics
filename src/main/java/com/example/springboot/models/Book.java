package com.example.springboot.models;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "books")
public class Book {
    /*
     * JSON format
     * {
     * "isbn": "",
     * "title": "",
     * "genre": "",
     * "publicationYear": "",
     * "copiesAvailable": "",
     * "price": "",
     * "authorId": "",
     * "publisherId": ""
     * }
     */
    @Id
    @Column(name = "id")
    private String id;

    @Column(unique = true, name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "publication_year")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationYear;

    @Column(name = "copies_available")
    private int copiesAvailable;

    @Column(name = "price")
    private double price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_authors", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @JsonProperty("createdDate")
    @Column(name = "created_date")
    private Timestamp createdDate;

    @JsonProperty("createdBy")
    @Column(name = "created_by")
    private String createdBy;

    @JsonProperty("updatedDate")
    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @JsonProperty("updatedBy")
    @Column(name = "updated_by")
    private String updatedBy;


    public Book() {
    }

    public Book(String isbn, String title, String genre, LocalDate publicationYear, int copiesAvailable, double price) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.copiesAvailable = copiesAvailable;
        this.price = price;
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.createdBy = "system";
        this.updatedDate = new Timestamp(System.currentTimeMillis());
        this.updatedBy = "system";  
    }

    public void addAuthor(Author author) {
        if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
    }

}
