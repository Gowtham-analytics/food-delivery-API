package com.fooddelivery.fooddeliveryapi.domain.dto.response;

import com.fooddelivery.fooddeliveryapi.domain.entities.Order;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;

public record RestaurantConflictResponseDto(
        Boolean ifConflict,
        Restaurant restaurant,
        Order order
) {
}
