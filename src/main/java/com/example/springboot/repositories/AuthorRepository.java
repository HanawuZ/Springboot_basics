package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot.models.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, String> {

    // @Query("SELECT a FROM Author a JOIN a.book b WHERE b.Isbn = :isbn")
    // List<Author> findAuthorByISBN(Long isbn);

}
