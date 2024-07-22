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

        if (user == null) {
            throw new RuntimeException("Invalid username. User not found!");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password. Try again with correct credentials!");
        }

        return user;
    }
}
