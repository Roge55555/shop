package com.effective.shop.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    FROZEN(Set.of()),
    USER(Set.of(UserPermission.USERS_PERMISSION)),
    ADMIN(Set.of(UserPermission.ADMINS_PERMISSION, UserPermission.USERS_PERMISSION));

    private final Set<UserPermission> userPermissions;

    Role(Set<UserPermission> userPermissions) {
        this.userPermissions = userPermissions;
    }

    public Set<UserPermission> getPermissions() {
        return userPermissions;
    }

    public Set<SimpleGrantedAuthority> getAuthority() {
        return getPermissions().stream()
                .map(userPermission -> new SimpleGrantedAuthority(userPermission.getPermission()))
                .collect(Collectors.toSet());
    }

}