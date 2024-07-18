package com.registration.controller;

import com.registration.model.User;
import com.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
}
