package com.fooddelivery.fooddeliveryapi.repositories;

import com.fooddelivery.fooddeliveryapi.domain.entities.Order;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;
import com.fooddelivery.fooddeliveryapi.domain.entities.UserEntity;
import com.fooddelivery.fooddeliveryapi.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByRestaurantAndUserEntityAndOrderStatus(Restaurant restaurant, UserEntity userEntity, OrderStatus orderStatus);
    Optional<Order> findByUserEntityAndOrderStatus(UserEntity userEntity, OrderStatus orderStatus);
}