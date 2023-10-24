package com.example.springboot.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot.models.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    
}
