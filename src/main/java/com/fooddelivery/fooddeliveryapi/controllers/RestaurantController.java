package com.fooddelivery.fooddeliveryapi.controllers;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.RestaurantCreateDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.response.RestaurantResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.update.RestaurantUpdateDto;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;
import com.fooddelivery.fooddeliveryapi.mappers.RestaurantMapper;
import com.fooddelivery.fooddeliveryapi.services.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@EnableMethodSecurity
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    public RestaurantController(RestaurantService restaurantService, RestaurantMapper restaurantMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
    }

    @PreAuthorize("hasAuthority('VIEW_RESTAURANT')")
    @GetMapping
    public List<RestaurantResponseDto> listRestaurants() {
        return restaurantService.listRestaurants()
                .stream()
                .map(restaurantMapper::toResponseDto)
                .toList();
    }

    @PreAuthorize("hasAuthority('VIEW_RESTAURANT')")
    @GetMapping(path = "/{restaurant_id}")
    public RestaurantResponseDto getRestaurant(@PathVariable("restaurant_id") Long restaurantId) {

        return restaurantMapper.toResponseDto(restaurantService.getRestaurant(restaurantId));
    }

    @PreAuthorize("hasAuthority('ADD_RESTAURANT')")
    @PostMapping
    public ResponseEntity<RestaurantCreateDto> createRestaurant(
            @RequestBody @Valid RestaurantCreateDto restaurantCreateDto)
    {
        Restaurant savedRestaurant = restaurantService.createRestaurant(restaurantMapper.fromCreateDto(restaurantCreateDto));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restaurantMapper.toCreateDto(savedRestaurant));
    }

    @PreAuthorize("hasAuthority('UPDATE_RESTAURANT')")
    @PatchMapping(path = "/{restaurant_id}")
    public RestaurantUpdateDto partialUpdateRestaurant(
            @PathVariable("restaurant_id") Long restaurantId,
            @RequestBody @Valid RestaurantUpdateDto restaurantUpdateDto
    )
    {
        return restaurantMapper.toUpdateDto(
                restaurantService.partialUpdateRestaurant(
                        restaurantId,
                        restaurantMapper.fromUpdateDto(restaurantUpdateDto)));
    }

    @PreAuthorize("hasAuthority('UPDATE_RESTAURANT')")
    @PutMapping(path = "/{restaurant_id}")
    public RestaurantUpdateDto fullUpdateRestaurant(
            @PathVariable("restaurant_id") Long restaurantId,
            @RequestBody @Valid RestaurantUpdateDto restaurantUpdateDto
    )
    {
        return restaurantMapper.toUpdateDto(
                restaurantService.fullUpdateRestaurant(
                        restaurantId,
                        restaurantMapper.fromUpdateDto(restaurantUpdateDto)));
    }

    @PreAuthorize("hasAuthority('DELETE_RESTAURANT')")
    @DeleteMapping(path = "/{restaurant_id}")
    public void deleteRestaurant(@PathVariable("restaurant_id") Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
    }
}