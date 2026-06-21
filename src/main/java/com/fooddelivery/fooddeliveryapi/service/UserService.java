package com.fooddelivery.fooddeliveryapi.services;

import com.fooddelivery.fooddeliveryapi.domain.entities.UserEntity;
import com.fooddelivery.fooddeliveryapi.enums.UserRole;

public interface UserService {

    UserEntity getUserFromUsername(String username);
    void createUser(String username, String password, UserRole userRole);
}
