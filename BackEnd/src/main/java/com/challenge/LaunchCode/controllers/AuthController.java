package com.challenge.LaunchCode.controllers;

import com.challenge.LaunchCode.models.ERole;
import com.challenge.LaunchCode.models.Role;
import com.challenge.LaunchCode.models.User;
import com.challenge.LaunchCode.payloads.requests.LoginRequest;
import com.challenge.LaunchCode.payloads.requests.SignupRequest;
import com.challenge.LaunchCode.payloads.responses.JwtResponse;
import com.challenge.LaunchCode.payloads.responses.MessageResponse;
import com.challenge.LaunchCode.repositories.RoleRepository;
import com.challenge.LaunchCode.repositories.UserRepository;
import com.challenge.LaunchCode.security.jwt.JwtUtils;
import com.challenge.LaunchCode.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                roles));
    }

    @PostMapping(value = "/signup", produces = "application/json")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        System.out.println("Signup request: " + signupRequest);

        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        if (!signupRequest.getPassword().equals(signupRequest.getVerifiedPassword())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Passwords do not match!"));
        }

        User user = new User(signupRequest.getUsername(),
                signupRequest.getFirstName(),
                signupRequest.getLastName(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);
            if (userRole.isPresent()) {
                roles.add(userRole.get());
            } else {
                Role newUserRole = new Role(ERole.ROLE_USER);
                roleRepository.save(newUserRole);
                roles.add(newUserRole);
            }
        } else {
            strRoles.forEach(role -> {
                switch (role.toLowerCase()) {
                    case "admin":
                        Optional<Role> adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
                        if (adminRole.isPresent()) {
                            roles.add(adminRole.get());
                        } else {
                            Role newAdminRole = new Role(ERole.ROLE_ADMIN);
                            roleRepository.save(newAdminRole);
                            roles.add(newAdminRole);
                        }
                        break;

                    case "mod":
                        Optional<Role> modRole = roleRepository.findByName(ERole.ROLE_MODERATOR);
                        if (modRole.isPresent()) {
                            roles.add(modRole.get());
                        } else {
                            Role newModRole = new Role(ERole.ROLE_MODERATOR);
                            roleRepository.save(newModRole);
                            roles.add(newModRole);
                        }
                        break;

                    default:
                        Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);
                        if (userRole.isPresent()) {
                            roles.add(userRole.get());
                        } else {
                            Role newUserRole = new Role(ERole.ROLE_USER);
                            roleRepository.save(newUserRole);
                            roles.add(newUserRole);
                        }
                        break;
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}