package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}