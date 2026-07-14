package com.fooddelivery.fooddeliveryapi.service.impl;

import com.fooddelivery.fooddeliveryapi.enumerator.MenuDishStatus;
import com.fooddelivery.fooddeliveryapi.exception.ResourceNotFoundException;
import com.fooddelivery.fooddeliveryapi.domain.entity.MenuDish;
import com.fooddelivery.fooddeliveryapi.domain.entity.Restaurant;
import com.fooddelivery.fooddeliveryapi.repository.MenuDishRepository;
import com.fooddelivery.fooddeliveryapi.repository.RestaurantRepository;
import com.fooddelivery.fooddeliveryapi.service.MenuDishService;
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

        return menuDishRepository.findByRestaurant_IdAndStatus(id, MenuDishStatus.ACTIVE);
    }

    @Override
    public MenuDish getActiveMenuDish(Long restaurantId, Long menuDishId) {

        return menuDishRepository.findByRestaurant_IdAndIdAndStatus(restaurantId, menuDishId, MenuDishStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found!"));
    }

    @Override
    public MenuDish getMenuDishById(Long menuDishId) {
        return menuDishRepository.findById(menuDishId)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found!"));
    }

    @Override
    public MenuDish getAvailableMenuDish(Long menuDishId) {
        return menuDishRepository.findByIdAndAvailableAndStatus(menuDishId, true, MenuDishStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Dish is not available!"));
    }

    @Override
    public MenuDish createMenuDish(Long restaurantId, MenuDish menuDish, String username) {

        Restaurant existing = restaurantRepository.findByIdAndUserEntityUsername(restaurantId, username)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found!"));

        if(menuDish.getVeg() == null) {
            menuDish.setVeg(true);
        }

        LocalDateTime now = LocalDateTime.now();

        MenuDish menDishToSave = new MenuDish(
                null,
                menuDish.getName(),
                menuDish.getPrice(),
                menuDish.getVeg(),
                existing,
                false,
                MenuDishStatus.ACTIVE,
                menuDish.getDiscontinuedTime(),
                now,
                now
        );

        return menuDishRepository.save(menDishToSave);
    }

    @Override
    public MenuDish partialUpdate(Long restaurantId, Long menuDishId, MenuDish menuDish, String username) {

        MenuDish existing = menuDishRepository.findByIdAndRestaurant_IdAndStatusAndRestaurantUserEntityUsername(menuDishId, restaurantId, MenuDishStatus.ACTIVE, username)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found!"));

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

    @Override
    public MenuDish fullUpdate(Long restaurantId, Long menuDishId, MenuDish menuDish, String username) {

        MenuDish existing = menuDishRepository.findByIdAndRestaurant_IdAndStatusAndRestaurantUserEntityUsername(menuDishId, restaurantId, MenuDishStatus.ACTIVE, username)
                .orElseThrow(() -> new ResourceNotFoundException("Menu Dish does not exist!"));

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

    @Override
    public void discontinueMenuDish(Long restaurantId, Long menuDishId, String username) {

        MenuDish existing = menuDishRepository.findByIdAndRestaurant_IdAndStatusAndRestaurantUserEntityUsername(menuDishId, restaurantId, MenuDishStatus.ACTIVE, username)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found!"));

        if(existing.getStatus() == MenuDishStatus.DISCONTINUED) {
            throw new IllegalStateException("Dish already discontinued!");
        }

        LocalDateTime now = LocalDateTime.now();

        existing.setStatus(MenuDishStatus.DISCONTINUED);
        existing.setDiscontinuedTime(now);
        menuDishRepository.save(existing);
    }

    @Override
    public boolean toggleMenuDishAvailability(Long restaurantId, Long menuDishId, String username) {

        MenuDish existing = menuDishRepository.findByIdAndRestaurant_IdAndStatusAndRestaurantUserEntityUsername(menuDishId, restaurantId, MenuDishStatus.ACTIVE, username)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found"));

        existing.setAvailable(!existing.getAvailable());
        menuDishRepository.save(existing);
        return existing.getAvailable();
    }

}
