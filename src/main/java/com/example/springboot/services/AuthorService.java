package com.example.springboot.services;


import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.hibernate.annotations.Bag;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springboot.models.Author;
import com.example.springboot.repositories.AuthorRepository;


/**
 * InnerAuthorService
 */
interface IAuthorService {
    List<Author> getAllAuthors();
    Optional<Author> getAuthorById(String id);
    
}

@Service
public class AuthorService implements IAuthorService{
    AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        try {
            List<Author> authors = authorRepository.findAll();

            if (authors == null) {
                return null;
            }
            return authors;
            
        } catch (Exception e) {
            return null;
        }
    }

    public Optional<Author> getAuthorById(String id) {
        try {
            Optional<Author> author = authorRepository.findById(id);
            if (author == null) {
                return null;
            }
            return author;
            
        } catch (Exception e) {
            return null;
        }
    }
    
}
