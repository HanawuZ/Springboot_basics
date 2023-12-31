package com.example.springboot.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springboot.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, String>{

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findUserByUsername(String username);
    
}
