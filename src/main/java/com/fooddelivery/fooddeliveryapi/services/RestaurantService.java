package com.fooddelivery.fooddeliveryapi.services;

import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> listRestaurants();
    Restaurant createRestaurant(Restaurant restaurant);
    Restaurant getRestaurant(Long restaurantId);
    Restaurant partialUpdateRestaurant(Long restaurantId, Restaurant restaurant);
    Restaurant fullUpdateRestaurant(Long restaurantId, Restaurant restaurant);
    void deleteRestaurant(Long restaurantId);
}