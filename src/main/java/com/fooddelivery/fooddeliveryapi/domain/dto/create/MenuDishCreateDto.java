package com.fooddelivery.fooddeliveryapi.domain.dto.create;

import jakarta.validation.constraints.*;

public record MenuDishCreateDto(
        @NotBlank(message = "Menu Dish name cannot be blank!")
        @Size(max = 256, message = "Menu Dish name should not contain more than 256 characters!")
        String name,

        @NotNull(message = "Price cannot be null!")
        @Positive(message = "Price cannot be negative or zero!")
        Double price,

        @NotNull(message = "Boolean cannot be null!")
        Boolean veg
) {
}