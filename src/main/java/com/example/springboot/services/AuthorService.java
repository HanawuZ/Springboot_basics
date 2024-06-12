package com.example.springboot.services;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.hibernate.annotations.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springboot.models.Author;
import com.example.springboot.models.requests.AuthorRequest;
import com.example.springboot.repositories.AuthorRepository;


/**
 * InnerAuthorService
 */
interface IAuthorService {
    List<Author> getAllAuthors();
    Optional<Author> getAuthorById(String id);
    Optional<Author> createAuthor(AuthorRequest authorRequest);
    Optional<Author> deleteAuthor(String id);
    Optional<Author> updateAuthor(String id, AuthorRequest authorRequest);
    
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

    public Optional<Author> createAuthor(AuthorRequest authorRequest){
        try {
            Author author = new Author();
            author.setId(UUID.randomUUID().toString());
            author.setFirstname(authorRequest.getFirstname());
            author.setLastname(authorRequest.getLastname());
            author.setDob(authorRequest.getDob());
            author.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            author.setCreatedBy("system");
            author.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            author.setUpdatedBy("system");

            boolean isComplete = authorRepository.createAuthors(List.of(author));
            if (!isComplete) {
                return Optional.empty();
            }

            return Optional.of(author);

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Author> updateAuthor(String id, AuthorRequest authorRequest) {
        try {
            Author author = new Author();
            author.setFirstname(authorRequest.getFirstname());
            author.setLastname(authorRequest.getLastname());
            author.setDob(authorRequest.getDob());
            author.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            author.setUpdatedBy("system");

            boolean isComplete = authorRepository.updateAuthor(id, author);
            if (!isComplete) {
                return Optional.empty();
            }
            return Optional.of(author);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    public Optional<Author> deleteAuthor(String id) {
        try {
            boolean isComplete = authorRepository.deleteAuthor(id);
            if (!isComplete) {
                return Optional.empty();
            }   

            return Optional.of(new Author());

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
