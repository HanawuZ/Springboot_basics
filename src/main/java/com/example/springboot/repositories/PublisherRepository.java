package com.example.springboot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springboot.models.Author;
import com.example.springboot.models.Publisher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

/**
 * InnerPublisherRepository
 */
interface IPublisherRepository {

    List<Publisher> findAll();

    Optional<Publisher> findById(String id);

    boolean createPublisher(Publisher publisher);

    boolean updatePublisher(String id, Publisher publisher);

    boolean deletePublisher(String id);
}

@Repository
public class PublisherRepository implements IPublisherRepository {

    private EntityManager entityManager;

    @Autowired
    public PublisherRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    @Transactional
    public List<Publisher> findAll() {
        try {
            final String queryString = "SELECT * FROM publishers";
            
            entityManager.getTransaction().begin();
            
            List<Publisher> result = entityManager.createNativeQuery(queryString, Publisher.class).getResultList();

            entityManager.getTransaction().commit();

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
    @Transactional
    public Optional<Publisher> findById(String id) {
        try {
            final String queryString = String.format("SELECT * FROM publishers WHERE id = %s", id);

            Query query = entityManager.createNativeQuery(queryString, Publisher.class);

            entityManager.getTransaction().begin();

            List<Publisher> results = query.getResultList();

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
    public boolean createPublisher(Publisher publisher) {
        try {
            entityManager.getTransaction().begin();

            entityManager.persist(publisher);

            entityManager.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean updatePublisher(String id, Publisher publisher) {
        try {
            Publisher foundPublisher = entityManager.find(Publisher.class, id);
            if (foundPublisher == null) {
                return false;
            }

            final String quString = "UPDATE publishers SET name = :name, address = :address, phoneNumber = :phoneNumber, email = :email WHERE id = :id";
            entityManager.getTransaction().begin();

            Query query = entityManager.createNativeQuery(quString, Publisher.class);
            query.setParameter("name", publisher.getName());
            query.setParameter("address", publisher.getAddress());
            query.setParameter("phoneNumber", publisher.getPhoneNumber());
            query.setParameter("email", publisher.getEmail());
            query.setParameter("id", id);

            query.executeUpdate();

            entityManager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deletePublisher(String id) {
        try {
            Publisher foundPublisher = entityManager.find(Publisher.class, id);
            if (foundPublisher == null) {
                return false;
            }
            entityManager.getTransaction().begin();

            entityManager.remove(foundPublisher);

            entityManager.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
