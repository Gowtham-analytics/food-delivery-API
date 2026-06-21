package com.fooddelivery.fooddeliveryapi.repository;

import com.fooddelivery.fooddeliveryapi.domain.entity.MenuDish;
import com.fooddelivery.fooddeliveryapi.enumerator.MenuDishStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuDishRepository extends JpaRepository<MenuDish, Long> {

    List<MenuDish> findByRestaurant_IdAndStatus(Long id, MenuDishStatus status);
    Optional<MenuDish> findByRestaurant_IdAndIdAndStatus(Long restaurantId, Long menuDishId, MenuDishStatus status);
    Optional<MenuDish> findByIdAndRestaurant_IdAndStatusAndRestaurantUserEntityUsername(Long menuDishId, Long restaurantId, MenuDishStatus status, String username);
    Optional<MenuDish> findByIdAndStatus(Long menuDishId, MenuDishStatus status);
}