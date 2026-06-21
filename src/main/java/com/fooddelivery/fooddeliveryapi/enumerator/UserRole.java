package com.fooddelivery.fooddeliveryapi.enumerator;

import com.fooddelivery.fooddeliveryapi.permission.Permission;
import com.fooddelivery.fooddeliveryapi.permission.impl.MenuDishPermission;
import com.fooddelivery.fooddeliveryapi.permission.impl.OrderPermission;
import com.fooddelivery.fooddeliveryapi.permission.impl.RestaurantPermission;

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