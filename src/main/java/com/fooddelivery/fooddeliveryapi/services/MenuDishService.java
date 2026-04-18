package com.fooddelivery.fooddeliveryapi.services;

import com.fooddelivery.fooddeliveryapi.domain.entities.MenuDish;

import java.util.List;

public interface MenuDishService {

    List<MenuDish> listMenuDishes(Long id);
    MenuDish menuDish(Long restaurantId, Long menuDishId);
    MenuDish createMenuDish(Long restaurantId, MenuDish menuDish);
    MenuDish partialUpdate(Long restaurantId, Long menuDishId, MenuDish menuDish);
    MenuDish fullUpdate(Long restaurantId, Long menuDishId, MenuDish menuDish);
    void deleteMenuDish(Long restaurantId, Long menuDishId);
}