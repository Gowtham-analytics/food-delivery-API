package com.fooddelivery.fooddeliveryapi.mappers;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.OrderCreateDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.response.OrderResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.entities.Order;
import com.fooddelivery.fooddeliveryapi.services.RestaurantService;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    public OrderCreateDto toCreateDto(Order order) {
        return new OrderCreateDto(
                order.getOrderItemsList().stream().map(orderItemMapper::toCreateDto).toList()
        );
    }

    public Order fromCreateDto(OrderCreateDto orderCreateDto) {
        return new Order(
                null,
                null,
                null,
                orderCreateDto.items().stream().map(orderItemMapper::fromCreateDto).toList(),
                null,
                null,
                null,
                null,
                null
        );
    }

    public OrderResponseDto toResponseDto(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getRestaurant().getName(),
                order.getNumberOfItems(),
                order.getTotalPrice(),
                order.getStatus()
        );
    }
}
