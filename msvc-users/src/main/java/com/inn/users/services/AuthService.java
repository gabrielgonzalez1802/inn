package com.inn.users.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inn.users.entities.User;
import com.inn.users.repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(User credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return "user added to the system";
    }

    public String generateToken(String username) {
    	Optional<User> optionalUser = repository.findByUsername(username);
        return jwtService.generateToken(optionalUser.get());
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}