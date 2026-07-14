package com.fooddelivery.fooddeliveryapi.enumerator.permission.impl;

import com.fooddelivery.fooddeliveryapi.enumerator.permission.Permission;

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
