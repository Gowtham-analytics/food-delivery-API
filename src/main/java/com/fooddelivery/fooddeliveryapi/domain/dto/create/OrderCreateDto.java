package com.fooddelivery.fooddeliveryapi.domain.dto.create;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderCreateDto(
        @NotNull(message = "Order cannot be null!")
        @NotEmpty(message = "Order cannot be empty!")
        List<OrderItemCreateDto> items
) {
}
