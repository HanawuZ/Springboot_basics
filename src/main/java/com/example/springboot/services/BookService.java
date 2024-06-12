package com.example.springboot.services;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.springboot.models.query.BookQueryResult;
import com.example.springboot.models.response.Book.Author;
import com.example.springboot.models.response.Book.BookResponse;
import com.example.springboot.models.response.Book.Publisher;
import com.example.springboot.repositories.BookRepository;

/**
 * InnerBookService
 */
interface IBookService {
    List<BookResponse> getAllBooks();

    BookResponse getBookById(String id);

}

@Service
public class BookService {
    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookResponse> getAllBooks() {
        try {
            HashMap<String, BookResponse> bookIdDataMap = new HashMap<>();

            List<BookQueryResult> result = bookRepository.getAllBooks();
            if (result == null || result.isEmpty() || result.size() == 0) {
                return null;
            }

            /* */
            for (BookQueryResult v : result) {
                BookResponse bookResponse;

                String bookId = v.getId();
                // If book id is not stored as key in hashmap
                if (!bookIdDataMap.containsKey(bookId)) {

                    // Initialize book response
                    bookResponse = new BookResponse();
                    bookResponse.setId(bookId);

                    // Set book response
                    bookResponse.setCopiesAvailable(v.getCopiesAvailable());
                    bookResponse.setGenre(v.getGenre());
                    bookResponse.setIsbn(v.getIsbn());
                    bookResponse.setTitle(v.getTitle());
                    bookResponse.setPublicationYear(v.getPublicationYear());
                    bookResponse.setPrice(v.getPrice());

                    // Initialize publisher
                    Publisher publisher = new Publisher();
                    publisher.setId(v.getPublisherId());
                    publisher.setName(v.getPublisherName());
                    bookResponse.setPublisher(publisher);

                    // Initilize empty list of authors
                    List<Author> emptyAuthorsList = new java.util.ArrayList<>();
                    bookResponse.setAuthors(emptyAuthorsList);

                    // Set book id as key in hashmap
                    bookIdDataMap.put(bookId, bookResponse);
                }

                // Initilize author instance
                if (v.getAuthorId() == null || v.getAuthorId().isEmpty()) {
                    continue;
                }
                if (bookIdDataMap.get(bookId).getAuthors() == null
                        || bookIdDataMap.get(bookId).getAuthors().isEmpty()) {
                    bookIdDataMap.get(bookId).setAuthors(new ArrayList<Author>());
                }

                Author author = new Author();
                author.setId(v.getAuthorId());
                author.setFirstname(v.getAuthorFirstname());
                author.setLastname(v.getAuthorLastname());
                bookIdDataMap.get(bookId).getAuthors().add(author);
            }

            List<BookResponse> bookResponse = new ArrayList<BookResponse>(bookIdDataMap.values());
            return bookResponse;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BookResponse getBookById(String id) {
        try {

            List<BookQueryResult> bookQueryResult = bookRepository.getBookById(id);
            if (bookQueryResult == null || bookQueryResult.isEmpty() || bookQueryResult.size() == 0) {
                return null;
            }

            BookResponse bookResponse = new BookResponse();
            bookResponse.setId(id);
            bookResponse.setCopiesAvailable(bookQueryResult.get(0).getCopiesAvailable());
            bookResponse.setGenre(bookQueryResult.get(0).getGenre());
            bookResponse.setIsbn(bookQueryResult.get(0).getIsbn());
            bookResponse.setTitle(bookQueryResult.get(0).getTitle());
            bookResponse.setPublicationYear(bookQueryResult.get(0).getPublicationYear());
            bookResponse.setPrice(bookQueryResult.get(0).getPrice());

            Publisher publisher = new Publisher();
            publisher.setId(bookQueryResult.get(0).getPublisherId());
            publisher.setName(bookQueryResult.get(0).getPublisherName());
            bookResponse.setPublisher(publisher);
            bookResponse.setAuthors(new ArrayList<Author>());

            for (BookQueryResult v : bookQueryResult) {
                Author author = new Author();
                if (v.getAuthorId() == null || v.getAuthorId().isEmpty()) {
                    continue;
                }

                author.setId(v.getAuthorId());
                author.setFirstname(v.getAuthorFirstname());
                author.setLastname(v.getAuthorLastname());
                bookResponse.getAuthors().add(author);
            }
            return bookResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
