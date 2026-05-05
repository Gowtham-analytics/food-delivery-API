package com.fooddelivery.fooddeliveryapi.domain.dto.response;

import com.fooddelivery.fooddeliveryapi.enums.OrderStatus;

public record OrderResponseDto(

        Long orderId,
        String restaurantName,
        Integer numberOfItems,
        Double totalPrice,
        OrderStatus orderStatus
) {
}
