package com.example.springboot.controllers;

import com.example.springboot.models.Author;
import com.example.springboot.models.Book;
import com.example.springboot.models.BookRequest;
import com.example.springboot.repositories.AuthorRepository;
import com.example.springboot.repositories.BookRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            return ResponseEntity.ok(bookRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        try {
            Book book = bookRepository.findById(id).orElse(null);
            if (book == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<Book>> getBookByAuthorId(@PathVariable Long id) {
        try {
            // todo change to get books by authorID
            List<Book> books = bookRepository.findBookByAuthorID(id);
            if (books == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /*
     
     */
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookRequest bookRequest){
        // Create a new Book entity
        try {

            Book newBook = new Book();
            newBook.setIsbn(bookRequest.getIsbn());
            newBook.setTitle(bookRequest.getTitle());
            newBook.setGenre(bookRequest.getGenre());
            newBook.setPublicationYear(bookRequest.getPublicationYear());
            System.out.println(bookRequest.getPublicationYear());
            newBook.setCopiesAvailable(bookRequest.getCopiesAvailable());
            newBook.setPrice(bookRequest.getPrice());
    
            // Retrieve the Author based on the authorId from the request
            Author author = authorRepository.findById(bookRequest.getAuthorId()).orElse(null);
            if (author == null) {
                return ResponseEntity.notFound().build();
            } 
    
            // Add the Author to the list of authors for the new book
            newBook.addAuthor(author);
            
    
            // Save the new Book to the database
            Book savedBook = bookRepository.save(newBook);
    
            // Return a ResponseEntity with the added Book
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok("Book with ID " + id + " has been deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
