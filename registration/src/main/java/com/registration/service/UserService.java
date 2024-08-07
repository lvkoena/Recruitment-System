package com.registration.service;

import com.registration.model.User;
import com.registration.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        logger.info("Attempting to register user with username: {}", user.getUsername());

        if (userRepository.existsByUsername(user.getUsername())) {
            logger.warn("Registration failed: username '{}' already exists", user.getUsername());
            throw new RuntimeException("username already exists. Try again!!");
        }

        User savedUser = userRepository.save(user);
        logger.info("User registered successfully with username: {}", user.getUsername());
        return savedUser;
    }

    public User findByUsername(String username) {
        logger.info("Attempting to find the user with username: {}", username);

        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.warn("User not found with username: {}", username);
        } else {
            logger.info("User found with username: {}", username);
        }

        return user;
    }
}
