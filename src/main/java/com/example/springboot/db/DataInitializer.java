package com.example.springboot.db;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.springboot.models.Author;
import com.example.springboot.models.Book;
import com.example.springboot.repositories.AuthorRepository;
import com.example.springboot.repositories.BookRepository;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner{
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create sample authors
        Author author1 = new Author("John Doe", LocalDate.of(1980, 1, 1));
        Author author2 = new Author("Jane Smith", LocalDate.of(1981,10, 21));
        Author author3 = new Author("Bob Williams", LocalDate.of(1985, 5, 5));
        Author author4 = new Author("Alice Brown", LocalDate.of(1990, 12, 12));
        
        // Save all authors in 
        authorRepository.saveAll(List.of(author1, author2, author3, author4));

        // // Create sample books
        Book book1 = new Book(
            Long.parseLong("0373182341"), 
            "Book1",
            "Sci-fi",
            LocalDate.of(2010, 1, 1),
            10,
            100.0
        );
        book1.addAuthor(author1);        
        book1.addAuthor(author2);


        // Book book2 = new Book(
        //     Long.parseLong("0373182342"), 
        //     "Memory of dust",
        //     "Fantasy",
        //     LocalDate.of(2011, 1, 1),
        //     10,
        //     70.0,
        //     author2
        // );

        // Book book3 = new Book(
        //     Long.parseLong("0373182343"), 
        //     "Star wars Episode II: Attack of the clones",
        //     "Sci-fi",
        //     LocalDate.of(2012, 1, 1),
        //     100,
        //     120.0,
        //     author3
        // );

        bookRepository.saveAll(List.of(book1));
        
    }
}
