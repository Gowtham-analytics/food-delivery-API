package com.fooddelivery.fooddeliveryapi.Controllers;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.RestaurantCreateDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.response.RestaurantResponseDto;
import com.fooddelivery.fooddeliveryapi.domain.dto.update.RestaurantUpdateDto;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;
import com.fooddelivery.fooddeliveryapi.mappers.RestaurantMapper;
import com.fooddelivery.fooddeliveryapi.services.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    public RestaurantController(RestaurantService restaurantService, RestaurantMapper restaurantMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
    }

    @GetMapping
    public List<RestaurantResponseDto> listRestaurants() {
        return restaurantService.listRestaurants()
                .stream()
                .map(restaurantMapper::toResponseDto)
                .toList();
    }

    @GetMapping(path = "/{restaurant_id}")
    public RestaurantResponseDto getRestaurant(@PathVariable("restaurant_id") Long restaurantId) {

        return restaurantMapper.toResponseDto(restaurantService.getRestaurant(restaurantId));
    }

    @PostMapping
    public ResponseEntity<RestaurantCreateDto> createRestaurant(
            @RequestBody @Valid RestaurantCreateDto restaurantCreateDto)
    {
        Restaurant savedRestaurant = restaurantService.createRestaurant(restaurantMapper.fromCreateDto(restaurantCreateDto));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restaurantMapper.toCreateDto(savedRestaurant));
    }

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

    @DeleteMapping(path = "/{restaurant_id}")
    public void deleteRestaurant(@PathVariable("restaurant_id") Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
    }
}