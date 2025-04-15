package com.challenge.LaunchCode.controllers;

import com.challenge.LaunchCode.models.User;
import com.challenge.LaunchCode.payloads.requests.EditUserRequest;
import com.challenge.LaunchCode.payloads.responses.UserResponseDTO;
import com.challenge.LaunchCode.repositories.UserRepository;
import com.challenge.LaunchCode.security.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PutMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(
            @RequestBody @Valid EditUserRequest editUserRequest,
            Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>("User is not authenticated", HttpStatus.UNAUTHORIZED);
        }

        String username = authentication.getName();
        System.out.println("Updating profile for username: " + username);

        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.err.println("User not found with username: " + username);
                    return new RuntimeException("User not found");
                });

        if (editUserRequest.getFirstName() != null) existingUser.setFirstName(editUserRequest.getFirstName());
        if (editUserRequest.getLastName() != null) existingUser.setLastName(editUserRequest.getLastName());
        if (editUserRequest.getEmail() != null) existingUser.setEmail(editUserRequest.getEmail());
        if (editUserRequest.getBio() != null) existingUser.setBio(editUserRequest.getBio());
        if (editUserRequest.getPassword() != null && !editUserRequest.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(editUserRequest.getPassword());
            existingUser.setPassword(hashedPassword);
        }

        User savedUser = userRepository.save(existingUser);

        UserResponseDTO responseDTO = new UserResponseDTO();

        responseDTO.getUsername(savedUser.getUsername());
        responseDTO.setFirstName(savedUser.getFirstName());
        responseDTO.setLastName(savedUser.getLastName());

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletResponse response) {
        jwtUtils.clearJwtCookie(response);
        return ResponseEntity.ok("Logged out successfully.");
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<?> deleteAccount(HttpServletResponse response, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>("User is not authenticated", HttpStatus.UNAUTHORIZED);
        }

        String username = authentication.getName();
        System.out.println("Deleting account for username: " + username);

        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        userRepository.delete(userOpt.get());

        jwtUtils.clearJwtCookie(response);

        return ResponseEntity.ok("User account deleted successfully and logged out.");
    }
}
