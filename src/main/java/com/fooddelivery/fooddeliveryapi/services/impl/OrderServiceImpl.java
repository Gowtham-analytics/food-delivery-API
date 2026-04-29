package com.fooddelivery.fooddeliveryapi.services.impl;

import com.fooddelivery.fooddeliveryapi.domain.entities.Order;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;
import com.fooddelivery.fooddeliveryapi.domain.entities.UserEntity;
import com.fooddelivery.fooddeliveryapi.enums.OrderStatus;
import com.fooddelivery.fooddeliveryapi.repositories.OrderRepository;
import com.fooddelivery.fooddeliveryapi.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> getOrder(String username, OrderStatus orderStatus) {
        return orderRepository.findByUserEntityUsernameAndOrderStatus(username, orderStatus);
    }

    @Override
    public Order createOrder(Restaurant restaurant, UserEntity userEntity, OrderStatus orderStatus) {
        return orderRepository.save(new Order(
                null,
                null,
                null,
                new ArrayList<>(),
                restaurant,
                userEntity,
                null,
                null,
                OrderStatus.CART_OPEN
        ));
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }
}
