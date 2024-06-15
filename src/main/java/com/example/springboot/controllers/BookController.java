package com.example.springboot.controllers;

import org.springframework.http.ResponseEntity;

import com.example.springboot.lib.http.BaseResponse;
import com.example.springboot.models.response.Book.BookResponse;
import com.example.springboot.services.BookService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * InnerBookController
 */
interface IBookController {
    ResponseEntity<BaseResponse> getAllBooks();

    ResponseEntity<BaseResponse> getBookById(String id);

}

@CrossOrigin
@RequestMapping("/books")
@RestController
public class BookController 
// implements IBookController 
{
    // private final BookService bookService;

    // public BookController(BookService bookService) {
    //     this.bookService = bookService;
    // }

    // @GetMapping
    // public ResponseEntity<BaseResponse> getAllBooks() {
    //     try {
    //         List<BookResponse> result = bookService.getAllBooks();
    //         if (result == null) {
    //             return BaseResponse.NotFound("", "No books found", null);
    //         }
    //         return BaseResponse.OK("Found books", null, result);

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return BaseResponse.InternalServerError(null, e.getMessage(), null);
    //     }
    // }

    // @GetMapping("/by-id")
    // public ResponseEntity<BaseResponse> getBookById(@RequestParam(name = "id") String id) {
    //     try {
    //         if (id == null || id.isEmpty() || id == "") {
    //             return BaseResponse.BadRequest(null, "Book id is required", null);
    //         }

    //         BookResponse result = bookService.getBookById(id);
    //         if (result == null) {
    //             return BaseResponse.NotFound("", "No book found", null);
    //         }

    //         return BaseResponse.OK("Found book", null, result);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return BaseResponse.InternalServerError(null, e.getMessage(), null);
    //     }
    // }

}
