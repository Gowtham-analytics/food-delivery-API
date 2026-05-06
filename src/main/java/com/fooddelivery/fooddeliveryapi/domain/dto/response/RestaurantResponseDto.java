package com.fooddelivery.fooddeliveryapi.domain.dto.response;

public record RestaurantResponseDto(
        Long id,
        String name,
        String location
) {
}