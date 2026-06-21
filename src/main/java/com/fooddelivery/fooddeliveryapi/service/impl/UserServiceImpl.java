package com.fooddelivery.fooddeliveryapi.services.impl;

import com.fooddelivery.fooddeliveryapi.exceptions.UsernameAlreadyExistsException;
import com.fooddelivery.fooddeliveryapi.domain.entities.UserEntity;
import com.fooddelivery.fooddeliveryapi.enums.UserRole;
import com.fooddelivery.fooddeliveryapi.repositories.UserRepository;
import com.fooddelivery.fooddeliveryapi.services.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity getUserFromUsername(String username) {

        return userRepository
                .findByUsernameAndIsActive(username, true)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = getUserFromUsername(username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

        user.getRole().getPermissions().forEach(permission -> {
            authorities.add(new SimpleGrantedAuthority(permission.getCode()));
        });

        return User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }

    @Override
    public void createUser(String username, String password, UserRole userRole) {

        Boolean userExists = userRepository.existsByUsername(username);

        if(userExists) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        if(userRole == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }

        LocalDateTime now = LocalDateTime.now();

        UserEntity user = new UserEntity(
                null,
                username,
                null,
                passwordEncoder.encode(password),
                null,
                null,
                null,
                null,
                null,
                userRole,
                true,
                null,
                null,
                now,
                now
        );

        userRepository.save(user);
    }
}