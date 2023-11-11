package com.example.springboot.controllers;

import com.example.springboot.models.Author;
import com.example.springboot.models.Book;
import com.example.springboot.models.Publisher;
import com.example.springboot.models.requests.BookRequest;
import com.example.springboot.repositories.AuthorRepository;
import com.example.springboot.repositories.BookRepository;
import com.example.springboot.repositories.PublisherRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final AuthorRepository authorRepository;

    @Autowired
    private final PublisherRepository publisherRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            return ResponseEntity.ok(bookRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        try {
            Book book = bookRepository.findBookByISBN(isbn);
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
            System.out.println(bookRequest.getIsbn());
            newBook.setTitle(bookRequest.getTitle());            
            System.out.println(bookRequest.getTitle());
            newBook.setGenre(bookRequest.getGenre());            
            System.out.println(bookRequest.getGenre());

            newBook.setPublicationYear(bookRequest.getPublicationYear());
            System.out.println(bookRequest.getPublicationYear());
            newBook.setCopiesAvailable(bookRequest.getCopiesAvailable());            
            System.out.println(bookRequest.getCopiesAvailable());

            newBook.setPrice(bookRequest.getPrice());            
            System.out.println(bookRequest.getPrice());

            System.out.println(bookRequest.getAuthorId());
            
    
            // Add list of authors for the new book
            for (int i = 0 ; i < bookRequest.getAuthorId().size() ; i++){
                Author author = authorRepository.findById(bookRequest.getAuthorId().get(i)).orElse(null);
                if (author == null) {
                    return ResponseEntity.notFound().build();
                } 
                newBook.addAuthor(author);
            }

            Publisher publisher = publisherRepository.findById(bookRequest.getPublisherId()).orElse(null);
            if (publisher == null) {
                return ResponseEntity.notFound().build();
            }

            newBook.setPublisher(publisher);
            
            // Save the new Book to the database
            Book savedBook = bookRepository.save(newBook);
    
            // Return a ResponseEntity with the added Book
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Book> deleteBook(@PathVariable String isbn) {
        try {
            Book book = bookRepository.findBookByISBN(isbn);
            if (book == null) {
                return ResponseEntity.notFound().build();
            }
            bookRepository.delete(book);
            return ResponseEntity.ok(book);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
