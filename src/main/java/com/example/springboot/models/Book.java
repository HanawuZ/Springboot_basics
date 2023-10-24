package com.example.springboot.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
     JSON format 
     {
            "isbn": "",
            "title": "",
            "genre": "",
            "publicationYear": "",
            "copiesAvailable": "",
            "price": "",
            "authorId": "",
            "publisherId": ""
     }
     */
    @Id
    private Long Isbn;

    private String title;

    private String genre;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationYear;

    private int copiesAvailable;

    private double price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        
        name = "book_author", 
        joinColumns = @JoinColumn(name = "isbn"), inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Book() {}

    public Book(Long Isbn, String title, String genre, LocalDate publicationYear, int copiesAvailable, double price) {
        this.Isbn = Isbn;
        this.title = title;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.copiesAvailable = copiesAvailable;
        this.price = price;
    }
    public void addAuthor(Author author) {
        if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
    }

}
