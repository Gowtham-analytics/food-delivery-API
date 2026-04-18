package com.fooddelivery.fooddeliveryapi.domain.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RestaurantCreateDto(
        @NotBlank(message = "Restaurant name cannot be blank!")
        @Size(max = 256, message = "Restaurant name should not contain more than 256 characters!")
        String name,

        @NotBlank(message = "Restaurant location cannot be blank!")
        @Size(max = 256, message = "Restaurant location should not contain more than 256 characters!")
        String location
) {
}
