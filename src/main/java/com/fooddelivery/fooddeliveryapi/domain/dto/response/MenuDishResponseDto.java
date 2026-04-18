package com.fooddelivery.fooddeliveryapi.domain.dto.response;

public record MenuDishResponseDto(
        String name,
        Double price,
        Boolean veg
) {
}
