package com.fooddelivery.fooddeliveryapi.Controllers;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.MenuDishCreateDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.response.MenuDishResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.update.MenuDishUpdateDto;
import com.fooddelivery.fooddeliveryapi.domain.entities.MenuDish;
import com.fooddelivery.fooddeliveryapi.mappers.MenuDishMapper;
import com.fooddelivery.fooddeliveryapi.services.MenuDishService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurant_id}/menu-dishes")
public class MenuDishController {

    private final MenuDishService menuDishService;
    private final MenuDishMapper menuDishMapper;

    public MenuDishController(MenuDishService menuDishService, MenuDishMapper menuDishMapper) {
        this.menuDishService = menuDishService;
        this.menuDishMapper = menuDishMapper;
    }

    @GetMapping
    public List<MenuDishResponseDto> listMenuDishes(@PathVariable("restaurant_id") Long restaurantId) {
        return menuDishService.listMenuDishes(restaurantId).stream().map(menuDishMapper::toResponseDto).toList();
    }

    @GetMapping(path = "/{menu_dish_id}")
    public MenuDishResponseDto menuDish(
            @PathVariable("restaurant_id") Long restaurantId,
            @PathVariable("menu_dish_id") Long menuDishId)
    {
        MenuDish menuDish = menuDishService.menuDish(restaurantId, menuDishId);
        return menuDishMapper.toResponseDto(menuDish);
    }

    @PostMapping
    public ResponseEntity<MenuDishCreateDto> createMenuDish(
            @PathVariable("restaurant_id") Long restaurantId,
            @RequestBody @Valid MenuDishCreateDto menuDishCreateDto
    )
    {
        MenuDish savedMenuDish = menuDishService.createMenuDish(restaurantId, menuDishMapper.fromCreateDto(menuDishCreateDto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(menuDishMapper.toCreateDto(savedMenuDish));
    }

    @PatchMapping(path = "/{menu_dish_id}")
    public MenuDishUpdateDto partialUpdateMenuDish(
            @PathVariable("restaurant_id") Long restaurantId,
            @PathVariable("menu_dish_id") Long menuDishId,
            @RequestBody MenuDishUpdateDto menuDishUpdateDto
    )
    {
        return menuDishMapper.toUpdateDto(
                menuDishService.partialUpdate(
                        restaurantId,
                        menuDishId,
                        menuDishMapper.fromUpdateDto(menuDishUpdateDto)));
    }

    @PutMapping(path = "/{menu_dish_id}")
    public MenuDishUpdateDto fullUpdateMenuDish(
            @PathVariable("restaurant_id") Long restaurantId,
            @PathVariable("menu_dish_id") Long menuDishId,
            @RequestBody MenuDishUpdateDto menuDishUpdateDto
    )
    {
        return menuDishMapper.toUpdateDto(
                menuDishService.fullUpdate(
                        restaurantId,
                        menuDishId,
                        menuDishMapper.fromUpdateDto(menuDishUpdateDto)));
    }

    @DeleteMapping(path = "/{menu_dish_id}")
    public void deleteMenuDish(
            @PathVariable("restaurant_id") Long restaurantId,
            @PathVariable("menu_dish_id") Long menuDishId
    )
    {
        menuDishService.deleteMenuDish(restaurantId, menuDishId);
    }
}