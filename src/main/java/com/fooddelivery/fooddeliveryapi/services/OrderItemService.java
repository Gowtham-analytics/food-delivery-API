package com.fooddelivery.fooddeliveryapi.services;

import com.fooddelivery.fooddeliveryapi.domain.entities.MenuDish;
import com.fooddelivery.fooddeliveryapi.domain.entities.Order;
import com.fooddelivery.fooddeliveryapi.domain.entities.OrderItem;

public interface OrderItemService {

    OrderItem createOrUpdateOrderItem(Long menuDishId, String username, OrderItem orderItem);
    OrderItem createNewOrderItem(MenuDish menuDish, Order order, int quantity, Double totalPrice);
}
