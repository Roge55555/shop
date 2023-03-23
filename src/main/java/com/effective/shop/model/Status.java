package com.effective.shop.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Status {

    CONSIDERATION(Set.of(RegistrationPermission.CONSIDERATION_PERMISSION)),
    APPROVED(Set.of(RegistrationPermission.APPROVED_PERMISSION)),
    FROZEN(Set.of(RegistrationPermission.FROZEN_PERMISSION)),
    DELETED(Set.of(RegistrationPermission.DELETED_PERMISSION));

    private final Set<RegistrationPermission> registrationPermissions;

    Status(Set<RegistrationPermission> registrationPermissions) {
        this.registrationPermissions = registrationPermissions;
    }

    public Set<RegistrationPermission> getPermissions() {
        return registrationPermissions;
    }

    public Set<SimpleGrantedAuthority> getAuthority() {
        return getPermissions().stream()
                .map(userPermission -> new SimpleGrantedAuthority(userPermission.getPermission()))
                .collect(Collectors.toSet());
    }

}