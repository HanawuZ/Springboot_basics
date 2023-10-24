package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.models.Author;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
