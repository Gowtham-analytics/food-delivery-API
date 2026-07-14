package com.fooddelivery.fooddeliveryapi.service;

import com.fooddelivery.fooddeliveryapi.domain.entity.MenuDish;

import java.util.List;

public interface MenuDishService {

    List<MenuDish> listMenuDishes(Long id);
    MenuDish getActiveMenuDish(Long restaurantId, Long menuDishId);
    MenuDish getMenuDishById(Long menuDishId);
    MenuDish getAvailableMenuDish(Long MenuDishId);
    MenuDish createMenuDish(Long restaurantId, MenuDish menuDish, String username);
    MenuDish partialUpdate(Long restaurantId, Long menuDishId, MenuDish menuDish, String username);
    MenuDish fullUpdate(Long restaurantId, Long menuDishId, MenuDish menuDish, String username);
    void discontinueMenuDish(Long restaurantId, Long menuDishId, String username);
    boolean toggleMenuDishAvailability(Long restaurantId, Long menuDishId, String username);
}