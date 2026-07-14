package com.fooddelivery.fooddeliveryapi.repository;

import com.fooddelivery.fooddeliveryapi.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByIdAndUserEntityUsername(Long restaurantId, String username);
}
