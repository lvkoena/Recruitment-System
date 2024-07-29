package com.login.service;

import com.login.model.User;
import com.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String REGISTRATION_SERVICE_URL = "http://localhost:8080/register";

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            // Fetch user from the registration service
            user = fetchUserFromRegistrationService(username);
            if (user == null) {
                throw new RuntimeException("Invalid username. User not found!");
            }
            // Save the user to the login service's database
            userRepository.save(user);
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password. Try again with correct credentials!");
        }

        return user;
    }

    private User fetchUserFromRegistrationService(String username) {
        try {
            return restTemplate.getForObject(REGISTRATION_SERVICE_URL + "/by-username/" + username, User.class);
        } catch (Exception e) {
            return null;
        }
    }
}
