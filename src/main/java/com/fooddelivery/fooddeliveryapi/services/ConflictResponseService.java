package com.fooddelivery.fooddeliveryapi.services;

import com.fooddelivery.fooddeliveryapi.domain.dto.response.RestaurantConflictResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.entities.Order;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;

public interface ConflictResponseService {

    RestaurantConflictResponseDto createConflictResponseDto(boolean isConflict, Restaurant restaurant, Order order);
}
