package com.fooddelivery.fooddeliveryapi.service.impl;

import com.fooddelivery.fooddeliveryapi.domain.entity.Order;
import com.fooddelivery.fooddeliveryapi.domain.entity.Restaurant;
import com.fooddelivery.fooddeliveryapi.domain.entity.UserEntity;
import com.fooddelivery.fooddeliveryapi.enumerator.OrderStatus;
import com.fooddelivery.fooddeliveryapi.exception.ResourceNotFoundException;
import com.fooddelivery.fooddeliveryapi.repository.OrderRepository;
import com.fooddelivery.fooddeliveryapi.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> orderList(String username) {

        return orderRepository.findOrderWithRestaurantByUsername(username);
    }

    @Override
    public Order getOrder(String username, Long id) {
        return orderRepository.findByUserEntityUsernameAndId(username, id)
                .orElseThrow(() -> new ResourceNotFoundException("Order cannot be found!"));
    }
}
