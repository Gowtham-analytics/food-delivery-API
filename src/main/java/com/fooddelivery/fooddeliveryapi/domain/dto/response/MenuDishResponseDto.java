package com.fooddelivery.fooddeliveryapi.domain.dto.response;

public record MenuDishResponseDto(
        Long id,
        String name,
        Double price,
        Boolean veg
) {
}
