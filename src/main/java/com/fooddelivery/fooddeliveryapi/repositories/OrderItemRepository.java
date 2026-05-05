package com.fooddelivery.fooddeliveryapi.repositories;

import com.fooddelivery.fooddeliveryapi.domain.entities.Order;
import com.fooddelivery.fooddeliveryapi.domain.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findByMenuDishIdAndOrderUserEntityUsernameAndOrder(Long menuDishId, String username, Order order);
}
