package com.fooddelivery.fooddeliveryapi.services.impl;

import com.fooddelivery.fooddeliveryapi.domain.entities.*;
import com.fooddelivery.fooddeliveryapi.enums.OrderStatus;
import com.fooddelivery.fooddeliveryapi.repositories.*;
import com.fooddelivery.fooddeliveryapi.services.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final MenuDishService menuDishService;

    private final RestaurantService restaurantService;

    private final UserService userService;

    private final OrderItemRepository orderItemRepository;

    private final OrderService orderService;

    public OrderItemServiceImpl(MenuDishService menuDishService, RestaurantService restaurantService, UserService userService, OrderItemRepository orderItemRepository, OrderService orderService) {
        this.menuDishService = menuDishService;
        this.restaurantService = restaurantService;
        this.userService = userService;
        this.orderItemRepository = orderItemRepository;
        this.orderService = orderService;
    }

    @Transactional
    @Override
    public OrderItem createOrUpdateOrderItem(Long menuDishId, String username, OrderItem orderItem) {

        MenuDish existingMenuDish = menuDishService.getMenuDishById(menuDishId);

        Long restaurantId = existingMenuDish.getRestaurant().getId();

        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);

        UserEntity userEntity = userService.getUserFromUsername(username);

        Order existingOrder = orderService.getOrCreateOrder(
                restaurant,
                userEntity,
                OrderStatus.CART_OPEN);

        OrderItem savedItem;

        Optional<OrderItem> optionalOrderItem = orderItemRepository.findByMenuDishIdAndOrder(menuDishId, existingOrder);

        Double addedPrice = totalPrice(orderItem.getQuantity(), existingMenuDish.getPrice());

        if(optionalOrderItem.isPresent()) {

            OrderItem existingOrderItem = optionalOrderItem.get();

            existingOrderItem.setQuantity(existingOrderItem.getQuantity() + orderItem.getQuantity());
            existingOrderItem.setOrderPrice(addedPrice + existingOrderItem.getOrderPrice());

            savedItem = orderItemRepository.save(existingOrderItem);
        } else {
            savedItem = createNewOrderItem(
                    existingMenuDish,
                    existingOrder,
                    orderItem.getQuantity(),
                    addedPrice);
        }

        Double existingPrice = (existingOrder.getTotalPrice() == null) ? 0.0 : existingOrder.getTotalPrice();
        Double totalPrice = existingPrice + addedPrice;

        existingOrder.setTotalPrice(totalPrice);

        orderService.updateOrderTotal(existingOrder);

        return savedItem;
    }

    @Override
    public OrderItem createNewOrderItem(MenuDish menuDish, Order order, int quantity, Double totalPrice) {

        return orderItemRepository.save(
                new OrderItem(
                        null,
                        menuDish,
                        order,
                        quantity,
                        totalPrice
                )
        );
    }

    private Double totalPrice(int quantity, Double price) {
        return quantity * price;
    }
}
