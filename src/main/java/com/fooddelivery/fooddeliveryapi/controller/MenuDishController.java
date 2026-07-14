package com.fooddelivery.fooddeliveryapi.controller;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.MenuDishCreateDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.response.MenuDishResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.update.MenuDishUpdateDto;
import com.fooddelivery.fooddeliveryapi.domain.entity.MenuDish;
import com.fooddelivery.fooddeliveryapi.mapper.MenuDishMapper;
import com.fooddelivery.fooddeliveryapi.service.MenuDishService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurant_id}/menu-dishes")
@EnableMethodSecurity
public class MenuDishController {

    private final MenuDishService menuDishService;
    private final MenuDishMapper menuDishMapper;

    public MenuDishController(MenuDishService menuDishService, MenuDishMapper menuDishMapper) {
        this.menuDishService = menuDishService;
        this.menuDishMapper = menuDishMapper;
    }

    @PreAuthorize("hasAuthority('VIEW_MENU_DISH')")
    @GetMapping
    public List<MenuDishResponseDto> listMenuDishes(@PathVariable("restaurant_id") Long restaurantId) {
        return menuDishService.listMenuDishes(restaurantId).stream().map(menuDishMapper::toResponseDto).toList();
    }

    @PreAuthorize("hasAuthority('VIEW_MENU_DISH')")
    @GetMapping(path = "/{menu_dish_id}")
    public MenuDishResponseDto menuDish(
            @PathVariable("restaurant_id") Long restaurantId,
            @PathVariable("menu_dish_id") Long menuDishId)
    {
        return menuDishMapper.toResponseDto(menuDishService.getActiveMenuDish(restaurantId, menuDishId));
    }

    @PreAuthorize("hasAuthority('ADD_MENU_DISH')")
    @PostMapping
    public ResponseEntity<MenuDishCreateDto> createMenuDish(
            @PathVariable("restaurant_id") Long restaurantId,
            @RequestBody @Valid MenuDishCreateDto menuDishCreateDto,
            Authentication authentication
    )
    {
        String username = authentication.getName();

        MenuDish savedMenuDish = menuDishService.createMenuDish(
                restaurantId,
                menuDishMapper.fromCreateDto(menuDishCreateDto),
                username);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(menuDishMapper.toCreateDto(savedMenuDish));
    }

    @PreAuthorize("hasAuthority('UPDATE_MENU_DISH')")
    @PatchMapping(path = "/{menu_dish_id}")
    public MenuDishUpdateDto partialUpdateMenuDish(
            @PathVariable("restaurant_id") Long restaurantId,
            @PathVariable("menu_dish_id") Long menuDishId,
            @RequestBody MenuDishUpdateDto menuDishUpdateDto,
            Authentication authentication
    )
    {
        String username = authentication.getName();

        return menuDishMapper.toUpdateDto(
                menuDishService.partialUpdate(
                        restaurantId,
                        menuDishId,
                        menuDishMapper.fromUpdateDto(menuDishUpdateDto),
                        username));
    }

    @PreAuthorize("hasAuthority('UPDATE_MENU_DISH')")
    @PutMapping(path = "/{menu_dish_id}")
    public MenuDishUpdateDto fullUpdateMenuDish(
            @PathVariable("restaurant_id") Long restaurantId,
            @PathVariable("menu_dish_id") Long menuDishId,
            @RequestBody MenuDishUpdateDto menuDishUpdateDto,
            Authentication authentication
    )
    {
        String username = authentication.getName();

        return menuDishMapper.toUpdateDto(
                menuDishService.fullUpdate(
                        restaurantId,
                        menuDishId,
                        menuDishMapper.fromUpdateDto(menuDishUpdateDto),
                        username));
    }

    @PreAuthorize("hasAuthority('UPDATE_MENU_DISH')")
    @PatchMapping(path = "/{menu_dish_id}/life-cycle")
    public void discontinueMenuDish(
            @PathVariable("restaurant_id") Long restaurantId,
            @PathVariable("menu_dish_id") Long menuDishId,
            Authentication authentication
    )
    {
        String username = authentication.getName();

        menuDishService.discontinueMenuDish(restaurantId, menuDishId, username);
    }

    @PreAuthorize("hasAuthority('UPDATE_MENU_DISH')")
    @PatchMapping(path = "/{menu_dish_id}/toggle-availability")
    public boolean toggleMenuDishAvailability(
            @PathVariable("restaurant_id") Long restaurantId,
            @PathVariable("menu_dish_id") Long menuDishId,
            Authentication authentication
    )
    {
        String username = authentication.getName();

        return menuDishService.toggleMenuDishAvailability(restaurantId, menuDishId, username);
    }
}