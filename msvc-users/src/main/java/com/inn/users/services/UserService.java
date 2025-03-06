package com.inn.users.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inn.users.entities.Role;
import com.inn.users.entities.User;
import com.inn.users.repositories.RoleRepository;
import com.inn.users.repositories.UserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoles(getRoles(user));
        user.setEnabled(true);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Optional<User> update(User user, Long id) {
        
        Optional<User> userOptional = this.findById(id);
        
        return userOptional.map(userDb -> {
            userDb.setEmail(user.getEmail());
            userDb.setUsername(user.getUsername());
            if (user.getEnabled() == null) {
                userDb.setEnabled(true);
            } else {
                userDb.setEnabled(user.getEnabled());
            }
            userDb.setRoles(getRoles(user));
            
            return Optional.of(userRepository.save(userDb));
        }).orElseGet(() -> Optional.empty());

    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private List<Role> getRoles(User user) {
        List<Role> roles = new ArrayList<>();
  
        if (user.isAdmin()) {
            Optional<Role> adminRoleOptional = roleRepository.findByName("ROLE_ADMIN");
            adminRoleOptional.ifPresent(roles::add);
        }else {
        	Optional<Role> adminRoleOptional = roleRepository.findByName("ROLE_USER");
            adminRoleOptional.ifPresent(roles::add);
            
            if(user.getRoles()!=null) {
                for (Role role : user.getRoles()) {
                    Optional<Role> roleOptional = roleRepository.findByName(role.getName().toUpperCase());
                    roleOptional.ifPresent(roles::add);
        		}
            }
        }
        
        return roles;
    }
}