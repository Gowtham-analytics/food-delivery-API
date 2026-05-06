package com.fooddelivery.fooddeliveryapi.repositories;

import com.fooddelivery.fooddeliveryapi.domain.dto.response.OrderResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.entities.Order;
import com.fooddelivery.fooddeliveryapi.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByUserEntityUsernameAndOrderStatus(String username, OrderStatus orderStatus);

    @Query("""
    SELECT o FROM Order o
    JOIN FETCH o.restaurant
    WHERE o.userEntity.username = :username
""")
    List<Order> findOrderWithRestaurantByUsername(String username);

    Optional<Order> findByUserEntityUsernameAndId(String username, Long id);
}