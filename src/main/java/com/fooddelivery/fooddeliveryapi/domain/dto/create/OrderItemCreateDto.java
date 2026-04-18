package com.fooddelivery.fooddeliveryapi.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemCreateDto(

        Long menuDishId,

        @NotNull(message = "Quantity cannot be null!")
        @Positive(message = "Quantity cannot be negative or zero!")
        int quantity
) {
}
