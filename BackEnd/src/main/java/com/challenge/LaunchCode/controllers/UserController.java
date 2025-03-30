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

    @Autowired
    JwtUtils jwtUtils;

    @PutMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(
            @RequestBody @Valid EditUserRequest editUserRequest,
            Authentication authentication) {

        // Check if the user is authenticated
        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>("User is not authenticated", HttpStatus.UNAUTHORIZED);
        }

        // Get the authenticated user's username
        String username = authentication.getName();

        // Fetch the existing user from the database
        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update the user's profile details
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
