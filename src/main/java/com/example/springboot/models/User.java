package com.example.springboot.models;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @JsonProperty("id")
    private String id;

    @Column(name = "username")
    @JsonProperty("username")
    private String username;

    @Column(name = "firstname")
    @JsonProperty("firstName")
    private String firstName;

    @Column(name = "lastname")
    @JsonProperty("lastName")
    private String lastName;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "password")
    @JsonProperty("password")
    private String password;

    @Column(name = "role")
    @JsonProperty("role")
    private String role;

    @Column(name = "created_date")
    @JsonProperty("createdDate")
    private Timestamp createdDate;

    @Column(name = "created_by")
    @JsonProperty("createdBy")
    private String createdBy;

    @Column(name = "updated_date")
    @JsonProperty("updatedDate")
    private Timestamp updatedDate;

    @Column(name = "updated_by")
    @JsonProperty("updatedBy")
    private String updatedBy;

    
    public User(){}

    public User(String username, String firstName, String lastName, String email, String password, String role){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
