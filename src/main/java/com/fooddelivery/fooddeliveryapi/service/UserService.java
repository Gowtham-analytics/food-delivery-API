package com.fooddelivery.fooddeliveryapi.service;

import com.fooddelivery.fooddeliveryapi.domain.entity.UserEntity;
import com.fooddelivery.fooddeliveryapi.enumerator.UserRole;

public interface UserService {

    UserEntity getUserFromUsername(String username);
    void createUser(String username, String password, UserRole userRole);
}
