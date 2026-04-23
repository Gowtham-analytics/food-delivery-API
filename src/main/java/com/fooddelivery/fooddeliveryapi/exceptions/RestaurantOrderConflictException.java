package com.fooddelivery.fooddeliveryapi.exceptions;

public class RestaurantOrderConflictException extends RuntimeException {
    public RestaurantOrderConflictException(String message) {
        super(message);
    }
}
