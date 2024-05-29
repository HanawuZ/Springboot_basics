package com.example.springboot.controllers;

import com.example.springboot.models.Author;
import com.example.springboot.repositories.AuthorRepository;
import com.example.springboot.services.AuthorService;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.lib.http.BaseResponse;
import com.example.springboot.lib.http.StatusCode;

/**
 * InnerAuthorController
 */
interface IAuthorController {
    ResponseEntity<BaseResponse> getAllAuthors();
    ResponseEntity<BaseResponse> getAuthorById(String id);
    // Author saveAuthor(Author author);
    // Author deleteAuthorById(Long id);
    // Author updateAuthorById(Long id, Author author);
    
}

@CrossOrigin
@RequestMapping("/authors")
@RestController
public class AuthorController implements IAuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllAuthors() {
        try {
            List<Author> result = authorService.getAllAuthors();
            if (result == null) {
                return BaseResponse.NotFound("", "No authors found", null);
            }
            return BaseResponse.OK("Found authors", null, result);
        } catch (Exception e) {
            return BaseResponse.InternalServerError(null, e.getMessage(), null );
        }
    }

    @GetMapping("/by-id")
    public ResponseEntity<BaseResponse> getAuthorById(@RequestParam(name = "id") String id) {
        try {
            Optional<Author> author = authorService.getAuthorById(id);
            if (!author.isPresent()) {
                return BaseResponse.NotFound("Author not found", "Author with given ID does not exist", null);
            }
            return BaseResponse.OK("Found author", null, author);

        } catch(Exception e) {
            return BaseResponse.InternalServerError(null, e.getMessage(), null);
        }
    }

    // @PostMapping
    // public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
    //     try {
    //         Author savedAuthor = authorRepository.save(author);
    //         return ResponseEntity.ok(savedAuthor);
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().build();
    //     }
    // }

    // @GetMapping("/book/{isbn}")
    // public ResponseEntity<List<Author>> getAuthorByISBN(@PathVariable Long isbn){
    //     try {
    //         List<Author> authors = authorRepository.findAuthorByISBN(isbn);
    //         if (authors == null) {
    //             return ResponseEntity.notFound().build();
    //         }
    //         return ResponseEntity.ok(authors);

    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //         return ResponseEntity.badRequest().build();
    //     }

    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
    //     // Find existing author by ID
    //     Author existingAuthor = authorRepository.findById(id).orElse(null);
    //     if (existingAuthor == null) {
    //         return ResponseEntity.notFound().build();
    //     }

    //     // Update existing author
    //     existingAuthor.setName(author.getName());
    //     existingAuthor.setDob(author.getDob());
    //     authorRepository.save(existingAuthor);
    //     return ResponseEntity.ok(existingAuthor);
    
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
    //     if (authorRepository.existsById(id)) {
    //         authorRepository.deleteById(id);
    //         return ResponseEntity.ok("Author with ID " + id + " has been deleted.");
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

}
