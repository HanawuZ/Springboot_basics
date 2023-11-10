package com.example.springboot.controllers;

import com.example.springboot.models.Author;
import com.example.springboot.repositories.AuthorRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin

@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        try {
            return ResponseEntity.ok(authorRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(author);
    }

    @PostMapping
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
        try {
            Author savedAuthor = authorRepository.save(author);
            return ResponseEntity.ok(savedAuthor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/book/{isbn}")
    public ResponseEntity<List<Author>> getAuthorByISBN(@PathVariable Long isbn){
        try {
            List<Author> authors = authorRepository.findAuthorByISBN(isbn);
            if (authors == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(authors);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        // Find existing author by ID
        Author existingAuthor = authorRepository.findById(id).orElse(null);
        if (existingAuthor == null) {
            return ResponseEntity.notFound().build();
        }

        // Update existing author
        existingAuthor.setName(author.getName());
        existingAuthor.setDob(author.getDob());
        authorRepository.save(existingAuthor);
        return ResponseEntity.ok(existingAuthor);
    
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return ResponseEntity.ok("Author with ID " + id + " has been deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
