package com.fooddelivery.fooddeliveryapi.services.impl;

import com.fooddelivery.fooddeliveryapi.enums.MenuDishStatus;
import com.fooddelivery.fooddeliveryapi.exceptions.ResourceNotFoundException;
import com.fooddelivery.fooddeliveryapi.domain.entities.MenuDish;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;
import com.fooddelivery.fooddeliveryapi.repositories.MenuDishRepository;
import com.fooddelivery.fooddeliveryapi.repositories.RestaurantRepository;
import com.fooddelivery.fooddeliveryapi.services.MenuDishService;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
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
    public MenuDish getMenuDish(Long restaurantId, Long menuDishId) {

        return menuDishRepository.findByRestaurantIdAndId(restaurantId, menuDishId)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found!"));
    }

    @Override
    public MenuDish getMenuDishById(Long menuDishId) {
        return menuDishRepository.findById(menuDishId)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found!"));
    }

    @Override
    public MenuDish getMenuDishByIdAndActive(Long menuDishId) {
        return menuDishRepository.findByIdAndStatus(menuDishId, MenuDishStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found!"));
    }

    @Override
    public MenuDish createMenuDish(Long restaurantId, MenuDish menuDish, String username) {

        Restaurant existing = restaurantRepository.findByIdAndUserEntityUsername(restaurantId, username)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found!"));

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
                menuDish.getAvailable(),
                menuDish.getStatus(),
                menuDish.getDiscontinuedTime(),
                now,
                now
        );

        return menuDishRepository.save(menDishToSave);
    }

    @Override
    public MenuDish partialUpdate(Long restaurantId, Long menuDishId, MenuDish menuDish, String username) {

        MenuDish existing = menuDishRepository.findByIdAndStatusAndRestaurantUserEntityUsername(menuDishId, username, MenuDishStatus.ACTIVE)
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

        MenuDish existing = menuDishRepository.findByIdAndStatusAndRestaurantUserEntityUsername(menuDishId, username, MenuDishStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found!"));

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
    public void discontinueMenuDish(Long menuDishId, String username) {

        MenuDish existing = menuDishRepository.findByIdAndStatusAndRestaurantUserEntityUsername(menuDishId, username, MenuDishStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found!"));

        if(existing.getStatus() == MenuDishStatus.DISCONTINUED) {
            throw new IllegalStateException("Dish already discontinued!");
        }

        LocalDateTime now = LocalDateTime.now();

        existing.setStatus(MenuDishStatus.DISCONTINUED);
        existing.setDiscontinuedTime(now);
        menuDishRepository.save(existing);
    }

}
