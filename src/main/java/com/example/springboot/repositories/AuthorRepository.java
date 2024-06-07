package com.example.springboot.repositories;

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

    private EntityManager em;

    @Autowired
    public AuthorRepository(EntityManagerFactory entityManagerFactory) {
        this.em = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Author> findAll() {

        try {
            final String queryString = "SELECT * FROM authors";
    
            Query query = em.createNativeQuery(queryString, Author.class);
    
            // Get result query
            List<Author> result = query.getResultList();
            if (result.isEmpty()) {
                return null;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Author> findById(String id) {
        try {
            final String queryString = String.format("SELECT * FROM authors WHERE id = %s", id);
    
            Query query = em.createNativeQuery(queryString, Author.class);
    
            // Get result query
            List<Author> results = query.getResultList();
    
            if (results.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(results.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public boolean createAuthors(List<Author> author) {
        try {
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            for (Author a : author) {
                em.persist(a);
            }

            transaction.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    @Modifying
    public boolean updateAuthor(String id, Author author) {
        try {
            Author foundAuthor = em.find(Author.class, id);
            if (foundAuthor == null) {
                return false;
            }
            final String queryString = "UPDATE authors SET name = :name, dob = :dob, updated_by = :updatedBy, updated_date = :updatedDate WHERE id = :id";

            // Begin transaction
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();
            
            Query query = em.createNativeQuery(queryString, Author.class);
            
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
            return false;
        }
    }

    public boolean deleteAuthor(String id) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            Author author = em.find(Author.class, id);
            em.remove(author);

            transaction.commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}