package com.fooddelivery.fooddeliveryapi.domain.dto.response;

import com.fooddelivery.fooddeliveryapi.domain.entity.Order;
import com.fooddelivery.fooddeliveryapi.domain.entity.Restaurant;

public record RestaurantConflictResponseDto(
        Boolean ifConflict,
        Restaurant restaurant,
        Order order
) {
}
