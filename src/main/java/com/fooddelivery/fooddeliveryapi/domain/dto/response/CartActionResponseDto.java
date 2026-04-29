package com.fooddelivery.fooddeliveryapi.domain.dto.response;

import com.fooddelivery.fooddeliveryapi.enums.CartStatus;

public record CartActionResponseDto(
        CartStatus cartStatus,
        String restaurant,
        String conflictRestaurant,
        Integer numberOfItems
) {
}