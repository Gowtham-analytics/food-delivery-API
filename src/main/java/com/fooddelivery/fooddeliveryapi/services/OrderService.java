package com.fooddelivery.fooddeliveryapi.services;

import com.fooddelivery.fooddeliveryapi.domain.entities.Order;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;
import com.fooddelivery.fooddeliveryapi.domain.entities.UserEntity;
import com.fooddelivery.fooddeliveryapi.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> getOrder(String username, OrderStatus orderStatus);
    Order createOrder(Restaurant restaurant, UserEntity userEntity, OrderStatus orderStatus);
    void updateOrder(Order order);
    List<Order> orderList(String username);
}