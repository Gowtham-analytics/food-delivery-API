package com.fooddelivery.fooddeliveryapi.services;

import com.fooddelivery.fooddeliveryapi.exceptions.ResourceNotFoundException;
import com.fooddelivery.fooddeliveryapi.domain.entities.MenuDish;
import com.fooddelivery.fooddeliveryapi.domain.entities.Restaurant;
import com.fooddelivery.fooddeliveryapi.repositories.MenuDishRepository;
import com.fooddelivery.fooddeliveryapi.repositories.RestaurantRepository;
import com.fooddelivery.fooddeliveryapi.repositories.UserRepository;
import com.fooddelivery.fooddeliveryapi.services.impl.MenuDishServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MenuDishServiceImplTests {

    @Mock
    RestaurantRepository restaurantRepository;

    @Mock
    MenuDishRepository menuDishRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    MenuDishServiceImpl menuDishService;

    @Test
    public void listMenuDishesShouldReturnListOfGetMenuDishesWhenRestaurantExists() {

        Long id = 1L;
        Restaurant testRestaurant = new Restaurant();
        List<MenuDish> testList = List.of(new MenuDish(), new MenuDish());

        when(restaurantRepository.findById(id)).thenReturn(Optional.of(testRestaurant));
        when(menuDishRepository.findByRestaurantId(id)).thenReturn(testList);

        List<MenuDish> result = menuDishService.listMenuDishes(id);

        assertNotNull(result);
        assertEquals(testList.size(), result.size());
    }

    @Test
    public void listGetMenuDishesShouldThrowExceptionWhenRestaurantDoesNotExist() {

        Long id = 1L;

        when(restaurantRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> menuDishService.listMenuDishes(id));
    }

    @Test
    public void menuDishShouldReturnGetMenuDish() {

        Long restaurantId = 1L;
        Long menuDishId = 1L;
        MenuDish testMenuDish = new MenuDish(1L, "testMenuDish", 50.00, true, null, null, null, null, null, null);

        when(menuDishRepository.findByRestaurantIdAndId(restaurantId, menuDishId)).thenReturn(Optional.of(testMenuDish));

        MenuDish result = menuDishService.getMenuDish(restaurantId, menuDishId);

        assertEquals(testMenuDish, result);
    }

    @Test
    public void menuDishShouldThrowExceptionWhenRestaurantOrGetMenuDishDoesNotExist() {

        Long restaurantId = 1L;
        Long menuDishId = 1L;

        when(menuDishRepository.findByRestaurantIdAndId(restaurantId, menuDishId))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> menuDishService.getMenuDish(restaurantId, menuDishId));
    }

    @Test
    public void createMenuDishMustCreateGetMenuDish() {

        Long id = 1L;

        Restaurant testRestaurant = new Restaurant();
        MenuDish testMenuDish = new MenuDish(null, "Veg Cutlet", 50.00, true, testRestaurant, null, null, null, null, null);

        when(restaurantRepository.findByIdAndUserEntityUsername(id, "username")).thenReturn(Optional.of(testRestaurant));
        when(menuDishRepository.save(any(MenuDish.class))).thenReturn(testMenuDish);

        MenuDish result = menuDishService.createMenuDish(id, testMenuDish, "username");

        assertEquals(testMenuDish.getName(), result.getName());
        assertEquals(testMenuDish.getPrice(), result.getPrice());
        assertEquals(testMenuDish.getVeg(), result.getVeg());
        assertEquals(testMenuDish.getRestaurant(), result.getRestaurant());
    }

    @Test
    public void createGetMenuDishShouldThrowExceptionWhenRestaurantDoesNotExist() {

        Long id = 1L;

        MenuDish testMenuDish = new MenuDish(null, "Veg Cutlet", 50.00, true, null, null, null, null, null, null);

        when(restaurantRepository.findByIdAndUserEntityUsername(id, "username")).thenReturn(Optional.empty());

        assertThrows(AccessDeniedException.class,
                () -> menuDishService.createMenuDish(id, testMenuDish, "username"));
    }

    @Test
    public void partialUpdateShouldUpdateGetMenuDish() {

        Long restaurantId = 1L;
        Long menuDishId = 1L;
        MenuDish testMenuDish = new MenuDish(null, "Veg Cutlet", 50.00, true, null, null, null, null, null, null);

        testMenuDish.setName("Poori");
        testMenuDish.setPrice(30.00);

        when(menuDishRepository.findByIdAndRestaurantUserEntityUsername(menuDishId, "username")).thenReturn(Optional.of(testMenuDish));
        when(menuDishRepository.save(testMenuDish)).thenReturn(testMenuDish);

        MenuDish result = menuDishService.partialUpdate(restaurantId, menuDishId, testMenuDish, "username");

        assertEquals(result.getName(), testMenuDish.getName());
        assertEquals(result.getPrice(), testMenuDish.getPrice());
        assertEquals(result.getVeg(), testMenuDish.getVeg());
        assertEquals(result.getRestaurant(), testMenuDish.getRestaurant());
    }

    @Test
    public void partialUpdateShouldThrowExceptionWhenRestaurantOrGetMenuDishDoesNotExist() {

        Long restaurantId = 1L;
        Long menuDishId = 1L;
        Restaurant testRestaurant = new Restaurant();
        MenuDish testMenuDish = new MenuDish(null, "Veg Cutlet", 50.00, true, testRestaurant, null, null, null, null, null);

        testMenuDish.setName("Poori");
        testMenuDish.setPrice(30.00);

        when(menuDishRepository.findByIdAndRestaurantUserEntityUsername(menuDishId, "username")).thenReturn(Optional.empty());

        assertThrows(AccessDeniedException.class,
                () -> menuDishService.partialUpdate(restaurantId, menuDishId, testMenuDish, "username"));
    }

    @Test
    public void fullUpdateShouldUpdateGetMenuDish() {

        Long restaurantId = 1L;
        Long menuDishId = 1L;
        Restaurant testRestaurant = new Restaurant();
        MenuDish testMenuDish = new MenuDish(null, "Veg Cutlet", 50.00, true, testRestaurant, null, null, null, null, null);

        testMenuDish.setName("Poori");
        testMenuDish.setPrice(30.00);
        testMenuDish.setVeg(true);

        when(menuDishRepository.findByIdAndRestaurantUserEntityUsername(menuDishId, "username")).thenReturn(Optional.of(testMenuDish));
        when(menuDishRepository.save(testMenuDish)).thenReturn(testMenuDish);

        MenuDish result = menuDishService.fullUpdate(restaurantId, menuDishId, testMenuDish, "username");

        assertEquals(result.getName(), testMenuDish.getName());
        assertEquals(result.getPrice(), testMenuDish.getPrice());
        assertEquals(result.getVeg(), testMenuDish.getVeg());
        assertEquals(result.getRestaurant(), testMenuDish.getRestaurant());
    }

    @Test
    public void fullUpdateShouldThrowExceptionWhenRestaurantOrGetMenuDishDoesNotExist() {

        Long restaurantId = 1L;
        Long menuDishId = 1L;
        Restaurant testRestaurant = new Restaurant();
        MenuDish testMenuDish = new MenuDish(null, "Veg Cutlet", 50.00, true, testRestaurant, null, null, null, null, null);

        testMenuDish.setName("Poori");
        testMenuDish.setPrice(30.00);
        testMenuDish.setVeg(true);

        when(menuDishRepository.findByIdAndRestaurantUserEntityUsername(menuDishId, "username")).thenReturn(Optional.empty());

        assertThrows(AccessDeniedException.class,
                () -> menuDishService.partialUpdate(restaurantId, menuDishId, testMenuDish, "username"));

    }
}
