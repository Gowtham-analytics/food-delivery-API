package com.fooddelivery.fooddeliveryapi.service;

import com.fooddelivery.fooddeliveryapi.domain.dto.response.RestaurantConflictResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.entity.Order;
import com.fooddelivery.fooddeliveryapi.domain.entity.Restaurant;
import com.fooddelivery.fooddeliveryapi.domain.entity.UserEntity;
import com.fooddelivery.fooddeliveryapi.enumerator.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> getOrder(String username, OrderStatus orderStatus);
    Order createOrder(Restaurant restaurant, UserEntity userEntity, OrderStatus orderStatus);
    void updateOrder(Order order);
    List<Order> orderList(String username);
    Order getOrder(String username, Long id);
}