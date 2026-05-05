package com.fooddelivery.fooddeliveryapi.permissions.impl;

import com.fooddelivery.fooddeliveryapi.permissions.Permission;

public enum OrderPermission implements Permission {
    VIEW,
    ADD,
    UPDATE,
    DELETE;

    @Override
    public String getCode() {
        return name() + "_ORDER";
    }
}
