package com.example.springboot.models;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails{

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

    @Column(name = "is_active")
    @JsonProperty("isActive")
    private boolean isActive;

    
    public User(){}

    public User(String username, String firstName, String lastName, String email, String password, String role){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      if (this.role == "ROLE_ADMIN") {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
      }
      return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
