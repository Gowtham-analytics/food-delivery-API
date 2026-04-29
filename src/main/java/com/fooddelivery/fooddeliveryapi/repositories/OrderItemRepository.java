package com.fooddelivery.fooddeliveryapi.repositories;

import com.fooddelivery.fooddeliveryapi.domain.entities.Order;
import com.fooddelivery.fooddeliveryapi.domain.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findByMenuDishIdAndOrder(Long menuDishId, Order order);
    List<OrderItem> findByOrderId(Long orderId);
    Optional<OrderItem> findByMenuDishIdAndUserEntityUsernameAndOrder(Long menuDishId, String username, Order order);
}
