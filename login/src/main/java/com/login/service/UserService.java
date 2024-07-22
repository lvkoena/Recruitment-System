package com.login.service;

import com.login.model.User;
import com.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired

    private UserRepository userRepository;

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        throw new RuntimeException("Invalid username or password. Try again with correct credintials");
    }
}
