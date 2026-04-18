package com.fooddelivery.fooddeliveryapi.repositories;

import com.fooddelivery.fooddeliveryapi.domain.entities.MenuDish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuDishRepository extends JpaRepository<MenuDish, Long> {

    List<MenuDish> findByRestaurantId(Long id);
    Optional<MenuDish> findByRestaurantIdAndId(Long restaurantId, Long menuDishId);
    void deleteByRestaurantIdAndId(Long restaurantId, Long menuDishId);
}