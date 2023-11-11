package com.example.springboot.controllers;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.models.Admin;
import com.example.springboot.repositories.AdminRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
class SigninBody {
    @JsonProperty("id")
    Long id;

    @JsonProperty("password")
    String password;
}

@RestController
public class AuthController {

    /* 
     const compareHash = async (plainText, hashText) => {
  return new Promise((resolve, reject) => {
    bcrypt.compare(plainText, hashText, (err, data) => {
      if (err) {
        reject(new Error('Error bcrypt compare'))
      } else {
        resolve({ status: data });
      }
    })
  });
}

     */

    @Autowired
    private final AdminRepository adminRepository;

    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    public AuthController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @PostMapping("/signin/admin")
    public ResponseEntity<Admin> adminSignIn(@RequestBody SigninBody body) {
        try {
            Admin admin = adminRepository.findById(body.getId()).orElse(null);
            if (admin == null) {
                return ResponseEntity.notFound().build();
            } 

            System.out.println(body.getPassword());
            System.out.println(body.getId());
            boolean passChecker = bcrypt.matches(body.getPassword(), admin.getPassword());
            System.out.println(passChecker);
            
            if (!passChecker) {
                return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
            }
            
            return ResponseEntity.ok(admin);


        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    
}
