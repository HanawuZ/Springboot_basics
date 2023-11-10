package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot.models.Book;

import jakarta.transaction.Transactional;

import java.util.List;
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.id = :authorId")
    List<Book> findBookByAuthorID(Long authorId);

    // Select books by ISBN
    // @Query("SELECT b FROM Book b WHERE b.isbn = :isbn")
    Book findBookByISBN(String isbn);

    // Delete book by ISBN
    // @Transactional
    // @Modifying
    // @Query("DELETE FROM Book b WHERE b.isbn = :isbn")
    // void deleteBookByISBN(String isbn);
}