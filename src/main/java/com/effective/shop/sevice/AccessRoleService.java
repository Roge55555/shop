package com.effective.shop.sevice;

import com.effective.shop.entity.AccessRole;
import com.effective.shop.model.Role;

public interface AccessRoleService {

    AccessRole findByName(Role name);

    AccessRole findById(Long id);
}
