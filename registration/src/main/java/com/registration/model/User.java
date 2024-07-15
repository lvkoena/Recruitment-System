package com.registration.model;

import jakarta.persistence.Entity;

@Entity
public class User {
    private Long id;
    private String name;
    private String surname;
    private String password;
    private String email;
}
