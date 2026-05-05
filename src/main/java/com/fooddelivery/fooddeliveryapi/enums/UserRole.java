package com.fooddelivery.fooddeliveryapi.enums;

import com.fooddelivery.fooddeliveryapi.permissions.Permission;
import com.fooddelivery.fooddeliveryapi.permissions.impl.MenuDishPermission;
import com.fooddelivery.fooddeliveryapi.permissions.impl.OrderPermission;
import com.fooddelivery.fooddeliveryapi.permissions.impl.RestaurantPermission;

import java.util.Collections;
import java.util.Set;

public enum UserRole {
    CUSTOMER(Set.of(
            RestaurantPermission.VIEW,
            MenuDishPermission.VIEW,
            OrderPermission.VIEW,
            OrderPermission.ADD,
            OrderPermission.UPDATE,
            OrderPermission.DELETE
    )),
    OWNER(Set.of(
            RestaurantPermission.VIEW,
            RestaurantPermission.ADD,
            RestaurantPermission.UPDATE,
            RestaurantPermission.DELETE,
            MenuDishPermission.VIEW,
            MenuDishPermission.ADD,
            MenuDishPermission.UPDATE,
            MenuDishPermission.DELETE
    ));

    private final Set<Permission> permissions;

    UserRole(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return Collections.unmodifiableSet(permissions);
    }
}