package com.challenge.LaunchCode.security.services;

import com.challenge.LaunchCode.models.User;
import com.challenge.LaunchCode.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            user = new User();
            user.setUsername(username);
            userRepository.save(user);
        }

        return UserDetailsImpl.build(user);
    }
}