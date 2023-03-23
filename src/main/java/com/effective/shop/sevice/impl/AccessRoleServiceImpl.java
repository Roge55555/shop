package com.effective.shop.sevice.impl;

import com.effective.shop.entity.AccessRole;
import com.effective.shop.model.Role;
import com.effective.shop.repository.AccessRoleRepository;
import com.effective.shop.sevice.AccessRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessRoleServiceImpl implements AccessRoleService {

    private final AccessRoleRepository accessRoleRepository;

    @Override
    public AccessRole findByName(Role name) {
        return accessRoleRepository.findByName(name).orElseThrow();
    }

    @Override
    public AccessRole findById(Long id) {
        return accessRoleRepository.findById(id).orElseThrow();
    }
}
