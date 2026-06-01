package com.fooddelivery.fooddeliveryapi.services;

import com.fooddelivery.fooddeliveryapi.domain.entities.MenuDish;

import java.util.List;

public interface MenuDishService {

    List<MenuDish> listMenuDishes(Long id);
    MenuDish getMenuDish(Long restaurantId, Long menuDishId);
    MenuDish getMenuDishById(Long menuDishId);
    MenuDish getMenuDishActive(Long menuDishId);
    MenuDish createMenuDish(Long restaurantId, MenuDish menuDish, String username);
    MenuDish partialUpdate(Long restaurantId, Long menuDishId, MenuDish menuDish, String username);
    MenuDish fullUpdate(Long restaurantId, Long menuDishId, MenuDish menuDish, String username);
    void discontinueMenuDish(Long menuDishId, String username);
    MenuDish toggleMenuDishAvailability(Long menuDishId, String username);
}