package com.challenge.LaunchCode.controllers;

import com.challenge.LaunchCode.models.User;
import com.challenge.LaunchCode.payloads.requests.EditUserRequest;
import com.challenge.LaunchCode.repositories.UserRepository;
import com.challenge.LaunchCode.security.jwt.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(
            @RequestBody @Valid EditUserRequest editUserRequest,
            Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>("User is not authenticated", HttpStatus.UNAUTHORIZED);
        }

        String username = authentication.getName();

        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setFirstName(editUserRequest.getFirstName());
        existingUser.setLastName(editUserRequest.getLastName());
        existingUser.setEmail(editUserRequest.getEmail());
        existingUser.setBio(editUserRequest.getBio());

        if (editUserRequest.getPassword() != null && !editUserRequest.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(editUserRequest.getPassword());
            existingUser.setPassword(hashedPassword);
        }
        User savedUser = userRepository.save(existingUser);

        return ResponseEntity.ok(savedUser);
    }

    }
