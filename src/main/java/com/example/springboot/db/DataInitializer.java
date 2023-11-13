package com.example.springboot.db;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.springboot.models.User;
import com.example.springboot.models.Author;
import com.example.springboot.models.Book;
import com.example.springboot.models.Publisher;
import com.example.springboot.repositories.UserRepository;
import com.example.springboot.repositories.AuthorRepository;
import com.example.springboot.repositories.BookRepository;
import com.example.springboot.repositories.PublisherRepository;
import com.example.springboot.services.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner{
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {

        // List<Author> authors = new ArrayList<>();

        // authors.add(new Author("John Smith", LocalDate.parse("1980-05-15")));
        // authors.add(new Author("Alice Johnson", LocalDate.parse("1975-12-10")));
        // authors.add(new Author("Michael Davis", LocalDate.parse("1992-08-22")));
        // authors.add(new Author("Emily Wilson", LocalDate.parse("1988-04-30")));
        // authors.add(new Author("David Roberts", LocalDate.parse("1970-11-05")));
        // authors.add(new Author("Sarah Brown", LocalDate.parse("1995-02-18")));
        // authors.add(new Author("James Miller", LocalDate.parse("1982-09-25")));
        // authors.add(new Author("Olivia Taylor", LocalDate.parse("1978-07-12")));
        // authors.add(new Author("Daniel Anderson", LocalDate.parse("1990-03-03")));
        // authors.add(new Author("Sophia White", LocalDate.parse("1986-06-28")));

        // authorRepository.saveAll(authors);
        
        // // Create sample publishers
        // Publisher publisher1 = new Publisher("Publisher A", "123 Main St", "123-456-7890", "publisherA@example.com");
        // Publisher publisher2 = new Publisher("Publisher B", "456 Elm St", "987-654-3210", "publisherB@example.com");
        
        // publisherRepository.saveAll(List.of(publisher1, publisher2));

        // // Create sample books
        // List<Book> books = new ArrayList<>();

        // books.add(new Book("9781234567890", "Book Title 1", "Fiction", LocalDate.parse("2021-01-15"), 50, 19.99));
        // books.get(0).addAuthor(authors.get(0));
        // books.get(0).addAuthor(authors.get(1));
        // books.get(0).setPublisher(publisher1);

        // books.add(new Book("9782345678901", "Book Title 2", "Mystery", LocalDate.parse("2019-07-20"), 30, 15.99));
        // books.get(1).addAuthor(authors.get(2));
        // books.get(1).setPublisher(publisher1);

        // books.add(new Book("9783456789012", "Book Title 3", "Science Fiction", LocalDate.parse("2020-05-10"), 40, 14.99));
        // books.get(2).addAuthor(authors.get(3));
        // books.get(2).setPublisher(publisher2);

        // books.add(new Book("9784567890123", "Book Title 4", "Romance", LocalDate.parse("2018-11-30"), 20, 12.99));
        // books.get(3).addAuthor(authors.get(4));
        // books.get(3).setPublisher(publisher2);

        // bookRepository.saveAll(books);

        // Create sample users
        User user1 = new User(
            "user1",
            "John",
            "Doe",
            "ex@gmail.com",
            "123456",
            "admin"
        );

    
    }

}
