package com.fooddelivery.fooddeliveryapi.repositories;

import com.fooddelivery.fooddeliveryapi.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}