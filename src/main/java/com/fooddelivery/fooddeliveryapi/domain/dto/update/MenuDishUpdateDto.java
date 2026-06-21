package com.fooddelivery.fooddeliveryapi.domain.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record MenuDishUpdateDto(

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
