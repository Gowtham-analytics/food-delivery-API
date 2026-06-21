package com.fooddelivery.fooddeliveryapi.repository;

import com.fooddelivery.fooddeliveryapi.domain.entity.Order;
import com.fooddelivery.fooddeliveryapi.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findByMenuDishIdAndOrderUserEntityUsernameAndOrder(Long menuDishId, String username, Order order);
}
