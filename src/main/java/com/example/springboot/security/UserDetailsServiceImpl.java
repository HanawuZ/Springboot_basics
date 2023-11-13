package com.example.springboot.security;

import com.example.springboot.models.User;
import com.example.springboot.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


// import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Find the user by username
        User user = userRepository.findUserByUsername(username);

        // If the user is not found, throw an exception
        if (user == null) {
            throw new UsernameNotFoundException("user not found with this username");
        }

        List<String> roles = new ArrayList<>();
        roles.add(user.getRole());
        // Return the user details
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                            .username(user.getUsername())
                            .password(user.getPassword())
                            .roles(roles.toArray(new String[0]))
                            .build();
        return userDetails;
    }
}