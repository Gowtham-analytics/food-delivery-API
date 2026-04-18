package com.fooddelivery.fooddeliveryapi.domain.dto.update;

public record MenuDishUpdateDto(
        String name,
        Double price,
        Boolean veg
) {
}
