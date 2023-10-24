package com.example.springboot.controllers;

import com.example.springboot.models.Author;
import com.example.springboot.models.Book;
import com.example.springboot.models.BookRequest;
import com.example.springboot.repositories.AuthorRepository;
import com.example.springboot.repositories.BookRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById( @PathVariable  Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<String> saveBook(@RequestBody BookRequest bookRequest) {

        // Find author by authorId
        Author author = authorRepository.findById(bookRequest.getAuthorId()).orElse(null);
        if (author == null) {
            return ResponseEntity.badRequest().body("Author not found");
        }

        
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(author);

        book = bookRepository.save(book);
        return ResponseEntity.ok().body(book.getIsbn().toString());
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

}
