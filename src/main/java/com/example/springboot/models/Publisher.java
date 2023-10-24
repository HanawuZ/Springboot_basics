package com.example.springboot.models;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
/*
 Book * ------- 1 Publisher

 Publisher
    PublisherID (Primary Key)
    Publisher Name
    Address
    Phone Number
    Email

 */

@Data
@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publisherId;
    
    private String publisherName;

    private String address;

    private String phoneNumber;

    private String email;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Book> book;
}
