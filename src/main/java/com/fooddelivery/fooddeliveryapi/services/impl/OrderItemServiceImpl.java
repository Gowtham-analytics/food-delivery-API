package com.fooddelivery.fooddeliveryapi.services.impl;

import com.fooddelivery.fooddeliveryapi.domain.entities.OrderItem;
import com.fooddelivery.fooddeliveryapi.repositories.MenuDishRepository;
import com.fooddelivery.fooddeliveryapi.repositories.OrderItemRepository;
import com.fooddelivery.fooddeliveryapi.services.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final MenuDishRepository menuDishRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, MenuDishRepository menuDishRepository) {
        this.orderItemRepository = orderItemRepository;
        this.menuDishRepository = menuDishRepository;
    }

    @Override
    public OrderItem createOrderItem(Long customerId, Long menuDishId, OrderItem orderItem) {
        return null;
    }
}
