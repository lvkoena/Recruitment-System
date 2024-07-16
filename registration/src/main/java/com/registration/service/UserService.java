package com.registration.service;

import com.registration.model.User;
import com.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists. Try another one");
        }

        return userRepository.save(user);
    }
}
