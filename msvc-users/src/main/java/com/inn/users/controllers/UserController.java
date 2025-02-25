package com.inn.users.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inn.users.dtos.UserDto;
import com.inn.users.entities.User;
import com.inn.users.exceptions.ResourceNotFoundException;
import com.inn.users.services.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;
    
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<UserDto> getAllUser() {
        return userService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(user));
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getUserByLogin(@PathVariable String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this username :: " + username));
        return ResponseEntity.ok(convertToDTO(user));
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDto userDTO) {
        User user = convertToEntity(userDTO);
        return convertToDTO(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDTO) {
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        modelMapper.map(userDTO, user);
        return ResponseEntity.ok(convertToDTO(userService.save(user)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    private UserDto convertToDTO(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToEntity(UserDto userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}