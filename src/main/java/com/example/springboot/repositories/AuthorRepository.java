package com.example.springboot.repositories;

import com.example.springboot.exception.DatabaseErrorException;
import com.example.springboot.models.Author;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

/**
 * InnerAuthorRepository
 */
interface IAuthorRepository {
    List<Author> findAll();
    Optional<Author> findById(String id);
    boolean createAuthors(List<Author> author);
    boolean updateAuthor(String id, Author author);
    boolean deleteAuthor(String id);
}

@Repository
public class AuthorRepository implements IAuthorRepository {

    private EntityManager entityManager;

    @Autowired
    public AuthorRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Author> findAll() {

        try {
            final String queryString = "SELECT * FROM authors";
    
            Query query = entityManager.createNativeQuery(queryString, Author.class);
    
            // Get result query
            List<Author> result = query.getResultList();
            if (result.isEmpty()) {
                return null;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseErrorException(e.getMessage());
        }
    }

    @Override
    public Optional<Author> findById(String id) {
        try {
            final String queryString = String.format("SELECT * FROM authors WHERE id = %s", id);
    
            Query query = entityManager.createNativeQuery(queryString, Author.class);
    
            // Get result query
            List<Author> results = query.getResultList();
    
            if (results.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(results.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseErrorException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean createAuthors(List<Author> author) {
        try {
            EntityTransaction transaction = entityManager.getTransaction();

            transaction.begin();

            for (Author a : author) {
                entityManager.persist(a);
            }

            transaction.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseErrorException(e.getMessage());
        }
    }

    @Override
    @Transactional
    @Modifying
    public boolean updateAuthor(String id, Author author) {
        try {
            Author foundAuthor = entityManager.find(Author.class, id);
            if (foundAuthor == null) {
                return false;
            }
            final String queryString = "UPDATE authors SET name = :name, dob = :dob, updated_by = :updatedBy, updated_date = :updatedDate WHERE id = :id";

            // Begin transaction
            EntityTransaction transaction = entityManager.getTransaction();

            transaction.begin();
            
            Query query = entityManager.createNativeQuery(queryString, Author.class);
            
            query.setParameter("name", author.getFirstname());
            query.setParameter("dob", author.getDob());
            query.setParameter("updatedBy", author.getUpdatedBy());
            query.setParameter("updatedDate", author.getUpdatedDate());
            query.setParameter("id", id);
            
            query.executeUpdate();
            transaction.commit();
            return true;

        } catch(Exception e) {
            e.printStackTrace();
            throw new DatabaseErrorException(e.getMessage());
        }
    }

    public boolean deleteAuthor(String id) {
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            Author author = entityManager.find(Author.class, id);
            entityManager.remove(author);

            transaction.commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseErrorException(e.getMessage());
        }
    }
}