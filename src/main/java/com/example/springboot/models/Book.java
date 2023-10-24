package com.example.springboot.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class Book {
    /*
     ISBN (Primary Key)
    Title
    Publication Year
    Genre
    Copies Available
    Price
     */

    @Id
    private Long Isbn;


    private String title;
    
    private String genre;

    @JsonFormat(pattern = "yyyy")
    private LocalDate publicationYear;

    private int copiesAvailable;

    private double price;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    public Book() {}

    public Book(Long Isbn, String title, String genre, LocalDate publicationYear, int copiesAvailable, double price, Author author) {
        this.Isbn = Isbn;
        this.title = title;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.copiesAvailable = copiesAvailable;
        this.price = price;
        this.author = author;
    }

        
}
