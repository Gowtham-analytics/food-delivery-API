package com.fooddelivery.fooddeliveryapi.services;

import com.fooddelivery.fooddeliveryapi.domain.entities.Order;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;
import com.fooddelivery.fooddeliveryapi.domain.entities.UserEntity;
import com.fooddelivery.fooddeliveryapi.enums.OrderStatus;

public interface OrderService {

    Order getOrCreateOrder(Restaurant restaurant, UserEntity userEntity, OrderStatus orderStatus);
    Order updateOrderTotal(Order order);
}
