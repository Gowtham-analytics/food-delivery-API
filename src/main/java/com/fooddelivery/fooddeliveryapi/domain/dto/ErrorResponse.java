package com.fooddelivery.fooddeliveryapi.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}