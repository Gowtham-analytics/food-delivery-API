package com.fooddelivery.fooddeliveryapi.services.impl;

import com.fooddelivery.fooddeliveryapi.exceptions.ResourceNotFoundException;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;
import com.fooddelivery.fooddeliveryapi.repositories.RestaurantRepository;
import com.fooddelivery.fooddeliveryapi.services.RestaurantService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> listRestaurants() {

        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {

        LocalDateTime now = LocalDateTime.now();

        return restaurantRepository.save(
                new Restaurant(
                        null,
                        restaurant.getName(),
                        restaurant.getLocation(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        now,
                        now
                )
        );
    }

    @Override
    public Restaurant getRestaurant(Long restaurantId) {

        return restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new ResourceNotFoundException("Restaurant does not exist!"));
    }

    @Override
    public Restaurant partialUpdateRestaurant(Long restaurantId, Restaurant restaurant) {

        return restaurantRepository.findById(restaurantId)
                .map(existing ->
                        {

                            if (restaurant.getName() != null && !restaurant.getName().isBlank()) {
                                existing.setName(restaurant.getName());
                            }

                            if (restaurant.getLocation() != null && !restaurant.getLocation().isBlank()) {
                                existing.setLocation(restaurant.getLocation());
                            }

                            return restaurantRepository.save(existing);
                        }
                ).orElseThrow(() -> new ResourceNotFoundException("Restaurant does not exist!"));
    }

    @Override
    public Restaurant fullUpdateRestaurant(Long restaurantId, Restaurant restaurant) {

        return restaurantRepository.findById(restaurantId).map(
                existing -> {

                    if(restaurant.getName() == null || restaurant.getName().isBlank()) {
                        throw new IllegalArgumentException("Name is required for update!");
                    }

                    if(restaurant.getLocation() == null || restaurant.getLocation().isBlank()) {
                        throw new IllegalArgumentException("Location name is required for update!");
                    }

                    existing.setName(restaurant.getName());
                    existing.setLocation(restaurant.getLocation());
                    return restaurantRepository.save(existing);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Restaurant does not exist!"));
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }
}