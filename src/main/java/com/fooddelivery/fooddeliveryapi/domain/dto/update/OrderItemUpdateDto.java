package com.fooddelivery.fooddeliveryapi.domain.dto.update;

import jakarta.validation.constraints.Positive;

public record OrderItemUpdateDto(

        @Positive(message = "Quantity cannot be negative or zero!")
        int quantity
) {
}
