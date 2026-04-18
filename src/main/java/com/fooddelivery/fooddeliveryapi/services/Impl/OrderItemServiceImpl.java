package com.fooddelivery.fooddeliveryapi.services.Impl;

import com.fooddelivery.fooddeliveryapi.Exceptions.ResourceNotFoundException;
import com.fooddelivery.fooddeliveryapi.domain.entities.Customer;
import com.fooddelivery.fooddeliveryapi.domain.entities.MenuDish;
import com.fooddelivery.fooddeliveryapi.domain.entities.OrderItem;
import com.fooddelivery.fooddeliveryapi.repositories.CustomerRepository;
import com.fooddelivery.fooddeliveryapi.repositories.MenuDishRepository;
import com.fooddelivery.fooddeliveryapi.repositories.OrderItemRepository;
import com.fooddelivery.fooddeliveryapi.services.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final MenuDishRepository menuDishRepository;
    private final CustomerRepository customerRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, MenuDishRepository menuDishRepository, CustomerRepository customerRepository) {
        this.orderItemRepository = orderItemRepository;
        this.menuDishRepository = menuDishRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public OrderItem createOrderItem(Long customerId, Long menuDishId, OrderItem orderItem) {
        return null;
    }
}
