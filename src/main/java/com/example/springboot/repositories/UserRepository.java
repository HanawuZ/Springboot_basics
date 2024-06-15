package com.example.springboot.repositories;

import com.example.springboot.exception.DatabaseErrorException;
import com.example.springboot.models.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

/**
 * InnerUserRepository
 */
interface IUserRepository {
    Optional<User> getUserByUsername(String username);

    boolean createUser(User user);
}

@Repository
public class UserRepository implements IUserRepository {

    private EntityManager entityManager;

    public UserRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    @Transactional
    public Optional<User> getUserByUsername(String username) {
        try {
            final String queryString = String.format("SELECT * FROM users WHERE users.username = '%s' OR users.email = '%s'", username, username);
            entityManager.getTransaction().begin();

            Query query = entityManager.createNativeQuery(queryString, User.class);
            List<User> results = query.getResultList();

            entityManager.getTransaction().commit();
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
    public boolean createUser(User user) {
        try {

            final String queryString = 
                "INSERT INTO users (id, firstname, lastname, email, username, password, role, created_by, created_date, updated_by, updated_date) "+
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            Query query = entityManager.createNativeQuery(queryString);
            query.setParameter(1, user.getId());
            query.setParameter(2, user.getFirstName());
            query.setParameter(3, user.getLastName());
            query.setParameter(4, user.getEmail());
            query.setParameter(5, user.getUsername());
            query.setParameter(6, user.getPassword());
            query.setParameter(7, user.getRole());
            query.setParameter(8, user.getCreatedBy());
            query.setParameter(9, user.getCreatedDate());
            query.setParameter(10, user.getUpdatedBy());
            query.setParameter(11, user.getUpdatedDate());
            
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseErrorException(e.getMessage());
        }
    }
}
