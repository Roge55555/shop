package com.effective.shop.model;

public enum UserPermission {

    USERS_PERMISSION("user:permission"),
    ADMINS_PERMISSION("admin:permission");

    private final String userPermission;

    UserPermission(String userPermission) {
        this.userPermission = userPermission;
    }

    public String getPermission() {
        return userPermission;
    }

}
