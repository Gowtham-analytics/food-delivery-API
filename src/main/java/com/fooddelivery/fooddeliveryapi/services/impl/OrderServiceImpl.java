package com.fooddelivery.fooddeliveryapi.services.impl;

import com.fooddelivery.fooddeliveryapi.domain.dto.response.OrderResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.entities.Order;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;
import com.fooddelivery.fooddeliveryapi.domain.entities.UserEntity;
import com.fooddelivery.fooddeliveryapi.enums.OrderStatus;
import com.fooddelivery.fooddeliveryapi.mappers.OrderMapper;
import com.fooddelivery.fooddeliveryapi.repositories.OrderRepository;
import com.fooddelivery.fooddeliveryapi.services.OrderService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
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

    @PostConstruct
    public void test() {
        List<Order> orders = orderList("Gowtham");

        System.out.println("Restaurant Name: " + orders.get(0).getRestaurant().getName());
    }
}
