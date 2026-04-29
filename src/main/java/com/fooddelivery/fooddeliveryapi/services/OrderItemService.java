package com.fooddelivery.fooddeliveryapi.services;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.OrderItemCreateDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.response.CartActionResponseDto;

public interface OrderItemService {

    CartActionResponseDto addOrderItem(OrderItemCreateDto orderItemCreateDto, String username);
}
