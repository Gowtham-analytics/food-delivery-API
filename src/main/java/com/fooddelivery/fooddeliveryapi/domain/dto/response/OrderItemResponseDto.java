package com.fooddelivery.fooddeliveryapi.domain.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemResponseDto(

        @NotNull(message = "Quantity cannot be null!")
        @Positive(message = "Quantity cannot be negative or zero!")
        int quantity
) {
}
