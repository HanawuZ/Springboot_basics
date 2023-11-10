package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot.models.Book;


import java.util.List;
public interface BookRepository extends JpaRepository<Book, String> {

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.id = :authorId")
    List<Book> findBookByAuthorID(Long authorId);

    // Select books by ISBN
    @Query("SELECT b FROM Book b WHERE b.Isbn = :Isbn")
    Book findBookByISBN(String Isbn);

    // Delete book by ISBN
    @Query("DELETE FROM Book b WHERE b.Isbn = :Isbn")
    @Modifying
    void deleteBookByISBN(String Isbn);
}