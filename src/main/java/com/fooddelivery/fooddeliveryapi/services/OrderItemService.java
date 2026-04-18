package com.fooddelivery.fooddeliveryapi.services;

import com.fooddelivery.fooddeliveryapi.domain.entities.OrderItem;

public interface OrderItemService {

    OrderItem createOrderItem(Long customerId, Long menuDishId, OrderItem orderItem);
}
