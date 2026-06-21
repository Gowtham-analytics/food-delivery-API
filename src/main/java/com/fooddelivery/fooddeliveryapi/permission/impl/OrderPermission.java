package com.fooddelivery.fooddeliveryapi.permission.impl;

import com.fooddelivery.fooddeliveryapi.permission.Permission;

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
