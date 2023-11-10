package com.example.springboot.controllers;
import com.example.springboot.models.Publisher;
import com.example.springboot.repositories.PublisherRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/publishers")
@CrossOrigin

@RestController
public class PublisherController {

    @Autowired
    private final PublisherRepository publisherRepository;

    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    // Get all publishers
    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        try {
            return ResponseEntity.ok(publisherRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get publisher by id
    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable Long id) {
        try {
            Publisher publisher = publisherRepository.findById(id).orElse(null);
            if (publisher == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(publisher);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Update publisher by id
    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher publisher) {
        try {
            Publisher existingPublisher = publisherRepository.findById(id).orElse(null);
            if (existingPublisher == null) {
                return ResponseEntity.notFound().build();
            }

            existingPublisher.setPublisherName(publisher.getPublisherName());
            existingPublisher.setAddress(publisher.getAddress());
            existingPublisher.setPhoneNumber(publisher.getPhoneNumber());
            existingPublisher.setEmail(publisher.getEmail());

            Publisher savedPublisher = publisherRepository.save(existingPublisher);
            return ResponseEntity.ok(savedPublisher);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete publisher
    @DeleteMapping("/{id}")
    public ResponseEntity<Publisher> deletePublisher(@PathVariable Long id) {
        try {
            Publisher publisher = publisherRepository.findById(id).orElse(null);
            if (publisher == null) {
                return ResponseEntity.notFound().build();
            }
            publisherRepository.delete(publisher);
            return ResponseEntity.ok(publisher);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
