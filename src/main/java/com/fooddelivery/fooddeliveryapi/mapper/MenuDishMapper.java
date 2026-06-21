package com.fooddelivery.fooddeliveryapi.mapper;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.MenuDishCreateDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.response.MenuDishResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.update.MenuDishUpdateDto;
import com.fooddelivery.fooddeliveryapi.domain.entity.MenuDish;
import org.springframework.stereotype.Component;

@Component
public class MenuDishMapper {

    public MenuDishCreateDto toCreateDto(MenuDish menuDish) {
        return new MenuDishCreateDto(
                menuDish.getName(),
                menuDish.getPrice(),
                menuDish.getVeg()
        );
    }

    public MenuDish fromCreateDto(MenuDishCreateDto menuDishCreateDto) {
        return new MenuDish(
                null,
                menuDishCreateDto.name(),
                menuDishCreateDto.price(),
                menuDishCreateDto.veg(),
                null,
                menuDishCreateDto.veg(),
                null,
                null,
                null,
                null
        );
    }

    public MenuDishUpdateDto toUpdateDto(MenuDish menuDish) {
        return new MenuDishUpdateDto(
                menuDish.getName(),
                menuDish.getPrice(),
                menuDish.getVeg()
        );
    }

    public MenuDish fromUpdateDto(MenuDishUpdateDto menuDishUpdateDto) {
        return new MenuDish(
                null,
                menuDishUpdateDto.name(),
                menuDishUpdateDto.price(),
                menuDishUpdateDto.veg(),
                null,
                menuDishUpdateDto.veg(),
                null,
                null,
                null,
                null
        );
    }

    public MenuDishResponseDto toResponseDto(MenuDish menuDish) {
        return new MenuDishResponseDto(
                menuDish.getId(),
                menuDish.getName(),
                menuDish.getPrice(),
                menuDish.getVeg()
        );
    }

    public MenuDish fromResponseDto(MenuDishResponseDto menuDishResponseDto) {
        return new MenuDish(
                menuDishResponseDto.id(),
                menuDishResponseDto.name(),
                menuDishResponseDto.price(),
                menuDishResponseDto.veg(),
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
