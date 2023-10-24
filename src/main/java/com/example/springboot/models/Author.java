package com.example.springboot.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Entity
@Data
@Table(name = "authors")
public class Author {
    /*
    JSON Format
    {
        "name": "",
        "dob": ""
    }
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @ManyToMany(
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        },
        mappedBy = "authors",
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    List<Book> book;

    public Author() {}

    public Author(String name, LocalDate dob) {
        this.name = name;
        this.dob = dob;
    }

    
}
