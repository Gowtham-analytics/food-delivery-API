package com.fooddelivery.fooddeliveryapi.domain.dto.create;

import jakarta.validation.constraints.NotBlank;

public record CustomerCreateDto(
        String name,
        Integer age,
        String address
) {
}