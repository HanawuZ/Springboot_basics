package com.example.springboot.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.springboot.models.query.BookQueryResult;
import java.sql.Date;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * InnerBookRepository
 */
interface IBookRepository {
    List<BookQueryResult> getAllBooks();

    List<BookQueryResult> getBookById(String id);

}

@Repository
public class BookRepository implements IBookRepository {

    private EntityManager entityManager;

    public BookRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Transactional
    public List<BookQueryResult> getAllBooks() {
        try {
            final String queryString = "SELECT b.id, b.copies_available, b.genre, b.isbn, b.title, b.price, b.publication_year, "
                    +
                    "p.id AS publisher_id, p.name AS publisher_name, " +
                    "a.id AS author_id, a.firstname as author_firstname, a.lastname as author_lastname " +
                    "FROM books b " +
                    "LEFT JOIN publishers p ON b.publisher_id = p.id " +
                    "LEFT JOIN book_authors ba ON b.id = ba.book_id " +
                    "LEFT JOIN authors a ON ba.author_id = a.id";

            List<Object[]> resultList = entityManager.createNativeQuery(queryString).getResultList();
            List<BookQueryResult> books = new ArrayList<>();

            for (Object[] row : resultList) {
                // Manually map the result set to BookQueryResult objects
                BookQueryResult bookQueryResult = new BookQueryResult();
                bookQueryResult.setId((String) row[0]);
                bookQueryResult.setCopiesAvailable((Integer) row[1]);
                bookQueryResult.setGenre((String) row[2]);
                bookQueryResult.setIsbn((String) row[3]);
                bookQueryResult.setTitle((String) row[4]);
                bookQueryResult.setPrice((Double) row[5]);
                bookQueryResult.setPublicationYear((Date) row[6]);
                bookQueryResult.setPublisherId((String) row[7]);
                bookQueryResult.setPublisherName((String) row[8]);
                bookQueryResult.setAuthorId((String) row[9]);
                bookQueryResult.setAuthorFirstname((String) row[10]);
                bookQueryResult.setAuthorLastname((String) row[11]);

                books.add(bookQueryResult);
            }

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public List<BookQueryResult> getBookById(String id) {
        try {
            final String queryString = "SELECT b.id, b.copies_available, b.genre, b.isbn, b.title, b.price, b.publication_year, "
                    +
                    "p.id AS publisher_id, p.name AS publisher_name, " +
                    "a.id AS author_id, a.firstname as author_firstname, a.lastname as author_lastname " +
                    "FROM books b " +
                    "LEFT JOIN publishers p ON b.publisher_id = p.id " +
                    "LEFT JOIN book_authors ba ON b.id = ba.book_id " +
                    "LEFT JOIN authors a ON ba.author_id = a.id " +
                    String.format("WHERE b.id = '%s'", id);
            List<Object[]> resultList = entityManager.createNativeQuery(queryString).getResultList();
            List<BookQueryResult> books = new ArrayList<>();

            for (Object[] row : resultList) {
                // Manually map the result set to BookQueryResult objects
                BookQueryResult bookQueryResult = new BookQueryResult();
                bookQueryResult.setId((String) row[0]);
                bookQueryResult.setCopiesAvailable((Integer) row[1]);
                bookQueryResult.setGenre((String) row[2]);
                bookQueryResult.setIsbn((String) row[3]);
                bookQueryResult.setTitle((String) row[4]);
                bookQueryResult.setPrice((Double) row[5]);
                bookQueryResult.setPublicationYear((Date) row[6]);
                bookQueryResult.setPublisherId((String) row[7]);
                bookQueryResult.setPublisherName((String) row[8]);
                bookQueryResult.setAuthorId((String) row[9]);
                bookQueryResult.setAuthorFirstname((String) row[10]);
                bookQueryResult.setAuthorLastname((String) row[11]);

                books.add(bookQueryResult);
            }

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
