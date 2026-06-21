package com.fooddelivery.fooddeliveryapi.mappers;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.OrderItemCreateDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.response.OrderItemResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.update.OrderItemUpdateDto;
import com.fooddelivery.fooddeliveryapi.domain.entities.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemCreateDto toCreateDto(OrderItem orderItem) {
        return new OrderItemCreateDto(
                null,
                orderItem.getQuantity()
        );
    }

    public OrderItem fromCreateDto(OrderItemCreateDto orderItemCreateDto) {
        return new OrderItem(
                null,
                null,
                null,
                orderItemCreateDto.quantity(),
                null
        );
    }

    public OrderItemUpdateDto toUpdateDto(OrderItem orderItem) {
        return new OrderItemUpdateDto(
                orderItem.getQuantity()
        );
    }

    public OrderItem fromUpdateDto(OrderItemUpdateDto orderItemUpdateDto) {
        return new OrderItem(
                null,
                null,
                null,
                orderItemUpdateDto.quantity(),
                null
        );
    }

    public OrderItemResponseDto toResponseDto(OrderItem orderItem) {
        return new OrderItemResponseDto(
                orderItem.getQuantity(),
                orderItem.getOrderPrice()
        );
    }

    public OrderItem fromResponseDto(OrderItemResponseDto orderItemResponseDto) {
        return new OrderItem(
                null,
                null,
                null,
                orderItemResponseDto.quantity(),
                orderItemResponseDto.totalPrice()
        );
    }
}