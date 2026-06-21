package com.fooddelivery.fooddeliveryapi.service.impl;

import com.fooddelivery.fooddeliveryapi.domain.dto.response.RestaurantConflictResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.entity.Order;
import com.fooddelivery.fooddeliveryapi.domain.entity.Restaurant;
import com.fooddelivery.fooddeliveryapi.service.ConflictResponseService;
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
