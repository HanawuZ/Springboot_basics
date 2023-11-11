package com.example.springboot.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.models.User;

public interface UserRepository extends JpaRepository<User, String>{
    
}
