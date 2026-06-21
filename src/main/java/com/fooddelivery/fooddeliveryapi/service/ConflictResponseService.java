package com.fooddelivery.fooddeliveryapi.service;

import com.fooddelivery.fooddeliveryapi.domain.dto.response.RestaurantConflictResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.entity.Order;
import com.fooddelivery.fooddeliveryapi.domain.entity.Restaurant;

public interface ConflictResponseService {

    RestaurantConflictResponseDto createConflictResponseDto(boolean isConflict, Restaurant restaurant, Order order);
}
