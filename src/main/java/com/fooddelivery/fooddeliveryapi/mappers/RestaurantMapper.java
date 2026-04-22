package com.fooddelivery.fooddeliveryapi.mappers;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.RestaurantCreateDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.response.RestaurantResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.update.RestaurantUpdateDto;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class RestaurantMapper {

    public RestaurantCreateDto toCreateDto(Restaurant restaurant) {
        return new RestaurantCreateDto(
                restaurant.getName(),
                restaurant.getLocation()
        );
    }

    public Restaurant fromCreateDto(RestaurantCreateDto restaurantCreateDto) {
        return new Restaurant(
                null,
                restaurantCreateDto.name(),
                restaurantCreateDto.location(),
                Collections.emptyList(),
                Collections.emptyList(),
                null,
                null,
                null
        );
    }

    public RestaurantUpdateDto toUpdateDto(Restaurant restaurant) {
        return new RestaurantUpdateDto(
                restaurant.getName(),
                restaurant.getLocation()
        );
    }

    public Restaurant fromUpdateDto(RestaurantUpdateDto restaurantUpdateDto) {
        return new Restaurant(
                null,
                restaurantUpdateDto.name(),
                restaurantUpdateDto.location(),
                Collections.emptyList(),
                Collections.emptyList(),
                null,
                null,
                null
        );
    }

    public RestaurantResponseDto toResponseDto(Restaurant restaurant) {
        return new RestaurantResponseDto(
                restaurant.getName(),
                restaurant.getLocation()
        );
    }

    public Restaurant fromResponseDto(RestaurantResponseDto restaurantResponseDto) {
        return new Restaurant(
                null,
                restaurantResponseDto.name(),
                restaurantResponseDto.location(),
                Collections.emptyList(),
                Collections.emptyList(),
                null,
                null,
                null
        );
    }
}
