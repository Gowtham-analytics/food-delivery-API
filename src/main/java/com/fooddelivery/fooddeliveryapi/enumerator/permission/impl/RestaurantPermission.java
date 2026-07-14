package com.fooddelivery.fooddeliveryapi.enumerator.permission.impl;

import com.fooddelivery.fooddeliveryapi.enumerator.permission.Permission;

public enum RestaurantPermission implements Permission {
    VIEW,
    ADD,
    UPDATE,
    DELETE;

    @Override
    public String getCode() {
        return name() + "_RESTAURANT";
    }
}
