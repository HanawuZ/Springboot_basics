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

import com.example.springboot.constants.DataResultStatusCode;
import com.example.springboot.models.Author;
import com.example.springboot.models.requests.AuthorRequest;
import com.example.springboot.repositories.AuthorRepository;
import com.example.springboot.models.DataResult;

/**
 * InnerAuthorService
 */
interface IAuthorService {
    DataResult<List<Author>> getAllAuthors();
    DataResult<Author> getAuthorById(String id);
    DataResult<Author> createAuthor(AuthorRequest authorRequest);
    DataResult<Author> deleteAuthor(String id);
    DataResult<Author> updateAuthor(String id, AuthorRequest authorRequest);
    
}

@Service
public class AuthorService implements IAuthorService{
    AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public DataResult<List<Author>> getAllAuthors() {
        try {
            List<Author> authors = authorRepository.findAll();

            if (authors == null || authors.isEmpty()) {
                return new DataResult<List<Author>>(DataResultStatusCode.NOT_FOUND_DATA, null, "No authors found");
            }
            
            return new DataResult<List<Author>>(DataResultStatusCode.GET_DATA_SUCCESS, authors, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new DataResult<List<Author>>(DataResultStatusCode.INTERNAL_SERVER_ERROR, null, e.getMessage());
        }
    }

    public DataResult<Author> getAuthorById(String id) {
        try {
            Optional<Author> author = authorRepository.findById(id);
            if (author == null) {
                return new DataResult<Author>(DataResultStatusCode.NOT_FOUND_DATA, null, "No authors found");
            }
            return new DataResult<Author>(DataResultStatusCode.GET_DATA_SUCCESS, author.get(), null);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new DataResult<Author>(DataResultStatusCode.INTERNAL_SERVER_ERROR, null, e.getMessage());
        }
    }

    public DataResult<Author> createAuthor(AuthorRequest authorRequest){
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
                return new DataResult<Author>(DataResultStatusCode.CREATE_DATA_FAILED, null, "Unable to create author");
            }

            return new DataResult<Author>(DataResultStatusCode.CREATE_DATA_SUCCESS, author, null);

        } catch (Exception e) {
            e.printStackTrace();
            return new DataResult<Author>(DataResultStatusCode.INTERNAL_SERVER_ERROR, null, e.getMessage());
        }
    }

    public DataResult<Author> updateAuthor(String id, AuthorRequest authorRequest) {
        try {
            Author author = new Author();
            author.setFirstname(authorRequest.getFirstname());
            author.setLastname(authorRequest.getLastname());
            author.setDob(authorRequest.getDob());
            author.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            author.setUpdatedBy("system");

            boolean isComplete = authorRepository.updateAuthor(id, author);
            if (!isComplete) {
                return new DataResult<Author>(DataResultStatusCode.UPDATE_DATA_FAILED, null, "Unable to update author");
            }
            return new DataResult<Author>(DataResultStatusCode.UPDATE_DATA_SUCCESS, author, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new DataResult<Author>(DataResultStatusCode.INTERNAL_SERVER_ERROR, null, e.getMessage());
        }
    }


    public DataResult<Author> deleteAuthor(String id) {
        try {
            boolean isComplete = authorRepository.deleteAuthor(id);
            if (!isComplete) {
                return new DataResult<Author>(DataResultStatusCode.DELETE_DATA_FAILED, null, "Unable to delete author");
            }   

            return new DataResult<Author>(DataResultStatusCode.DELETE_DATA_SUCCESS, null, null);

        } catch (Exception e) {
            e.printStackTrace();
            return new DataResult<Author>(DataResultStatusCode.INTERNAL_SERVER_ERROR, null, e.getMessage());
        }
    }

}
