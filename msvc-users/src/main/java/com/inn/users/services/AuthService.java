package com.inn.users.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.users.entities.User;
import com.inn.users.repositories.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserService userService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtService jwtService;

    public User saveUser(User user) {
        return userService.save(user);
    }

    public String generateToken(String username) {
    	Optional<User> optionalUser = repository.findByUsername(username);
        return jwtService.generateToken(optionalUser.get());
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}