package com.fooddelivery.fooddeliveryapi.controllers;

import com.fooddelivery.fooddeliveryapi.domain.dto.response.OrderResponseDto;
import com.fooddelivery.fooddeliveryapi.mappers.OrderMapper;
import com.fooddelivery.fooddeliveryapi.services.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@EnableMethodSecurity
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PreAuthorize("hasAuthority('VIEW_ORDER')")
    @GetMapping
    public List<OrderResponseDto> getOrderList(
            Authentication authentication
    )
    {
        String username = authentication.getName();

        return orderService.orderList(username).stream().map(
                orderMapper::toResponseDto
        ).toList();
    }

    @PreAuthorize("hasAuthority('VIEW_ORDER')")
    @GetMapping("/{order_id}")
    public OrderResponseDto getOrder(
            @PathVariable("order_id") Long id,
            Authentication authentication
    )
    {
        String username = authentication.getName();

        return orderMapper.toResponseDto(orderService.getOrder(username, id));
    }
}
