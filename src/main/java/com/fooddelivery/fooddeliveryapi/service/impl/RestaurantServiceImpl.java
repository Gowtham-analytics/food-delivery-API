package com.fooddelivery.fooddeliveryapi.service.impl;

import com.fooddelivery.fooddeliveryapi.domain.entity.UserEntity;
import com.fooddelivery.fooddeliveryapi.exception.ResourceNotFoundException;
import com.fooddelivery.fooddeliveryapi.domain.entity.Restaurant;
import com.fooddelivery.fooddeliveryapi.repository.RestaurantRepository;
import com.fooddelivery.fooddeliveryapi.service.RestaurantService;
import com.fooddelivery.fooddeliveryapi.service.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final UserService userService;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, UserService userService) {
        this.restaurantRepository = restaurantRepository;
        this.userService = userService;
    }

    @Override
    public List<Restaurant> listRestaurants() {

        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant, String username) {

        UserEntity userEntity = userService.getUserFromUsername(username);

        LocalDateTime now = LocalDateTime.now();

        return restaurantRepository.save(
                new Restaurant(
                        null,
                        restaurant.getName(),
                        restaurant.getLocation(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        userEntity,
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
    public Restaurant partialUpdateRestaurant(Long restaurantId, Restaurant restaurant, String username) {

        Restaurant existing = restaurantRepository.findByIdAndUserEntityUsername(restaurantId, username)
                .orElseThrow(() -> new AccessDeniedException("You cannot modify this Restaurant"));


        if (restaurant.getName() != null && !restaurant.getName().isBlank()) {
            existing.setName(restaurant.getName());
        }

        if (restaurant.getLocation() != null && !restaurant.getLocation().isBlank()) {
            existing.setLocation(restaurant.getLocation());
        }

        return restaurantRepository.save(existing);
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
        ).orElseThrow(() -> new ResourceNotFoundException("Restaurant does not exist"));
    }

    @Override
    public void deleteRestaurant(Long restaurantId, String username) {

        Restaurant existing = restaurantRepository.findByIdAndUserEntityUsername(restaurantId, username)
                .orElseThrow(() -> new AccessDeniedException("Not allowed"));

        restaurantRepository.deleteById(restaurantId);
    }
}