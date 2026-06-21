package com.fooddelivery.fooddeliveryapi.exception;

public class RestaurantOrderConflictException extends RuntimeException {
    public RestaurantOrderConflictException(String message) {
        super(message);
    }
}
