package com.fooddelivery.fooddeliveryapi.services;

import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;
import com.fooddelivery.fooddeliveryapi.domain.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> listRestaurants();
    Restaurant createRestaurant(Restaurant restaurant, String username);
    Restaurant getRestaurant(Long restaurantId);
    Restaurant partialUpdateRestaurant(Long restaurantId, Restaurant restaurant, String username);
    Restaurant fullUpdateRestaurant(Long restaurantId, Restaurant restaurant);
    void deleteRestaurant(Long restaurantId, String username);
}