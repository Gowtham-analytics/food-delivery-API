package com.fooddelivery.fooddeliveryapi.domain.dto.request;

import com.fooddelivery.fooddeliveryapi.domain.entities.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDto(

        @NotBlank(message = "Username cannot be empty!")
        @Size(min = 3, max = 256, message = "Username should only have 3 - 256 characters")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers, and underscores")
        String username,

        @NotBlank(message = "Password cannot be empty!")
        @Size(min = 6, max = 100, message = "Password should only have 6 - 100 characters")
        String password,

        UserRole role
) {
}
