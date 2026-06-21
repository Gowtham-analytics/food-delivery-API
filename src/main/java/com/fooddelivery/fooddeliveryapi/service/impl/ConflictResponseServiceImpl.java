package com.fooddelivery.fooddeliveryapi.services.impl;

import com.fooddelivery.fooddeliveryapi.domain.dto.response.RestaurantConflictResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.entities.Order;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;
import com.fooddelivery.fooddeliveryapi.services.ConflictResponseService;
import org.springframework.stereotype.Service;

@Service
public class ConflictResponseServiceImpl implements ConflictResponseService {

    @Override
    public RestaurantConflictResponseDto createConflictResponseDto(boolean isConflict, Restaurant restaurant, Order order) {
        return new RestaurantConflictResponseDto(
                isConflict,
                restaurant,
                order
        );
    }
}
