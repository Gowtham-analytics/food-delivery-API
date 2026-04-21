package com.fooddelivery.fooddeliveryapi.services.impl;

import com.fooddelivery.fooddeliveryapi.exceptions.ResourceNotFoundException;
import com.fooddelivery.fooddeliveryapi.domain.entities.MenuDish;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;
import com.fooddelivery.fooddeliveryapi.repositories.MenuDishRepository;
import com.fooddelivery.fooddeliveryapi.repositories.RestaurantRepository;
import com.fooddelivery.fooddeliveryapi.services.MenuDishService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MenuDishServiceImpl implements MenuDishService {

    private final MenuDishRepository menuDishRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuDishServiceImpl(MenuDishRepository menuDishRepository, RestaurantRepository restaurantRepository) {
        this.menuDishRepository = menuDishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<MenuDish> listMenuDishes(Long id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No restaurant in record!"));

        return menuDishRepository.findByRestaurantId(id);
    }

    @Override
    public MenuDish menuDish(Long restaurantId, Long menuDishId) {

        return menuDishRepository.findByRestaurantIdAndId(restaurantId, menuDishId)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found!"));
    }

    @Override
    public MenuDish createMenuDish(Long restaurantId, MenuDish menuDish) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Restaurant ID!"));

        if(menuDish.getVeg() == null) {
            menuDish.setVeg(true);
        }

        LocalDateTime now = LocalDateTime.now();

        MenuDish menDishToSave = new MenuDish(
                null,
                menuDish.getName(),
                menuDish.getPrice(),
                menuDish.getVeg(),
                restaurant,
                now,
                now
        );

        return menuDishRepository.save(menDishToSave);
    }

    @Override
    public MenuDish partialUpdate(Long restaurantId, Long menuDishId, MenuDish menuDish) {

        return menuDishRepository.findByRestaurantIdAndId(restaurantId, menuDishId).map(
                existing -> {

                    if(menuDish.getName() != null && !menuDish.getName().isBlank()) {
                        existing.setName(menuDish.getName());
                    }

                    if(menuDish.getPrice() != null && !menuDish.getPrice().isNaN()) {
                        existing.setPrice(menuDish.getPrice());
                    }

                    if(menuDish.getVeg() != null) {
                        existing.setVeg(menuDish.getVeg());
                    }

                    return menuDishRepository.save(existing);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Menu Dish does not exist!"));
    }

    @Override
    public MenuDish fullUpdate(Long restaurantId, Long menuDishId, MenuDish menuDish) {

        return menuDishRepository.findByRestaurantIdAndId(restaurantId, menuDishId).map(
                existing -> {

                    if(menuDish.getName() == null || menuDish.getName().isBlank()) {
                        throw new IllegalArgumentException("Name is required for update!");
                    }

                    if(menuDish.getPrice() == null) {
                        throw new IllegalArgumentException("Price is required for update!");
                    }

                    if(menuDish.getVeg() != null) {
                        existing.setVeg(menuDish.getVeg());
                    }

                    existing.setName(menuDish.getName());
                    existing.setPrice(menuDish.getPrice());
                    return menuDishRepository.save(existing);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Menu Dish cannot be found!"));
    }

    @Transactional
    @Override
    public void deleteMenuDish(Long restaurantId, Long menuDishId) {

        menuDishRepository.deleteByRestaurantIdAndId(restaurantId, menuDishId);
    }
}
