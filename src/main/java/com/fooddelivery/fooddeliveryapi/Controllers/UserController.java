package com.fooddelivery.fooddeliveryapi.Controllers;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.UserCreateDto;
import com.fooddelivery.fooddeliveryapi.services.Impl.JwtServiceImpl;
import com.fooddelivery.fooddeliveryapi.services.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    private final AuthenticationManager authenticationManager;

    private final JwtServiceImpl jwtSecurity;

    public UserController(UserServiceImpl userServiceImpl, AuthenticationManager authenticationManager, JwtServiceImpl jwtSecurity) {
        this.userServiceImpl = userServiceImpl;
        this.authenticationManager = authenticationManager;
        this.jwtSecurity = jwtSecurity;
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
            @RequestBody @Valid UserCreateDto userCreateDto
    )
    {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userCreateDto.username(),
                        userCreateDto.password())
        );

        if(authenticate.isAuthenticated()) {

            String role = authenticate
                    .getAuthorities()
                    .stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .map(r -> r.replace("ROLE_", ""))
                    .orElse(null);

            return jwtSecurity.generateToken(userCreateDto.username(), role);
        }

        return null;
    }
}