package com.example.springboot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.sql.Timestamp;
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
    @JsonProperty("id")
    @Column(name = "id")
    private String id;

    @JsonProperty("name")
    @Column(name = "name")
    private String name;

    @JsonProperty("address")
    @Column(name = "address")
    private String address;

    @JsonProperty("phoneNumber")
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @JsonProperty("email")
    @Column(name = "email")
    private String email;

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

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Book> book;

    public Publisher() {
    }

    public Publisher(String name, String lastname, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.createdBy = "system";
        this.updatedDate = new Timestamp(System.currentTimeMillis());
        this.updatedBy = "system";
    }
}
