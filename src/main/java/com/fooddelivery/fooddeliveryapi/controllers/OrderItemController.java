package com.fooddelivery.fooddeliveryapi.controllers;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.OrderItemCreateDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.response.OrderItemResponseDto;
import com.fooddelivery.fooddeliveryapi.mappers.OrderItemMapper;
import com.fooddelivery.fooddeliveryapi.services.OrderItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    private final OrderItemMapper orderItemMapper;

    public OrderItemController(OrderItemService orderItemService, OrderItemMapper orderItemMapper) {
        this.orderItemService = orderItemService;
        this.orderItemMapper = orderItemMapper;
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping
    public OrderItemResponseDto createOrderItem(
            @RequestBody OrderItemCreateDto orderItemCreateDto,
            Authentication authentication
    )
    {
        String username = authentication.getName();

        return orderItemMapper.toResponseDto(orderItemService.createOrUpdateOrderItem(
                orderItemCreateDto.menuDishId(),
                username,
                orderItemMapper.fromCreateDto(orderItemCreateDto)));
    }
}
