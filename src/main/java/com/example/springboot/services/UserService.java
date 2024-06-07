package com.example.springboot.services;

import com.example.springboot.models.User;
import com.example.springboot.models.UserInfoDetail;
import com.example.springboot.models.requests.SignupRequest;
import com.example.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

/**
 * InnerUserService
 */
interface IUserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);

    boolean createUser(SignupRequest userSignupRequest);
}

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            Optional<User> userDetail = userRepository.getUserByUsername(username);
            if (userDetail.isEmpty()) {
                return null;
            }
            return userDetail.map(UserInfoDetail::new)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createUser(SignupRequest userSignupRequest) {
        try {
            User newUser = new User();

            newUser.setId(UUID.randomUUID().toString());
            newUser.setPassword(encoder.encode(userSignupRequest.getPassword()));
            newUser.setUsername(userSignupRequest.getUsername());
            newUser.setFirstName(userSignupRequest.getFirstName());
            newUser.setLastName(userSignupRequest.getLastName());
            newUser.setEmail(userSignupRequest.getEmail());
            newUser.setRole(userSignupRequest.getRole());
            newUser.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            newUser.setCreatedBy("system");
            newUser.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            newUser.setUpdatedBy("system");
            

            boolean isComplete = userRepository.createUser(newUser);
            if (!isComplete) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
