package com.example.springboot.repositories;

import com.example.springboot.models.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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

    @Autowired
    public UserRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    @Transactional
    public Optional<User> getUserByUsername(String username) {
        try {
            final String queryString = String.format("SELECT * FROM users WHERE username = '%s'", username);
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
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public boolean createUser(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
