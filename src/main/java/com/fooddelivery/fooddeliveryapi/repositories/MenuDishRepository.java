package com.fooddelivery.fooddeliveryapi.repositories;

import com.fooddelivery.fooddeliveryapi.domain.entities.MenuDish;
import com.fooddelivery.fooddeliveryapi.enums.MenuDishStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuDishRepository extends JpaRepository<MenuDish, Long> {

    List<MenuDish> findByRestaurantIdAndStatus(Long id, MenuDishStatus status);
    Optional<MenuDish> findByRestaurantIdAndId(Long restaurantId, Long menuDishId);
    Optional<MenuDish> findByIdAndStatusAndRestaurantUserEntityUsername(Long menuDishId, MenuDishStatus status, String username);
    Optional<MenuDish> findByIdAndStatus(Long menuDishId, MenuDishStatus status);
}