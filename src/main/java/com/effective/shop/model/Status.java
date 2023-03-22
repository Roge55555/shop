package com.effective.shop.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Status {

    CONSIDERATION(Set.of(OrganizationPermission.CONSIDERATION_PERMISSION)),
    APPROVED(Set.of(OrganizationPermission.APPROVED_PERMISSION)),
    FROZEN(Set.of(OrganizationPermission.FROZEN_PERMISSION)),
    DELETED(Set.of(OrganizationPermission.DELETED_PERMISSION));

    private final Set<OrganizationPermission> organizationPermissions;

    Status(Set<OrganizationPermission> organizationPermissions) {
        this.organizationPermissions = organizationPermissions;
    }

    public Set<OrganizationPermission> getPermissions() {
        return organizationPermissions;
    }

    public Set<SimpleGrantedAuthority> getAuthority() {
        return getPermissions().stream()
                .map(userPermission -> new SimpleGrantedAuthority(userPermission.getPermission()))
                .collect(Collectors.toSet());
    }

}