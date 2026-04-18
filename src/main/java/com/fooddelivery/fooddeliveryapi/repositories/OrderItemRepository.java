package com.fooddelivery.fooddeliveryapi.repositories;

import com.fooddelivery.fooddeliveryapi.domain.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
