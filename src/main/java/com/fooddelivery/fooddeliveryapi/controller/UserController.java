package com.fooddelivery.fooddeliveryapi.controller;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.UserCreateDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.request.UserLoginRequestDto;
import com.fooddelivery.fooddeliveryapi.service.impl.JwtServiceImpl;
import com.fooddelivery.fooddeliveryapi.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    private final AuthenticationManager authenticationManager;

    private final JwtServiceImpl jwtServiceImpl;

    public UserController(UserServiceImpl userServiceImpl, AuthenticationManager authenticationManager, JwtServiceImpl jwtServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.authenticationManager = authenticationManager;
        this.jwtServiceImpl = jwtServiceImpl;
    }

    @PostMapping("/sign-up")
    public void createUser(
            @RequestBody @Valid UserCreateDto userCreateDto
    )
    {
        userServiceImpl.createUser(
                userCreateDto.username(),
                userCreateDto.password(),
                userCreateDto.role());
    }

    @PostMapping("/log-in/authenticate")
    public String authenticateUser(
            @RequestBody @Valid UserLoginRequestDto userLoginRequestDto
    )
    {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequestDto.username(),
                        userLoginRequestDto.password())
        );

        if(authenticate.isAuthenticated()) {

            String role = authenticate
                    .getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .filter(Objects::nonNull)
                    .filter(auth -> auth.startsWith("ROLE_"))
                    .findFirst()
                    .map(r -> r.replace("ROLE_", ""))
                    .orElse(null);

            return jwtServiceImpl.generateToken(userLoginRequestDto.username(), role);
        }

        return null;
    }
}