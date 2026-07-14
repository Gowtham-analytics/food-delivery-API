package com.fooddelivery.fooddeliveryapi.domain.dto.response;

import com.fooddelivery.fooddeliveryapi.enumerator.CartStatus;

public record CartActionResponseDto(
        CartStatus cartStatus,
        String restaurant,
        String conflictRestaurant,
        Integer numberOfItems,
        Long orderId
) {
}