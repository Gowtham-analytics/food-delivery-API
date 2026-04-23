package com.fooddelivery.fooddeliveryapi.services.impl;

import com.fooddelivery.fooddeliveryapi.domain.entities.Order;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;
import com.fooddelivery.fooddeliveryapi.domain.entities.UserEntity;
import com.fooddelivery.fooddeliveryapi.enums.OrderStatus;
import com.fooddelivery.fooddeliveryapi.exceptions.ResourceNotFoundException;
import com.fooddelivery.fooddeliveryapi.exceptions.RestaurantOrderConflictException;
import com.fooddelivery.fooddeliveryapi.repositories.OrderRepository;
import com.fooddelivery.fooddeliveryapi.services.OrderService;
import com.fooddelivery.fooddeliveryapi.services.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final RestaurantService restaurantService;

    public OrderServiceImpl(OrderRepository orderRepository, RestaurantService restaurantService) {
        this.orderRepository = orderRepository;
        this.restaurantService = restaurantService;
    }

    @Override
    public Order getOrCreateOrder(Restaurant restaurant, UserEntity userEntity, OrderStatus orderStatus) {

        Optional<Order> existingOrder = orderRepository.findByUserEntityAndOrderStatus(userEntity, orderStatus);

        if(existingOrder.isPresent() && !existingOrder.get().getRestaurant().getId().equals(restaurant.getId())) {
            orderRepository.delete(existingOrder.get());
        }

        return orderRepository.findByRestaurantAndUserEntityAndOrderStatus(
                restaurant,
                userEntity,
                orderStatus)
                .orElseGet(() ->
                    orderRepository.save(new Order(
                            null,
                            null,
                            new ArrayList<>(),
                            restaurant,
                            userEntity,
                            null,
                            null,
                            OrderStatus.CART_OPEN
                    )));
    }

    @Override
    public Order updateOrderTotal(Order order) {
        return orderRepository.save(order);
    }
}
