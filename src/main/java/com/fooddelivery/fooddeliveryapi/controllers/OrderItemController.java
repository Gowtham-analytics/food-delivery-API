package com.fooddelivery.fooddeliveryapi.controllers;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.OrderItemCreateDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.response.CartActionResponseDto;
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

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping
    public CartActionResponseDto createOrderItem(
            @RequestBody OrderItemCreateDto orderItemCreateDto,
            Authentication authentication
    )
    {
        return orderItemService.addOrderItem(orderItemCreateDto, authentication.getName());
    }
}
