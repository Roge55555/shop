package com.effective.shop.model;

public enum OrganizationPermission {

    CONSIDERATION_PERMISSION("hidden:permission"),
    APPROVED_PERMISSION("available:permission"),
    FROZEN_PERMISSION("hidden:permission"),
    DELETED_PERMISSION("hidden:permission");

    private final String organizationPermission;

    OrganizationPermission(String organizationPermission) {
        this.organizationPermission = organizationPermission;
    }

    public String getPermission() {
        return organizationPermission;
    }

}
