package com.example.springboot.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
    
}
