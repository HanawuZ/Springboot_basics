package com.example.springboot.services;

import com.example.springboot.lib.http.BaseResponse;
import com.example.springboot.lib.http.StatusCode;
import com.example.springboot.models.DataResult;
import com.example.springboot.models.User;
import com.example.springboot.models.UserInfoDetail;
import com.example.springboot.models.requests.SignupRequest;
import com.example.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * InnerUserService
 */
interface IUserService extends UserDetailsService {
    // UserDetails loadUserByUsername(String usernameOrEmail);

    DataResult<User> getUserData(String usernameOrEmail);
    DataResult<String> createUser(SignupRequest userSignupRequest);
}

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) {
        try {
            Optional<User> userDetail = userRepository.getUserByUsername(usernameOrEmail);
            if (userDetail.isEmpty()) {
                return null;
            }
            return userDetail.map(UserInfoDetail::new)
                    .orElseThrow(() -> new UsernameNotFoundException("Cannot find username/email" + usernameOrEmail));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DataResult<User> getUserData(String usernameOrEmail) {
        try {
            Optional<User> user = userRepository.getUserByUsername(usernameOrEmail);
            if (user.isEmpty()) {
                return new DataResult<User>(4004, null, "Not found user with username/email: " + usernameOrEmail);
            }

            return new DataResult<User>(2000, user.get(), null);

        } catch (Exception e) {
            e.printStackTrace();
            return new DataResult<User>(5000, null, "Internal server error, failed to get user");
        }
    }


    public DataResult<String> createUser(SignupRequest userSignupRequest) {
        try {

            // Validation
            // Check is email pattern is valid
            // ^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$
            Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
            Matcher matcher = pattern.matcher(userSignupRequest.getEmail());
            boolean isEmailMatch = matcher.matches();
            if (!isEmailMatch) {
                return new DataResult<String>(4000, null, "Invalid email format");
            }

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
                return new DataResult<String>(5000, null, "Failed to create user");
            }
            return new DataResult<String>(2001, null, "User is created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new DataResult<String>(5000, null, "Internal server error, failed to create user");
        }
    }
}
