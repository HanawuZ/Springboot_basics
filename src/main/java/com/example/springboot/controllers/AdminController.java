package com.example.springboot.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.models.Admin;
import com.example.springboot.repositories.AdminRepository;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private final AdminRepository adminRepository;
    

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }   

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        try {
            Admin admin = adminRepository.findById(id).orElse(null);
            if (admin == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(admin);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
