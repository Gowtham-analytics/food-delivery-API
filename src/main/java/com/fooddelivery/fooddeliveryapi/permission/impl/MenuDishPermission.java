package com.fooddelivery.fooddeliveryapi.permissions.impl;

import com.fooddelivery.fooddeliveryapi.permissions.Permission;

public enum MenuDishPermission implements Permission {
    VIEW,
    ADD,
    UPDATE,
    DELETE;

    @Override
    public String getCode() {
        return name() + "_MENU_DISH";
    }
}
