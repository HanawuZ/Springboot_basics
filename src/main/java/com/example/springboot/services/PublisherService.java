package com.example.springboot.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.springboot.models.Publisher;
import com.example.springboot.models.requests.PublisherRequest;
import com.example.springboot.repositories.AuthorRepository;
import com.example.springboot.repositories.PublisherRepository;

/**
 * InnerPublisherService
 */
interface IPublisherService {
    List<Publisher> getAllPublishers();

    Optional<Publisher> getPublisherById(String id);

    boolean createPublisher(PublisherRequest publisherRequest);

    boolean updatePublisher(String id, PublisherRequest publisherRequest);

    boolean deletePublisher(String id);
}

@Service
public class PublisherService implements IPublisherService {

    PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> getAllPublishers() {
        try {
            List<Publisher> publishers = publisherRepository.findAll();

            if (publishers == null) {
                return null;
            }
            return publishers;

        } catch (Exception e) {
            return null;
        }
    }

    public Optional<Publisher> getPublisherById(String id) {
        try {
            Optional<Publisher> publisher = publisherRepository.findById(id);
            if (publisher == null) {
                return null;
            }
            return publisher;

        } catch (Exception e) {
            return null;
        }
    }

    public boolean createPublisher(PublisherRequest publisherRequest) {
        try {

            Publisher publisher = new Publisher();
            publisher.setId(UUID.randomUUID().toString());
            publisher.setName(publisherRequest.getName());
            publisher.setEmail(publisherRequest.getEmail());
            publisher.setAddress(publisherRequest.getAddress());
            publisher.setPhoneNumber(publisherRequest.getPhoneNumber());
            publisher.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            publisher.setCreatedBy("system");
            publisher.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            publisher.setUpdatedBy("system");

            boolean isComplete = publisherRepository.createPublisher(publisher);

            if (!isComplete) {
                return false;
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean updatePublisher(String id, PublisherRequest publisherRequest) {
        try {
            Publisher publisher = new Publisher();

            publisher.setName(publisherRequest.getName());
            publisher.setEmail(publisherRequest.getEmail());
            publisher.setAddress(publisherRequest.getAddress());
            publisher.setPhoneNumber(publisherRequest.getPhoneNumber());
            publisher.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            publisher.setUpdatedBy("system");
            boolean isComplete = publisherRepository.updatePublisher(id, publisher);

            if (!isComplete) {  
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deletePublisher(String id) {
        try {
            boolean isComplete = publisherRepository.deletePublisher(id);
            if (!isComplete) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
