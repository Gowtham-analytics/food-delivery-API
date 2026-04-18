package com.fooddelivery.fooddeliveryapi.domain.dto.update;

import jakarta.validation.constraints.Size;

public record RestaurantUpdateDto(
        @Size(max = 256, message = "Restaurant name should not contain more than 256 characters!")
        String name,
        @Size(max = 256, message = "Restaurant location should not contain more than 256 characters!")
        String location
) {
}
