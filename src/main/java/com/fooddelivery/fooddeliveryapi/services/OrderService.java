package com.fooddelivery.fooddeliveryapi.services;

import com.fooddelivery.fooddeliveryapi.domain.entities.Order;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;
import com.fooddelivery.fooddeliveryapi.domain.entities.UserEntity;
import com.fooddelivery.fooddeliveryapi.enums.OrderStatus;

import java.util.Optional;

public interface OrderService {

    Optional<Order> getOrder(String username, OrderStatus orderStatus);
    Order createOrder(Restaurant restaurant, UserEntity userEntity, OrderStatus orderStatus);
    Order updateOrder(Order order);
}