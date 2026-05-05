package com.fooddelivery.fooddeliveryapi.services.impl;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.OrderItemCreateDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.response.CartActionResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.response.RestaurantConflictResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.entities.*;
import com.fooddelivery.fooddeliveryapi.enums.CartStatus;
import com.fooddelivery.fooddeliveryapi.enums.OrderStatus;
import com.fooddelivery.fooddeliveryapi.repositories.OrderItemRepository;
import com.fooddelivery.fooddeliveryapi.services.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    private final OrderService orderService;

    private final MenuDishService menuDishService;

    private final UserService userService;

    private final ConflictResponseService conflictResponseService;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderService orderService, MenuDishService menuDishService, UserService userService, ConflictResponseService conflictResponseService) {
        this.orderItemRepository = orderItemRepository;
        this.orderService = orderService;
        this.menuDishService = menuDishService;
        this.userService = userService;
        this.conflictResponseService = conflictResponseService;
    }

    public RestaurantConflictResponseDto restaurantConflict(Long menuDishId, String username) {

        MenuDish menuDish = menuDishService.getMenuDishById(menuDishId);

        Optional<Order> order = orderService.getOrder(username, OrderStatus.CART_OPEN);

        Restaurant restaurant = menuDish.getRestaurant();

        if(order.isEmpty()) {
            return conflictResponseService.createConflictResponseDto(
                    false,
                    restaurant,
                    null
            );
        }

        Long existingRestaurantId = order.get().getRestaurant().getId();
        Long newRestaurantId = menuDish.getRestaurant().getId();

        if(existingRestaurantId.equals(newRestaurantId)) {
            return conflictResponseService.createConflictResponseDto(
                    false,
                    restaurant,
                    order.get()
            );
        }

        return conflictResponseService.createConflictResponseDto(
                true,
                restaurant,
                order.get()
        );
    }

    @Transactional
    @Override
    public CartActionResponseDto addOrderItem(OrderItemCreateDto orderItemCreateDto, String username) {

        MenuDish menuDish = menuDishService.getMenuDishById(orderItemCreateDto.menuDishId());

        UserEntity userEntity = userService.getUserFromUsername(username);

        RestaurantConflictResponseDto restaurantConflict = restaurantConflict(orderItemCreateDto.menuDishId(), username);

        Restaurant restaurant = restaurantConflict.restaurant();

        int quantity = orderItemCreateDto.quantity();

        Double totalPrice = totalPrice(quantity, menuDish.getPrice());

        //Conflict
        if(restaurantConflict.ifConflict()) {
            return new CartActionResponseDto(
                    CartStatus.CONFLICT,
                    restaurant.getName(),
                    restaurantConflict.order().getRestaurant().getName(),
                    restaurantConflict.order().getNumberOfItems()
            );
        }

        //New Order/Cart
        if(restaurantConflict.order() == null) {

            Order newOrder = orderService.createOrder(
                    restaurant,
                    userEntity,
                    OrderStatus.CART_OPEN
            );

            OrderItem orderItem = new OrderItem(
                    null,
                    menuDish,
                    newOrder,
                    quantity,
                    totalPrice
            );

            orderItemRepository.save(orderItem);

            newOrder.setNumberOfItems(quantity);
            newOrder.setTotalPrice(totalPrice);

            orderService.updateOrder(newOrder);

            return new CartActionResponseDto(
                    CartStatus.OK,
                    restaurant.getName(),
                    null,
                    newOrder.getNumberOfItems()
            );
        }

        //Existing Order
        Order existingOrder = restaurantConflict.order();

        orderItemRepository.findByMenuDishIdAndOrderUserEntityUsernameAndOrder(
                orderItemCreateDto.menuDishId(),
                username,
                existingOrder)
                        .map(existing -> {

                            existing.setQuantity(existing.getQuantity() + quantity);
                            existing.setOrderPrice(existing.getOrderPrice() + totalPrice);
                            return orderItemRepository.save(existing);
                                })
                .orElseGet(() ->
                        orderItemRepository.save(new OrderItem(
                                null,
                                menuDish,
                                existingOrder,
                                quantity,
                                totalPrice
                        ))
                );


        existingOrder.setNumberOfItems(existingOrder.getNumberOfItems() + quantity);
        existingOrder.setTotalPrice(existingOrder.getTotalPrice() + totalPrice);

        orderService.updateOrder(existingOrder);

        return new CartActionResponseDto(
                CartStatus.OK,
                restaurant.getName(),
                null,
                existingOrder.getNumberOfItems()
        );
    }

    public Double totalPrice(int quantity, Double price) {
        return quantity * price;
    }
}
